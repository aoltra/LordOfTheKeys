/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lordofthekeys;

import lordofthekeys.interfaces.ICogible;
import lordofthekeys.interfaces.IUsable;

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

    @Override
    public boolean coger() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean usar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
