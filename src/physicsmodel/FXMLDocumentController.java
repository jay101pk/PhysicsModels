/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physicsmodel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.stage.Stage;
import org.fxyz.geometry.Point3D;
import org.fxyz.shapes.composites.PolyLine3D;

/**
 *
 * @author logerquist3873
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    private Stage stage=new Stage();
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException{
            Parent root2 = FXMLLoader.load(getClass().getResource("FieldGUI.fxml"));
        Scene scene = new Scene(root2,1000,1000);
//        
        stage.setScene(scene);
        stage.show();
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
