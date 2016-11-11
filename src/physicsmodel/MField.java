/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physicsmodel;

import com.sun.javafx.geom.Vec3d;
import java.util.ArrayList;

/**
 *
 * @author logerquist3873
 */
public class MField {//this class creates and hold the info of an magnetic field created by charges
    public final static double K = Math.pow(10,-7);  
    private ArrayList<Charge> charges;
    private Vec3d[][][] field;
    
    public MField(int x,int y,int z){
        field=new Vec3d[x][y][z];
        charges = new ArrayList();
    }
    
    public void addCharge(Charge q){
        charges.add(q);
        
    }
    
    public void removeCharge(int i){
        charges.remove(i);
    }
    
    public void updateField(){
    //main method of class
    //goes through the entire region and makes vectors to represent the magnetic field field at those given points
        for(int i=0;i<field.length;i++){
            for(int j=0;j<field[0].length;j++){
                for(int k=0;k<field[0][0].length;k++){
                    field[i][j][k]=new Vec3d(0,0,0);
                    boolean taken =false;
                    for(Charge q:charges){
                        if(i==q.getX()&&j==q.getY()&&k==q.getZ())
                            taken=true;
                    }
                    if(!taken){
                        for(Charge q:charges){
                            Vec3d temp=new Vec3d();
                            Vec3d r = new Vec3d(i-q.getX(),j-q.getY(),k-q.getZ());
                            temp.cross(q.getVelocity(),r);
                            temp.mul(K*q.getChargeAmount()/Math.pow(r.length(),3));
                            field[i][j][k].add(temp);
                        }
                    }
                }
            }
        }
    }
    
    public ArrayList<Charge> getCharges(){
        return charges;
    }
    
    public Vec3d[][][] getfield(){
        return field;
    }
    
    public double getAngleS(int x,int y,int z){
        //returns the angle to rotate around the z axis
        //angle is based off the -y axis and goes clockwise direction
        //since im using 3d roatation, i only want to go from 0 to 180 or else i would double the roatation
        double xa =Math.abs(field[x][y][z].x);
        double ya =Math.abs(field[x][y][z].y);
        if(field[x][y][z].x==0&&field[x][y][z].y>0){
            return 0;
        }
        if(field[x][y][z].x==0&&field[x][y][z].y<0){
            return Math.PI;
        }
        if(field[x][y][z].y>0){
            return Math.atan(xa/ya);
        }
        if(field[x][y][z].y==0){
            return Math.PI/2;
        }
        
        if(field[x][y][z].y<0){
            return Math.PI-Math.atan(xa/ya);
        }
        return 0;
    }
    public double getAngleT(int x,int y,int z){
        //returns the angle to rotate around the y axis
        //angle is based off the +x axis and goes clockwise direction
        //this angle can go from 0 to 360
        double xa =Math.abs(field[x][y][z].x);
        double za =Math.abs(field[x][y][z].z);
        if(field[x][y][z].x<0&&field[x][y][z].z==0){
            return 0;
        }
        if(field[x][y][z].x<0&&field[x][y][z].z>0){
            return Math.atan(za/xa);
        }
        if(field[x][y][z].x==0&&field[x][y][z].z>0){
            return Math.PI/2;
        }
        if(field[x][y][z].x>0&&field[x][y][z].z>0){
            return Math.PI-Math.atan(za/xa);
        }
        if(field[x][y][z].x>0&&field[x][y][z].z==0){
            return Math.PI;
        }
        if(field[x][y][z].x>0&&field[x][y][z].z<0){
            return Math.PI+Math.atan(za/xa);
        }
        if(field[x][y][z].x==0&&field[x][y][z].z<0){
            return Math.PI*3/2;
        }
        if(field[x][y][z].x<0&&field[x][y][z].z<0){
            return 2*Math.PI-Math.atan(za/xa);
        }
        return 0;
    }
}
