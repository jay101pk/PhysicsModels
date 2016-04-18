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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.stage.Stage;

/**
 *
 * @author logerquist3873
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    private Stage stage=new Stage();
    
    @FXML
    private void handleButtonAction(ActionEvent event){
//        try{
//            Parent root2 = FXMLLoader.load(getClass().getResource("FXMLDocument1.fxml"));
        Group root2=new Group();
        Scene scene = new Scene(root2,1000,1000);
        scene.setFill(Color.MEDIUMBLUE);
        
        Box box = new Box(100,100,100);
        box.setLayoutX(75);
        box.setLayoutY(200);
        Point3D point=new Point3D(100,150,50);
        box.setRotationAxis(point);
        Slider slider = new Slider(0, 360, 0);
        slider.setBlockIncrement(1);
        slider.setTranslateX(425);
        slider.setTranslateY(625);
        box.rotateProperty().bind(slider.valueProperty());
        PerspectiveCamera camera= new PerspectiveCamera();
        camera.setFieldOfView(100);
        camera.setLayoutX(200);
        root2.getChildren().addAll(box, slider,camera);
        
        stage.setScene(scene);
        stage.show();
        
//        }
//        catch(IOException error){
//            System.out.println("didnt work");
//        }
    }
    
    @FXML
    private void handleClose(ActionEvent event){
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
