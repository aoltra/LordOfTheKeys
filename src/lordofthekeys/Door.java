/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lordofthekeys;

import lordofthekeys.interfaces.IAbrible;

/**
 *
 * @author aoltra
 */
public class Door  implements IAbrible {
    
    boolean _abierta;
    boolean _conLlave;     // true significa
                           // que necesita una llave para 
                           // abrirla
  
    public Door(boolean abierta,boolean conLlave)
    {
        _abierta = abierta;
        _conLlave = conLlave;
    }
    
    @Override
    public boolean abrir() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean cerrar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean estaAbierto() {
        return _abierta;
    }
}
