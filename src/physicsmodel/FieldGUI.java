/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physicsmodel;

import com.sun.javafx.geom.Vec2d;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
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
    @FXML
    private void handleButtonAction(ActionEvent event){
        Charge q= new Charge(0,0,0,Math.pow(1, -6));
        Field f= new Field();
        f.addCharge(new Charge(1,1,0,10));
        f.addCharge(new Charge(5,5,0,25));
        f.updateField();
        Group root2=new Group();
        Scene scene = new Scene(root2,1000,1000);
        Stage stage =new Stage();
        scene.setFill(Color.BLACK);
        for(int i=0;i<f.getCharges().size();i++){
            Sphere s =new Sphere(f.getCharges().get(i).getChargeAmount());
            s.setMaterial(new PhongMaterial(Color.AQUA));
            s.setLayoutX(f.getCharges().get(i).getX()*100);
            s.setLayoutY((f.getCharges().get(i).getY())*100);
            root2.getChildren().add(s);
        }
        Point3D point=new Point3D(500,500,0);
        Slider slider = new Slider(0, 360, 0);
        slider.setBlockIncrement(1);
        slider.setTranslateX(425);
        slider.setTranslateY(625);
        AmbientLight light=new AmbientLight();
        light.setColor(Color.WHITE);
        Vec2d[][] temp=f.getfield();
        ArrayList<Label> labels=new ArrayList<>();
        for(int i=0;i<temp.length;i++){
            for(int j=0;j<temp[0].length;j++){
                Label v=new Label(temp[i][j].toString().substring(5));
                v.setLayoutX(i*100);
                v.setLayoutY(j*100);
                v.setTextFill(Color.WHEAT);
                root2.getChildren().add(v);
                double mag = Math.sqrt(Math.pow(temp[i][j].x,2)+Math.pow(temp[i][j].y,2))*5;
                Cylinder c =  new Cylinder(10,mag);
                
                root2.getChildren().add(c);
//                c.setRotationAxis(new Point3D((i+0.5)*100,(.5+j)*100,0));
//                c.rotateProperty().bind(slider.valueProperty());
                double theta = f.getAngle(i, j);
                System.out.println(theta);
                c.setRotate(theta*180/Math.PI);
                c.setLayoutX(i*100+Math.sin(theta)*mag/2);
                c.setLayoutY(j*100+Math.cos(theta)*mag/2);
//                c.setLayoutX(50*Math.cos(theta)+c.getLayoutX());
//                c.setLayoutY(50*Math.sin(theta)+c.getLayoutY());
            }
        }
//        AdvancedCamera camera= new AdvancedCamera();
//        FPSController co=new FPSController();
//        camera.setController(co);
//        camera.setFieldOfView(100);
//        camera.setLayoutX(200);
//        root2.getChildren().addAll(slider);
        stage.setScene(scene);
        stage.show();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
