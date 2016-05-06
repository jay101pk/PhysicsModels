/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physicsmodel;

import com.sun.javafx.geom.Vec2d;
import java.util.ArrayList;
import java.util.Vector;
import javax.vecmath.Vector2d;
import org.fxyz.geometry.Vector3D;

/**
 *
 * @author logerquist3873
 */
public class Field {
    private ArrayList<Charge> charges;
    private Vec2d[][] field;
    public static final double[] ORIGIN={6,6,6};
    
    public Field(int x,int y){
        field=new Vec2d[x][y];
        charges = new ArrayList();
        
    }
    
    public void addCharge(Charge q){
        charges.add(q);
        
    }
    
    public void updateField(){
        for(int i=0;i<field.length;i++){
            for(int j=0;j<field[0].length;j++){
                field[i][j]=new Vec2d(0,0);
                boolean taken =false;
                for(Charge q:charges){
                    if(i==q.getX()&&j==q.getY())
                        taken=true;
                }
                if(!taken){
                    for(Charge q:charges){
                        double x=q.getX();
                        double y=q.getY();
                        double z=q.getZ();
                        double c=q.getChargeAmount();
//                        System.out.println(c*(i-x)/Math.pow(Math.pow(i-x,2) + Math.pow(j-y,2),3/2));
//                        System.out.println(c*(j-y)/Math.pow(Math.pow(i-x,2) + Math.pow(j-y,2),3/2));
                        field[i][j].x += (Math.round(100*c*(i-x)/Math.pow(Math.pow(i-x,2) + Math.pow(j-y,2),3/2))/100);
                        field[i][j].y += (Math.round(100*c*(j-y)/Math.pow(Math.pow(i-x,2) + Math.pow(j-y,2),3/2))/100);
                        
                    }
//                    System.out.println(field[i][j]);
                }
            }
        }
    }
    
    public ArrayList<Charge> getCharges(){
        return charges;
    }
    
    public Vec2d[][] getfield(){
        return field;
    }
    
    public double getAngle(int x,int y){
        double xa =Math.abs(field[x][y].x);
        double ya =Math.abs(field[x][y].y);
        if(field[x][y].x==0&&field[x][y].y<0){
            return 0;
        }
        if(field[x][y].x>0&&field[x][y].y<0){
            return Math.atan(xa/ya);
        }
        if(field[x][y].x>0&&field[x][y].y==0){
            return Math.PI/2;
        }
        if(field[x][y].x>0&&field[x][y].y>0){
            return Math.PI-Math.atan(xa/ya);
        }
        if(field[x][y].x==0&&field[x][y].y>0){
            return Math.PI;
        }
        if(field[x][y].x<0&&field[x][y].y>0){
            return Math.PI+Math.atan(xa/ya);
        }
        if(field[x][y].x<0&&field[x][y].y==0){
            return Math.PI*3/2;
        }
        if(field[x][y].x<0&&field[x][y].y<0){
            return 2*Math.PI-Math.atan(xa/ya);
        }
        
        
        
        
        return 0;
    }
}
