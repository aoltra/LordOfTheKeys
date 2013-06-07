/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lordofthekeys;

import java.util.ArrayList;
import lordofthekeys.interfaces.ICogible;
import lordofthekeys.interfaces.IUtilizable;

/**
 *
 * @author aoltra
 */
public class Room {
    
    public enum PuntosCardinales { SUR, ESTE, NORTE, OESTE};
    
    private String _nombre;
    private String _descripcion;
    
    private boolean _hayLuz;
    
    private Door [] _puertas;
    
    private ArrayList<Stuff> _cosas;    // lista de cosas que hay en la habitacion
    
    
    
    public Room(String nombre,
            String descrip,
            boolean hayLuz)
    {
        _nombre = nombre;
        _descripcion = descrip;
        _hayLuz = hayLuz;
        
        
        _puertas = new Door[4];
        
        for (int contador=0;contador<4;contador++)
        {
            _puertas[contador]=null;
        }
        
        _cosas = new ArrayList<Stuff>();
    }
    
    /**
     * Asigno una puerta en una posicion
     * @param posicion
     * @param puerta 
     */
    public void setDoor(PuntosCardinales posicion, Door puerta)
    {
          _puertas[posicion.ordinal()]=puerta;
    }
    
    /**
     * Coge la puerta que esta en la habitacion en la direccion propuesta
     * @param direccion direccion en la que quiero ver si hay puerta
     * @return 
     */
    public Door getDoor(Room.PuntosCardinales direccion)
    {
        return _puertas[direccion.ordinal()];
    }
    
    /**
     * Devuelve el nombre de la habitacion
     * @return 
     */
    public String getNombre() { return _nombre; }
    
    /**
     * Devuelve la descripcion de la habitacion
     * @return 
     */
    public String getDescripcion() { return _descripcion; }
    
    /**
     * introduce en la lista de cosas una cosa
     * @param cosa 
     */
    public void setCosa(Stuff cosa)
    {
        _cosas.add(cosa);
    }
    
    /** 
     * muestra en pantalla una lista de las cosas que hay en la habitacion 
     */
    public void muestraListaCosas()
    {
        // si hay al menos una cosa entonces pongo lo de ademas veo
        if (_cosas.size()>0)
        {
            System.out.println("Además veo:");
        }
        else
            System.out.println("No veo nada más.");
        
        for (int i =0;i<_cosas.size();i++)
        {
            System.out.println(_cosas.get(i).getNombre());
        }
    }
    

    /**
     * Comprueba si una cosa esta en la habitación
     * @param cosa cadena queidentifica la cosa que voy a comprobar si esta
     * @return true si la cosa esta y false si la cosa no esta
     */
    public boolean estaCosa(String cosa)
    {
        for (int i =0;i<_cosas.size();i++)   
        {
            if (_cosas.get(i).getNombre().equals(cosa))
                return true;  // lo he encontrado!!
        }
        
        
        return false;
    }
    
    
    public boolean estaCosa(IUtilizable cosa)
    {
        for (int i =0;i<_cosas.size();i++)   
        {
            if ((Stuff)_cosas.get(i) instanceof IUtilizable)
                if ((Stuff)_cosas.get(i)== cosa)
                    return true;  // lo he encontrado!!
        }
        
        
        return false;
    }
    
    /**
     * Comprueba si una cosa cogible esta en la habitación
     * @param cosa cadena que identifica la cosa que voy a comprobar si esta
     * @return true si la cosa esta y false si la cosa no esta
     */
    public boolean estaCosaCogible(String cosa)
    {
        for (int i =0;i<_cosas.size();i++)   
        {
            if (_cosas.get(i).getNombre().equals(cosa) 
                    && _cosas.get(i) instanceof ICogible)
                return true;  // lo he encontrado!!
        }
        
        
        return false;
    }
    
    /**
     * Devuelve el numero de cosas que hay en la habitacion
     * @return 
     */
    public int getNumeroCosas() { return _cosas.size();  }
    
    /**
     * Devuelve la cosa que esta e la posicion n de la lista de cosas de la room
     * @param i posicion de la cosa que queremos devolver
     * @return  objeto cosa
     */
    public Stuff getCosa(int i)
    {
        
        if (i<_cosas.size())
        {
            return _cosas.get(i);
        }
        else 
            return null;
        
    }
    
    /**
     * Elimina una cosa de la lista de cosas
     * @param i numero de orden de la cosa a eliminar
     */
    public void eliminaCosa(int i) { _cosas.remove(i); }
   
    
    /**
     * comprueba todas las puertas de la habitacion y las de las habotaciones de los lados para ver si coincide 
     * con la que se me pasa
     * @param puerta
     * @return true si encuntra la puerta,false en caso contrario
     */
    public boolean puertaAbrible(Door puerta)
    {
        for (int i=0;i<4;i++)
        {
            if (_puertas[i]==puerta)
                    return true;
        
        }
        
        return false;
    }
}
