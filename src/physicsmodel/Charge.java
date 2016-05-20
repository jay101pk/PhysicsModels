/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physicsmodel;

/**
 *
 * @author logerquist3873
 */
public class Charge {
    private int x,y,z;
    private double q;
    
    public Charge(int[] p, double qa){
        x=p[0];
        y=p[1];
        z=p[2];
        q=qa;
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
    @Override
    public String toString(){
        return "("+x+","+y+","+z+","+Math.round(q*Math.pow(10, 9))+"nc)";
    }
    
}
