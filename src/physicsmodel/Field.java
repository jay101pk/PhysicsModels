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
    
    public Field(){
        field=new Vector3D[11][11];
        charges = new ArrayList();
    }
    
    public void addCharge(Charge q){
        charges.add(q);
        
    }
}
