/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lordofthekeys;

import lordofthekeys.interfaces.ICogible;
import lordofthekeys.interfaces.IUsable;
import lordofthekeys.interfaces.IUtilizable;

/**
 *
 * @author aoltra
 */
public class Key extends Stuff implements ICogible, IUsable
{
    public Key()
    {
        super("llave");
    }
    
    public Key(IUtilizable util)
    {
        super("llave",util);
    }

    @Override
    public boolean coger() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean usar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
