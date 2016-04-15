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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
        try{
            Parent root2 = FXMLLoader.load(getClass().getResource("FXMLDocument1.fxml"));
        
        Scene scene = new Scene(root2);
        stage.setScene(scene);
        stage.show();
        }
        catch(IOException error){
            System.out.println("didnt work");
        }
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
