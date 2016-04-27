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
    private int x,y,z,q;
    
    public Charge(int xc, int yc,int zc, int qa){
        x=xc;
        y=yc;
        z=zc;
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
    
    public int getChargeAmount(){
        return q;
    }
    
    
}
