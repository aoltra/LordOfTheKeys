/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lordofthekeys;

/**
 *
 * @author aoltra
 */
public class Map {
 
    public final static int DIMENSION_H = 3;
    public final static int DIMENSION_V = 3;
    
    public Room plano [][];
    
    public Map()
    {
        plano = new Room[DIMENSION_H][DIMENSION_V];
        
    }
}
