/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physicsmodel;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 *
 * @author jsnlg
 */
public class Models {
    @FXML
    private ListView models;
    @FXML
    private void handleRun() throws IOException{
        Parent root=new Group();
        if(models.getSelectionModel().isSelected(0)){
            root = FXMLLoader.load(getClass().getResource("EFieldGUI.fxml"));
        }
        else if(models.getSelectionModel().isSelected(1)){
            root = FXMLLoader.load(getClass().getResource("MFieldGUI.fxml"));
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    
}
