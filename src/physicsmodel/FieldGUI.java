/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physicsmodel;

import com.sun.javafx.geom.Vec2d;
import com.sun.javafx.geom.Vec3d;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ListView.EditEvent;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import org.fxyz.cameras.AdvancedCamera;
import org.fxyz.cameras.controllers.FPSController;
import org.fxyz.cameras.controllers.OrbitController;
import org.fxyz.geometry.Point3D;
import org.fxyz.geometry.Vector3D;
import org.fxyz.shapes.SphereSegment;
import org.fxyz.shapes.Tetrahedron;
import org.fxyz.shapes.Torus;
import org.fxyz.shapes.composites.PolyLine3D;

/**
 *
 * @author logerquist3873
 */

public class FieldGUI implements Initializable{
    private Field f= new Field(20,20,20);
    private int s =0,t=0,theta=0;
    @FXML
    private TextField cText,xText,yText,zText;
    @FXML
    private ListView charges;
    @FXML
    private void handleButtonAction(ActionEvent event){
        Charge q= new Charge(0,0,0,Math.pow(1, -6));
        f.updateField();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Group root2=new Group();
        Scene scene = new Scene(root2,screenSize.width,screenSize.height);
        Stage stage =new Stage();
        scene.setFill(Color.BLACK);
        double xfactor =scene.getWidth()/f.getfield().length;
        double yfactor = scene.getHeight()/f.getfield()[0].length;
        for(int i=0;i<f.getCharges().size();i++){
            Sphere s =new Sphere(25);
            Color c;
            if(f.getCharges().get(i).getChargeAmount()>0)
                c= Color.BLUE;
            else
                c=Color.RED; 
//            for(int o = 0; o<(Math.abs(f.getCharges().get(i).getChargeAmount()))/10;o++){
//                c=c.darker();
//            }
            s.setMaterial(new PhongMaterial(c));
            s.setTranslateX(f.getCharges().get(i).getX()*xfactor);
            s.setTranslateY((f.getCharges().get(i).getY())*yfactor);
            s.setTranslateZ(f.getCharges().get(i).getZ()*100);
            root2.getChildren().add(s);
        }
        ArrayList<Label> labels=new ArrayList<>();
        for(int i=0;i<f.getfield().length;i++){
            for(int j=0;j<f.getfield()[0].length;j++){
                for(int k=0;k<f.getfield()[0][0].length;k++){
                    Sphere s =new Sphere(5);
                    s.setMaterial(new PhongMaterial(Color.GREEN));
                    s.setTranslateX(i*xfactor);
                    s.setTranslateY(j*yfactor);
                    s.setTranslateZ(k*100);
                    root2.getChildren().add(s);
                    double mag = Math.sqrt(f.getfield()[i][j][k].dot(f.getfield()[i][j][k]))*5;
                    Cylinder c =  new Cylinder(2,mag);
                
                    root2.getChildren().add(c);
                    double theta = f.getAngleT(i, j,k);
                    double sigma=f.getAngleS(i, j,k);
                    
                    System.out.println(sigma);
                    Rotate rz=new Rotate(0,0,0,0,Rotate.Z_AXIS);
                    Rotate ry=new Rotate(0,0,0,0,Rotate.Y_AXIS);
                    c.getTransforms().addAll(ry,rz);
                    rz.setAngle(theta*180/Math.PI);
                    ry.setAngle(sigma*180/Math.PI);
//                    c.setTranslateX(i*xfactor+Math.sin(theta)*mag/2);
//                    c.setTranslateY(j*yfactor-Math.cos(theta)*mag/2);
                    c.setTranslateX(i*xfactor+Math.sin(theta)*mag/2);
                    c.setTranslateY(j*yfactor-Math.cos(theta)*mag/2);
                    c.setTranslateZ(k*100+Math.cos(sigma)*mag/2);
                }
            }
        }
        AdvancedCamera camera= new AdvancedCamera();
        camera.setTranslateZ(-1000);
        camera.setTranslateX(scene.getWidth()/2);
        camera.setTranslateY(scene.getHeight()/2);
        FPSController co=new FPSController();
        camera.setController(co);
        scene.setCamera(camera);
        Rotate rx=new Rotate(0,0,0,2000,Rotate.X_AXIS);
        Rotate ry=new Rotate(0,0,0,2000,Rotate.Y_AXIS);
        camera.getTransforms().add(rx);
        camera.getTransforms().add(ry);
        camera.setRotate(0);
        EventHandler z; 
        z = new EventHandler<KeyEvent>() 
        {
            
            @Override
            public void handle(KeyEvent k) //this code runs whenver a spot is clicked
            {
                if(k.getCode().equals(KeyCode.RIGHT)){
                    t=(t+5)%360;
                }
                else if(k.getCode().equals(KeyCode.LEFT))
                    t=(t+355)%360;
                else if(k.getCode().equals(KeyCode.UP))
                    s=(s+5)%360;
                else if(k.getCode().equals(KeyCode.DOWN))
                    s=(s+355)%360;
                else if(k.getCode().equals(KeyCode.W))
                    camera.setTranslateZ(camera.getTranslateZ()+20);
                else if(k.getCode().equals(KeyCode.S))
                    camera.setTranslateZ(camera.getTranslateZ()-20);
                else if(k.getCode().equals(KeyCode.D))
                    camera.setTranslateX(camera.getTranslateX()+20);
                else if(k.getCode().equals(KeyCode.A))
                    camera.setTranslateX(camera.getTranslateX()-20);
                else if(k.getCode().equals(KeyCode.A))
                    camera.setTranslateX(camera.getTranslateX()-20);
                rx.setAngle(s);
                rx.setPivotX(scene.getWidth()/2-camera.getTranslateX());
                rx.setPivotY(scene.getHeight()/2-camera.getTranslateY());
                rx.setPivotZ(1000-camera.getTranslateZ());
                ry.setAngle(t);
                ry.setPivotX(scene.getWidth()/2-camera.getTranslateX());
                ry.setPivotY(scene.getHeight()/2-camera.getTranslateY());
                ry.setPivotZ(1000-camera.getTranslateZ());
            }
            
        };
        scene.setOnKeyPressed(z);
//        z = new EventHandler<MouseEvent>() 
//        {
//            
//            @Override
//            public void handle(MouseEvent k) //this code runs whenver a spot is clicked
//            {
//                k.
//            }
//            
//        };
//        scene.setOnMouseDragged(z);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void handleAddCharge(){
        int x = Integer.parseInt(xText.getText());
        int y = Integer.parseInt(yText.getText());
        int z = Integer.parseInt(zText.getText());
        double q = Integer.parseInt(cText.getText());
        Charge c = new Charge(x,y,z,q);
        f.addCharge(c);
        charges.getItems().add(c);
        charges.setEditable(true);
        System.out.println(f.getCharges());
        System.out.println(charges.getItems());
    }
    
    @FXML
    private void handleRemoveCharge(EditEvent event){
        System.out.println(event.getIndex());
//        if(charges.getItems().size()>0){
//            f.removeCharge(event.getIndex());
//            charges.getItems().remove(event.getIndex());
//        }
        System.out.println(charges.getEditingIndex());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
