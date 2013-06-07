/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lordofthekeys;

import lordofthekeys.interfaces.IUtilizable;

/**
 * Clase que definira las cosas que existen en las 
 * habitaciones
 * 
 * @author aoltra
 */
public class Stuff 
{
    private String _nombre;
    private IUtilizable _utilizable;
    
    public Stuff(String nombre)
    {
        _nombre = nombre;
    }
    
    public Stuff(String nombre,IUtilizable util)
    {
        _nombre = nombre;
        _utilizable = util;
    }
    
    public String getNombre() { return _nombre; }
   
}
