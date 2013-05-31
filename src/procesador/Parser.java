/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package procesador;

import java.util.Scanner;


/**
 * Se encarga de procesar los comandos
 * @author aoltra
 */
public class Parser 
{
    private Command _comando; 
    private Scanner _lector;        // clase que se 
                                    // encarga de leer del teclado
    
    public Parser()
    {
        _lector = new Scanner(System.in);
    
    }
    
    /**
     * coge la frase que el usuario escribe
     * y crea el objeto Command
     * @return 
     */
    public Command getComando()
    {
        String entradaUsuario,palabra1 = null,palabra2 = null, palabra3 = null;
        
        System.out.print(" > ");
        
        entradaUsuario = _lector.nextLine();
        
        Scanner  generadorDeToken = new Scanner(entradaUsuario);
        
        // por defecto el separador es el espacio
        if (generadorDeToken.hasNext())
        {
            palabra1 = generadorDeToken.next();
            if (generadorDeToken.hasNext())
            {
                palabra2 = generadorDeToken.next();
                
                if (generadorDeToken.hasNext())
                {
                    palabra3 = generadorDeToken.next();
                }
            }
        }
        
        if (palabra1!= null)
        {
            // si la palabra1 es valida
            if (CommandWords.esValida(palabra1))
            {
                if (palabra2 == null) // si no hay palabra2
                    return new Command(palabra1);
                 // si si que hay palabra2 compruebo que sea valida
                else if (CommandWords.esValida(palabra2)) 
                {
                    if (palabra3 == null) // si no hay palabra3
                        return new Command(palabra1,palabra2);
                    else if (CommandWords.esValida(palabra3)) 
                        return new Command(palabra1,palabra2,palabra3);            
                }

            }
        }
        
        // el comando no es valida, ya todo el comando es incorrecto  
        return new Command(null,null,null); 
            
    }
    
}
