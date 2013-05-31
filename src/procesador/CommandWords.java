/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package procesador;

/**
 * Diccionario
 * @author aoltra
 */
public class CommandWords 
{
     private static final String [] palabrasValidas =
     { "ir", "salir", "coger", "ayuda", "norte", "sur",
       "este", "oeste", "ver", "tiza", "llave", "dejar",
       "inventario"};
     
   //  public CommandWords() {}
     
     /**
      * Comprueba si una palabra pertenece al diccionario
      * @param palabra
      * @return 
      */
     public static boolean esValida(String palabra)
     {
         for (int i = 0; i<palabrasValidas.length;i++)
         {
             if (palabrasValidas[i].equals(palabra.toLowerCase()))
                 return true;
         
         }
         
         return false;
     }
     
     /**
      * muestra toda la lista de palabras disponibles
      */
     public static void muestraListaPalabras()
     {
         for (int i = 0; i<palabrasValidas.length;i++)
         {
            if (i==palabrasValidas.length-1)
                System.out.println(palabrasValidas[i] + ".");
            else
                System.out.print(palabrasValidas[i] +", ");
         }
         
     
     }
     
}
