/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physicsmodel;

import com.sun.javafx.geom.Vec3d;

/**
 *  
 * @author logerquist3873
 */
public class Charge {//purpose of this class is to hold info of charges
    private int x,y,z;
    private double q;
    private Vec3d vel;
    
    public Charge(int[] p, double qa, Vec3d vel){
        x=p[0];
        y=p[1];
        z=p[2];
        q=qa;
        this.vel=vel;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public int getZ(){
        return z;
    }
    
    public double getChargeAmount(){
        return q;
    }
    
    public Vec3d getVelocity(){
        return vel;
    }
    @Override
    public String toString(){
        return "("+x+","+y+","+z+","+Math.round(q*Math.pow(10, 9))+"nc)";
    }
    
}
