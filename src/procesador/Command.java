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
    
    public Command(String verbo,String sustantivo)
    {
        _verbo = verbo;
        _sustantivo = sustantivo;
    }
    
    public Command(String verbo)
    {
        _verbo = verbo;
        _sustantivo = null;
    }
    
    public boolean tieneSustantivo()
    {
        // si sustantivo es distinto de null hay sustantivo
        if (_sustantivo != null)
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
}
