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
    
    private boolean _abierta;
    private boolean _conLlave;     // true significa
                           // que necesita una llave para 
                           // abrirla
    
    private boolean _yaAbierta;    // si la puerta estaba cerrada con llave y se ha abierto
  
    public enum EstadosPuertas { SINPUERTA, ABIERTA, CERRADA_SIN_LLAVE, CERRADA_CON_LLAVE};
    
    
    public Door(boolean abierta,boolean conLlave)
    {
        _abierta = abierta;
        _conLlave = conLlave;
        _yaAbierta = false;
    }
    
    @Override
    public boolean abrir() 
    {
        _abierta=true;
        System.out.println("He abierto la puerta.");
        return true;
    }

    @Override
    public boolean cerrar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean estaAbierto() {
        return _abierta;
    }
    
    public boolean getConLlave()
    {
        // si la puerta no se ha aboerto nunca y requiere llave
        if (_conLlave==true && _yaAbierta==false)
            return true;
        
        return false;
    }
}

