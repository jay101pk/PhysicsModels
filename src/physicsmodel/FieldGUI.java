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
import javafx.geometry.Point3D;
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
import org.fxyz.geometry.Vector3D;
import org.fxyz.shapes.SphereSegment;
import org.fxyz.shapes.Tetrahedron;
import org.fxyz.shapes.Torus;

/**
 *
 * @author logerquist3873
 */

public class FieldGUI implements Initializable{
    private Field f= new Field(20,20,20);
    private int s =0;
    @FXML
    private TextField cText,xText,yText,zText;
    @FXML
    private ListView charges;
    @FXML
    private void handleButtonAction(ActionEvent event){
        Charge q= new Charge(0,0,0,Math.pow(1, -6));
        
        
//        f.addCharge(new Charge(15,15,0,50));
//        f.addCharge(new Charge(5,5,0,25));
//        f.addCharge(new Charge(7,7,0,8));
        f.updateField();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Group root2=new Group();
        Scene scene = new Scene(root2,screenSize.width,screenSize.height);
        Stage stage =new Stage();
        scene.setFill(Color.BLACK);
        double xfactor =scene.getWidth()/f.getfield().length;
        double yfactor = scene.getHeight()/f.getfield()[0].length;
        for(int i=0;i<f.getCharges().size();i++){
            Sphere s =new Sphere(f.getCharges().get(i).getChargeAmount());
            s.setMaterial(new PhongMaterial(Color.AQUA));
            s.setTranslateX(f.getCharges().get(i).getX()*xfactor);
            s.setTranslateY((f.getCharges().get(i).getY())*yfactor);
            s.setTranslateZ(f.getCharges().get(i).getZ()*100);
            root2.getChildren().add(s);
        }
        Point3D point=new Point3D(500,500,0);
        Slider slider = new Slider(-1000, -2000, -1000);
        slider.setBlockIncrement(1);
        slider.setTranslateX(425);
        slider.setTranslateY(625);
        AmbientLight light=new AmbientLight();
        light.setColor(Color.WHITE);
        Vec3d[][][] temp=f.getfield();
        ArrayList<Label> labels=new ArrayList<>();
        for(int i=0;i<temp.length;i++){
            for(int j=0;j<temp[0].length;j++){
//                Label v=new Label(temp[i][j].toString().substring(5));
//                v.setLayoutX(i*100);
//                v.setLayoutY(j*100);
//                v.setTextFill(Color.WHEAT);
//                root2.getChildren().add(v);
                Sphere s =new Sphere(5);
                s.setMaterial(new PhongMaterial(Color.GREEN));
                s.setTranslateX(i*xfactor);
                s.setTranslateY(j*yfactor);
//                root2.getChildren().add(s);
                double mag = Math.sqrt(Math.pow(temp[i][j][0].x,2)+Math.pow(temp[i][j][0].y,2))*5;
                Cylinder c =  new Cylinder(2,mag);
                
                root2.getChildren().add(c);
//                c.setRotationAxis(new Point3D((i+0.5)*100,(.5+j)*100,0));
//                c.rotateProperty().bind(slider.valueProperty());
                double theta = f.getAngle(i, j);
//                System.out.println(theta);
                c.setRotate(theta*180/Math.PI);
                c.setTranslateX(i*xfactor+Math.sin(theta)*mag/2);
                c.setTranslateY(j*yfactor-Math.cos(theta)*mag/2);
//                c.setTranslateZ(-200);
//                c.setLayoutX(50*Math.cos(theta)+c.getLayoutX());
//                c.setLayoutY(50*Math.sin(theta)+c.getLayoutY());
            }
        }
        AdvancedCamera camera= new AdvancedCamera();
//        camera.setRotate(30);
        camera.setTranslateZ(-1000);
        camera.setTranslateX(scene.getWidth()/2);
        camera.setTranslateY(scene.getHeight()/2);
        FPSController co=new FPSController();
        camera.setController(co);
        scene.setCamera(camera);
        camera.setRotationAxis(Rotate.Y_AXIS);
        EventHandler z; 
        z = new EventHandler<KeyEvent>() 
        {
            
            @Override
            public void handle(KeyEvent t) //this code runs whenver a spot is clicked
            {
                if(t.getCode().equals(KeyCode.RIGHT)){
                    s=(s+10)%360;
                }
                else
                    s=(s+350)%360;
                camera.setTranslateZ(-Math.cos(Math.PI/180*s)*1000);
                camera.setTranslateX(Math.sin(s*Math.PI/180)*1000+scene.getWidth()/2);
                camera.setRotate(-s);
            }
            
        };
        scene.setOnKeyPressed(z);
        
//        camera.setFieldOfView(100);
//        camera.setLayoutX(200);
//        root2.getChildren().addAll(light,slider);
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
