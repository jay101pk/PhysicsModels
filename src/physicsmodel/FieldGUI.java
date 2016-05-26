/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physicsmodel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.fxyz.cameras.AdvancedCamera;

/**
 *
 * @author logerquist3873
 */

public class FieldGUI implements Initializable{
    //this class is repsonsible for taking the field info and displaying it 3d
    private Field f= new Field(19,19,19);
    private int s =0,t=0;
    @FXML
    private TextField cText,xText,yText,zText;
    @FXML
    private ListView charges;
    @FXML
    private CheckBox rot;
    @FXML
    private void handleButtonAction(ActionEvent event){
        //main method that is responsible for displaying the field vectors
        int[] p ={10,10,10};
        Charge q= new Charge(p,50*Math.pow(10, -9));
        
        f.addCharge(q);
        f.updateField();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Group root2=new Group();//root2 is where i put all the visual objects into to display on the screen
        Scene scene = new Scene(root2,screenSize.width,screenSize.height);//Scene tells the window what to display
        Stage stage =new Stage();//stage is the actual window
        scene.setFill(Color.BLACK);
        AdvancedCamera camera= new AdvancedCamera();//camera is responsible for showing from various angles what is on the screen
        camera.setTranslateZ(-1000);
        scene.setCamera(camera);
        Rotate rx=new Rotate(0,0,0,2000,Rotate.X_AXIS);
        Rotate ry=new Rotate(0,0,0,2000,Rotate.Y_AXIS);
        camera.getTransforms().add(rx);
        camera.getTransforms().add(ry);
        if(rot.isSelected()){
            Timeline time=new Timeline();
            time.getKeyFrames().addAll(new KeyFrame(Duration.ZERO,new KeyValue(ry.angleProperty(),0)),new KeyFrame(new Duration(50000.0),new KeyValue(ry.angleProperty(),360)));
            time.setCycleCount(Timeline.INDEFINITE);
            time.play();
        }
        EventHandler z; 
        //this event occurs whenever a key is pressed while on the screen
        //camera rotates around the center of the field
        z = new EventHandler<KeyEvent>() 
        {
            @Override
            public void handle(KeyEvent k)
            {
                switch (k.getCode()) {
                    case LEFT:
                        t=(t+5)%360;
                        break;
                    case RIGHT:
                        t=(t+355)%360;
                        break;
                    case DOWN:
                        s=(s+5)%360;
                        break;
                    case UP:
                        s=(s+355)%360;
                        break;
                    case W:
                        camera.setTranslateZ(camera.getTranslateZ()+100);
                        break;
                    case S:
                        camera.setTranslateZ(camera.getTranslateZ()-100);
                        break;
                    case D:
                        camera.setTranslateX(camera.getTranslateX()+100);
                        break;
                    case A:
                        camera.setTranslateX(camera.getTranslateX()-100);
                        break;
                    default:
                        break;
                }
                rx.setAngle(s);
                rx.setPivotX(-camera.getTranslateX());
                rx.setPivotY(-camera.getTranslateY());
                rx.setPivotZ(1000-camera.getTranslateZ());
                if(!rot.isSelected())
                    ry.setAngle(t);
                ry.setPivotX(-camera.getTranslateX());
                ry.setPivotY(-camera.getTranslateY());
                ry.setPivotZ(1000-camera.getTranslateZ());
            }
            
        };
        scene.setOnKeyPressed(z);
        for(int i=0;i<f.getCharges().size();i++){
            Sphere s =new Sphere(25);
            Color c;
            if(f.getCharges().get(i).getChargeAmount()>0)
                c= Color.BLUE;
            else
                c=Color.RED;
            s.setMaterial(new PhongMaterial(c));
            s.setTranslateX(f.getCharges().get(i).getX()*100-1000);
            s.setTranslateY((f.getCharges().get(i).getY())*100-1000);
            s.setTranslateZ(f.getCharges().get(i).getZ()*100);
            root2.getChildren().add(s);
        }
        for(int i=0;i<f.getfield().length;i++){
            for(int j=0;j<f.getfield()[0].length;j++){
                for(int k=0;k<f.getfield()[0][0].length;k++){
                    double mag = Math.sqrt(f.getfield()[i][j][k].dot(f.getfield()[i][j][k]))*5;
                    Cylinder c =  new Cylinder(2,mag);
                    root2.getChildren().add(c);
                    double theta = f.getAngleT(i, j,k);
                    double sigma=f.getAngleS(i, j,k);
                    Rotate oz=new Rotate(0,0,0,0,Rotate.Z_AXIS);
                    Rotate oy=new Rotate(0,0,0,0,Rotate.Y_AXIS);
                    c.getTransforms().addAll(oy,oz);
                    oz.setAngle(sigma*180/Math.PI);
                    oy.setAngle(theta*180/Math.PI);
                    c.setTranslateX(i*100-mag/2*Math.sin(sigma)*Math.cos(theta)-1000);
                    c.setTranslateY(j*100+mag/2*Math.cos(sigma)-1000);
                    c.setTranslateZ(k*100+mag/2*Math.sin(sigma)*Math.sin(theta));
                    Sphere s =new Sphere(2);
                    s.setMaterial(new PhongMaterial(Color.AQUA));
                    s.setTranslateX(i*100-mag*Math.sin(sigma)*Math.cos(theta)-1000);
                    s.setTranslateY(j*100+mag*Math.cos(sigma)-1000);
                    s.setTranslateZ(k*100+mag*Math.sin(sigma)*Math.sin(theta));
                    root2.getChildren().add(s);
                }
            }
        }
        stage.setScene(scene);
        stage.show();
        stage.setMaximized(true);
    }
    @FXML
    private void handleAddCharge(){
        int x = Integer.parseInt(xText.getText());
        int y = Integer.parseInt(yText.getText());
        int z = Integer.parseInt(zText.getText());
        double q = Integer.parseInt(cText.getText());
        int[] p={x,y,z};
        Charge c = new Charge(p,q*Math.pow(10, -9));
        f.addCharge(c);
        charges.getItems().setAll(f.getCharges());
        charges.setEditable(true);
//        System.out.println(f.getCharges());
//        System.out.println(charges.getItems());
    }
    
    @FXML
    private void handleClearField(){
        f=new Field(19,19,19);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
