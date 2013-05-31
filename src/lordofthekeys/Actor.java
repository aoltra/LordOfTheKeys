/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lordofthekeys;

import java.util.ArrayList;

/**
 *
 * @author aoltra
 */
public class Actor 
{
    // numero maximo de cosas que puede llevar el usuario
    private final static int MAX_NUM_COSAS = 2; 
    private String _nombre;
    
    // posicion en el mapa en la que se 
    // encuentra el personaje
    private int _columna;
    private int _fila;
    
    private ArrayList<Stuff> _inventario; // vector de cosas que lleva el jugador encima
    
    public Actor(int fil,int col)
    {
        // el personaje esta en el pasillo
        // en el momento de empezar
        _fila = fil;
        _columna = col;
        
        _inventario = new ArrayList<Stuff>(); 
    }
    
    public void setInRoom(int fila,int col)
    {
        _fila =fila;
        _columna = col;
    }
    
    /**
     * Devuelve la fila en la que se encuentra el actor
     * @return fila en la que se encuentra el actor
     */
    public int getFila() { return _fila;}
    
    /**
     * Devuelve la columna en la que se encuentra el actor
     * @return columna en la que se encuentra el actor
     */
    public int getColumna() { return _columna;}
    
    /**
     * Asigna la fila en la que esta eñ actor
     * @param fila 
     */
    public void setFila(int fila) { _fila = fila;  }
    
    /**
     * Asigna la columna en la que esta eñ actor
     * @param columna 
     */
    public void setColumna(int columna) { _columna = columna;  }
    
    /**
     * Añade una cosa al inventario si puede
     * @param cosa
     * @return true si la ha añadido, false si no la ha añadido
     */
    public boolean addCosaInventario(Stuff cosa)
    {
        if (_inventario.size()== MAX_NUM_COSAS) 
           return false;
        else
        {
            _inventario.add(cosa);
            return true;
        }
    }
    
    
    /**
     * Coge una cosa de la habitacion y la develve
     * Borra la cosa de la lista de cosas de la habitacion
     * @param cosa
     * @return 
     */
    public Stuff coge(Room habitacion, String cosa)
    {
        Stuff objetoCosa;
        
        for (int i =0;i<habitacion.getNumeroCosas();i++)   
        {
            // cojo el objeto Stuff en funcion de su nombre
            // y lo almaceno en una variable
            objetoCosa  = habitacion.getCosa(i);
            
            if (objetoCosa.getNombre().equals(cosa))
            {
                
                // elimino el objeto cosa de la lista de cosas
                habitacion.eliminaCosa(i);
                
                // devuelvo el objeto
                return objetoCosa;  
            }
        } 
        
        return null;
    }
    
    /**
     * Deja una cosa cosa en una habitacion
     * @return true si lo puede dejar, false si no lo puede dejar
     */
    public boolean deja(String cosa,Room habitacion)
    {
        int i;
        Stuff objetoCosa =null;
        
        for (i=0; i<_inventario.size();i++)
        {
            objetoCosa = _inventario.get(i);
            if (objetoCosa.getNombre().equals(cosa))
            {
                
                break;
            }
        }
        
        if (i == _inventario.size())
            return false;
        
        
        habitacion.setCosa(objetoCosa);
        _inventario.remove(i);
        
        
        return true;
    }
    
    /* muestra una lista del inventario del actor
     * 
     */
    public void muestraInventario()
    {
        // si hay al menos una cosa entonces pongo lo de ademas veo
        if (_inventario.size()>0)
        {
            System.out.println("Encima llevo:");
        }
        else
            System.out.println("No llevo nada encima.");
        
        for (int i =0;i<_inventario.size();i++)
        {
            System.out.println(_inventario.get(i).getNombre());
        }
    }
}
