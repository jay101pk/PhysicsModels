/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physicsmodel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.stage.Stage;

/**
 *
 * @author logerquist3873
 */
public class FieldGUI implements Initializable{
    @FXML
    private void handleButtonAction(ActionEvent event){
        Charge q= new Charge(0,0,0,10);
        Field f= new Field();
        f.addCharge(new Charge(1,1,0,10));
        f.updateField();
//        Group root2=new Group();
//        Scene scene = new Scene(root2,1000,1000);
//        Stage stage =new Stage();
//        scene.setFill(Color.MEDIUMBLUE);
//        
//        Box box = new Box(100,100,100);
//        box.setLayoutX(75);
//        box.setLayoutY(200);
//        Point3D point=new Point3D(100,150,50);
//        box.setRotationAxis(point);
//        Slider slider = new Slider(0, 360, 0);
//        slider.setBlockIncrement(1);
//        slider.setTranslateX(425);
//        slider.setTranslateY(625);
//        box.rotateProperty().bind(slider.valueProperty());
//        PerspectiveCamera camera= new PerspectiveCamera();
//        camera.setFieldOfView(100);
//        camera.setLayoutX(200);
//        root2.getChildren().addAll(box, slider,camera);
//        
//        stage.setScene(scene);
//        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
