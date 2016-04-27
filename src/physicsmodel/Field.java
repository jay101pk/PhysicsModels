/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physicsmodel;

import java.util.ArrayList;
import java.util.Vector;
import org.fxyz.geometry.Vector3D;

/**
 *
 * @author logerquist3873
 */
public class Field {
    private ArrayList<Charge> charges;
    private Vector3D[][] field;
    public static final double[] ORIGIN={6,6,6};
    
    public Field(){
        field=new Vector3D[11][11];
        charges = new ArrayList();
        
    }
    
    public void addCharge(Charge q){
        charges.add(q);
        
    }
    
    public void updateField(){
        for(int i=0;i<field.length;i++){
            for(int j=0;j<field[0].length;j++){
                boolean taken =false;
                for(Charge q:charges){
                    if(i==q.getX()&&j==q.getZ())
                        taken=true;
                }
                if(!taken){
                    for(Charge q:charges){
                        int x=q.getX();
                        int y=q.getY();
                        int z=q.getZ();
                        field[i][j].add((i-x)/((i-x)^2 + (j-y)^2)^(3/2),(j-y)/((i-x)^2 + (j-y)^2)^(3/2) , 0);
                    }
                    System.out.println(field[i][j]);
                }
            }
        }
    }
}
