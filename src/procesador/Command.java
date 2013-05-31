/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package procesador;

/**
 * Clase que encapsula las acciones que el juego puede realizar
 * cada comando puede consistir en dos palabras
 * un verbo + un sustantivo
 * por ejemplo: ir derecha, coger llave
 * @author aoltra
 */
public class Command {
    
    private String _verbo;
    private String _sustantivo;
    private String _sustantivo2;
    
    /** 
     * Constructor para dar soporte a comandos de tres palabras
     * @param verbo
     * @param sustantivo
     * @param sustantivo2 
     */
    public Command(String verbo,String sustantivo, String sustantivo2)
    {
        _verbo = verbo;
        _sustantivo = sustantivo;
        _sustantivo2 = sustantivo2;
    }
    
    public Command(String verbo,String sustantivo)
    {
        _verbo = verbo;
        _sustantivo = sustantivo;
        _sustantivo2 = null;
    }
    
    public Command(String verbo)
    {
        _verbo = verbo;
        _sustantivo = null;
        _sustantivo2 = null;
    }
    
    public boolean tieneSustantivo()
    {
        // si sustantivo es distinto de null hay sustantivo
        if (_sustantivo != null)
            return true;
        
        return false;
    }
    
    public boolean tieneSustantivo2()
    {
        // si sustantivo2 es distinto de null hay sustantivo2
        if (_sustantivo2 != null)
            return true;
        
        return false;
    }
    
    // devuelve el verbo del comando
    public String getVerbo()
    {
        return _verbo;
    }
    
    // devuelve el sustantivo del comando
    public String getSustantivo()
    {
        return _sustantivo;
    }
    
    // devuelve el sustantivo2 del comando
    public String getSustantivo2()
    {
        return _sustantivo2;
    }
}
