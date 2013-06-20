/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lordofthekeys;

import lordofthekeys.interfaces.ICogible;

/**
 *
 * @author aoltra
 */
public class Examen extends Stuff implements ICogible {

    public Examen()
    {
        super("examen");
    }
    
    
    @Override
    public boolean coger() {
        System.out.println("Que bueno que eres!!! has conseguido robar el examen!!");
        return true;
    }
    
}
