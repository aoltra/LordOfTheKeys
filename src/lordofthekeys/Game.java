/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lordofthekeys;

import lordofthekeys.interfaces.IUsable;
import procesador.Command;
import procesador.CommandWords;
import procesador.Parser;

/**
 * Implementa el motor del juego
 * @author aoltra
 */
public class Game 
{
    private Parser _parser;
    private Map _mapa;
    private Actor _actor;
    
    
    public Game()
    {
        _parser = new Parser();
        creaMapa();
        _actor = new Actor(1,1);
    }
    
    /**
     * Creo las habitaciones del juego
     */
    private void creaMapa()
    {
        Room pasillo,departamento,banio;
        Room aula1,aula2;
        
        pasillo = new Room("pasillo",
                "es un bonito pasillo",
                true);
        
        departamento = new Room("Departamento",
                "Es una misteriosa sala",
                false);
       
        aula1 = new Room("Aula 1",
                "Aquí dan clase",
                false);
        
        aula2 = new Room("Aula 2",
                "Aquí dan clase",
                false);
        
        banio = new Room("Baño",
                "Aquí huele mal",
                false);
        
        _mapa = new Map();
        
        _mapa.plano[1][1] = pasillo;
        _mapa.plano[0][0] = _mapa.plano[0][2] = null;
        _mapa.plano[2][1] = _mapa.plano[2][2] = null;
        _mapa.plano[0][1] = banio;
        _mapa.plano[1][0] = aula1;
        _mapa.plano[1][2] = aula2;
        _mapa.plano[2][1] = departamento;
        
        // asignamos las puertas de las habitaciones
        Door puertaDepartamento = new Door(false,true);
        departamento.setDoor(Room.PuntosCardinales.NORTE, puertaDepartamento);
        
        Door puertaAula1 =  new Door(true,false);
        aula1.setDoor(Room.PuntosCardinales.ESTE, puertaAula1);
        
        Door puertaAula2 =  new Door(false,true);
        aula2.setDoor(Room.PuntosCardinales.OESTE, puertaAula2);
     
        Door puertaBanio =  new Door(false,false);
        banio.setDoor(Room.PuntosCardinales.SUR, puertaBanio);
     
        
        /// creamos las cosas del juego
        Key llave = new Key(puertaDepartamento);
        Chalk tiza = new Chalk();
        
        aula1.setCosa(tiza);
        aula2.setCosa(llave);
        
    }
    
     /**
     * Procesa los comandos, es decir, en funcion de lo
     * que se le dice lo "entiende" y ejecuta la orden
     * Se implementa en el juego ya que como se actue segun
     * que comando depende del juego, no del parser
     * @return true si la orden es para salir del juego
     */
    private boolean procesaComando(Command comando)
    {
        String verbo = comando.getVerbo();
        String sustantivo = comando.getSustantivo();
        String sustantivo2 = comando.getSustantivo2();
        
        // lo paso todo a minusculas
        verbo = verbo.toLowerCase();
        if (sustantivo != null)
            sustantivo = sustantivo.toLowerCase();
        
        if (sustantivo2 != null)
            sustantivo2 = sustantivo2.toLowerCase();
        
        
        // el usuario quiere salir
        if (verbo.equals("salir") == true )
        {
            System.out.println("Cagaoooo!!");
            return true;
        }
        
        if (verbo.equals("ayuda") == true )
        {
            CommandWords.muestraListaPalabras();
            return false;
        }
        
        if (verbo.equals("inventario") == true )
        {
            _actor.muestraInventario();
            return false;
        }
        
        if (verbo.equals("ir") == true )
        {
            
            // cojo la habitacion en la que estoy actualmente
            Room habitacionActual=_mapa.plano[_actor.getFila()]
                [_actor.getColumna()];
        
            // si no hay sustantivo no puede hacer nada
            if (sustantivo == null)    
            {
                System.out.println("Ir? si, pero a donde quieres i???");
            }
            else if (sustantivo2 != null)
            {
                System.out.println("Ein? mucha información. No te entiendo");
            }
            else if (sustantivo.equals("norte"))
            {
                if (_actor.getFila()==0)
                {
                    System.out.println("No puedo ir más al norte");
                }
                else if (_mapa.plano[_actor.getFila()-1][_actor.getColumna()] == null)
                {
                     System.out.println("No puedo.");
                }
                else if (comprueboPuertas(habitacionActual,Room.PuntosCardinales.NORTE) == Door.EstadosPuertas.CERRADA_CON_LLAVE ||
                        comprueboPuertas(habitacionActual,Room.PuntosCardinales.NORTE) == Door.EstadosPuertas.CERRADA_SIN_LLAVE)
                {
                     System.out.println("Hay una puerta cerrada.");
                }
                else
                {
                    Room habitacionNorte = _mapa.plano[_actor.getFila()-1]
                                    [_actor.getColumna()];
                        
                    if (comprueboPuertas(habitacionNorte,Room.PuntosCardinales.SUR) == Door.EstadosPuertas.CERRADA_CON_LLAVE ||
                        comprueboPuertas(habitacionNorte,Room.PuntosCardinales.SUR) == Door.EstadosPuertas.CERRADA_SIN_LLAVE)
                    {
                        System.out.println("Hay una puerta cerrada.");
                    }
                    else
                        _actor.setFila(_actor.getFila()-1);
                }
                    
            }
            else if (sustantivo.equals("sur"))
            {
                if (_actor.getFila() == Map.DIMENSION_V-1 )
                {
                    System.out.println("No puedo ir más al sur");
                }
                else if (_mapa.plano[_actor.getFila()+1][_actor.getColumna()] == null)
                {
                     System.out.println("No puedo.");
                }
                else if (comprueboPuertas(habitacionActual,Room.PuntosCardinales.SUR) == Door.EstadosPuertas.CERRADA_CON_LLAVE ||
                        comprueboPuertas(habitacionActual,Room.PuntosCardinales.SUR) == Door.EstadosPuertas.CERRADA_SIN_LLAVE)    
                {
                     System.out.println("Hay una puerta cerrada.");
                }
                else
                {
                    Room habitacionSur = _mapa.plano[_actor.getFila()+1]
                                    [_actor.getColumna()];
                    
                        
                    if (comprueboPuertas(habitacionSur,Room.PuntosCardinales.NORTE) == Door.EstadosPuertas.CERRADA_CON_LLAVE ||
                        comprueboPuertas(habitacionSur,Room.PuntosCardinales.NORTE) == Door.EstadosPuertas.CERRADA_SIN_LLAVE)    
                    {
                        System.out.println("Hay una puerta cerrada.");
                    }
                    else
                        _actor.setFila(_actor.getFila()+1);
                }
                             
            }
            else if (sustantivo.equals("este"))
            {
                if (_actor.getColumna() == Map.DIMENSION_H-1 )
                {
                    System.out.println("No puedo ir más al este");
                }
                else if (_mapa.plano[_actor.getFila()][_actor.getColumna()+1] == null)
                {
                     System.out.println("No puedo.");
                }
                else if (comprueboPuertas(habitacionActual,Room.PuntosCardinales.ESTE) == Door.EstadosPuertas.CERRADA_CON_LLAVE ||
                        comprueboPuertas(habitacionActual,Room.PuntosCardinales.ESTE) == Door.EstadosPuertas.CERRADA_SIN_LLAVE)    
                {
                     System.out.println("Hay una puerta cerrada.");
                }
                else
                {
                    Room habitacionEste = _mapa.plano[_actor.getFila()]
                                    [_actor.getColumna()+1];
                    
                    
                    if (comprueboPuertas(habitacionEste,Room.PuntosCardinales.OESTE) == Door.EstadosPuertas.CERRADA_CON_LLAVE ||
                        comprueboPuertas(habitacionEste,Room.PuntosCardinales.OESTE) == Door.EstadosPuertas.CERRADA_SIN_LLAVE)    
                    {
                        System.out.println("Hay una puerta cerrada.");
                    }
                    else
                        _actor.setColumna(_actor.getColumna()+1);
                }  
            }
            else if (sustantivo.equals("oeste"))
            {
                if (_actor.getColumna() == 0 )
                {
                    System.out.println("No puedo ir más al oeste");
                }
                else if (_mapa.plano[_actor.getFila()][_actor.getColumna()-1] == null)
                {
                     System.out.println("No puedo.");
                }
                if (comprueboPuertas(habitacionActual,Room.PuntosCardinales.OESTE) == Door.EstadosPuertas.CERRADA_CON_LLAVE ||
                    comprueboPuertas(habitacionActual,Room.PuntosCardinales.OESTE) == Door.EstadosPuertas.CERRADA_SIN_LLAVE)        
                {
                     System.out.println("Hay una puerta cerrada.");
                }
                else
                {
                    Room habitacionOeste = _mapa.plano[_actor.getFila()]
                                    [_actor.getColumna()-1];
                    
                    
                    if (comprueboPuertas(habitacionOeste,Room.PuntosCardinales.ESTE) == Door.EstadosPuertas.CERRADA_CON_LLAVE ||
                        comprueboPuertas(habitacionOeste,Room.PuntosCardinales.ESTE) == Door.EstadosPuertas.CERRADA_SIN_LLAVE)        
                    {
                        System.out.println("Hay una puerta cerrada.");
                    }
                    else
                        _actor.setColumna(_actor.getColumna()-1);
                }  
            }
            else
                System.out.println("Lo siento, no conozco esa dirección");
            
        }
        
        if (verbo.equals("ver") == true )
        {
            Room habitacion = _mapa.plano[_actor.getFila()][_actor.getColumna()]; 
            // muestra la descripcion
            System.out.println(habitacion.getDescripcion()); 
            // muestra la lista de cosas
            habitacion.muestraListaCosas();
        }
        
        if (verbo.equals("coger") == true )
        {
            Room habitacion = _mapa.plano[_actor.getFila()][_actor.getColumna()]; 

            if (habitacion.estaCosa(sustantivo)== true)   // si que esta en la habitacion
            {
                // compruebo si la cosa ademas es cogible
                if (habitacion.estaCosaCogible(sustantivo)== true) 
                {
                    Stuff cosa = _actor.coge(habitacion,sustantivo);
                    
                    if (cosa == null)
                    {
                        System.out.println("ERROR!! por aqui no deberia pasar nunca"); 
                    }
                    else
                    {
                        // por fin se la añadimos al jugador
                        _actor.addCosaInventario(cosa);
                    }
                }
                else
                    System.out.println("No se puede coger."); 
            }
            else
            {
                System.out.println("En esta habitación no hay " + sustantivo); 
            }
            
            return false;
        }
        
        if (verbo.equals("dejar") == true )
        {
            Room habitacion = _mapa.plano[_actor.getFila()][_actor.getColumna()]; 
            _actor.deja(sustantivo,habitacion);
        }
        
        if (verbo.equals("abrir") == true )
        {
            if (sustantivo == null)    
            {
                System.out.println("¿Que quieres abrir?");
            }
            else if (sustantivo.equals("puerta")== true) // con la puerta me hace falta otro sustantivo
            {
                if (sustantivo2 == null)    
                {
                    System.out.println("¿ La puerta de qué direccion ?");
                }
                else
                {
                    Room habitacion = _mapa.plano[_actor.getFila()][_actor.getColumna()]; 
                    if (sustantivo2.equals("norte") == true)
                    {
                        Door puerta = getPuerta(habitacion,Room.PuntosCardinales.NORTE);
                        
                        // tiene la limitacion de que no puedn haber puertas dobles
                        if (puerta!=null)   // si hay puerta la norte
                        {
                            if (comprueboPuertas(habitacion,Room.PuntosCardinales.NORTE) == Door.EstadosPuertas.CERRADA_CON_LLAVE)        
                            {
                                System.out.println("Esta cerrada con llave.");  
                            }
                            else if (comprueboPuertas(habitacion,Room.PuntosCardinales.NORTE) == Door.EstadosPuertas.ABIERTA)        
                            {
                                System.out.println("Ya esta abierta.");  
                            }
                            else 
                            {
                          
                                puerta.abrir();
                            }
                        }   
                        else  
                        {
                            
                            Room habitacionN = _mapa.plano[_actor.getFila()-1][_actor.getColumna()]; 
                            Door puertaS = getPuerta(habitacionN,Room.PuntosCardinales.SUR);

                            if (comprueboPuertas(habitacionN,Room.PuntosCardinales.SUR) == Door.EstadosPuertas.CERRADA_CON_LLAVE) 
                            {
                                System.out.println("Esta cerrada con llave.");  
                            }
                            else if (comprueboPuertas(habitacionN,Room.PuntosCardinales.SUR) == Door.EstadosPuertas.ABIERTA)        
                            {
                                System.out.println("Ya esta abierta.");  
                            }
                            else 
                            {

                                puertaS.abrir();
                            }  
                        }
                        
                    }
                    
                    if (sustantivo2.equals("sur") == true)
                    {
                        Door puerta = getPuerta(habitacion,Room.PuntosCardinales.SUR);
                        
                        // tiene la limitacion de que no puedn haber puertas dobles
                        if (puerta!=null)   // si hay puerta la norte
                        {
                            if (comprueboPuertas(habitacion,Room.PuntosCardinales.SUR) == Door.EstadosPuertas.CERRADA_CON_LLAVE)        
                            {
                                System.out.println("Esta cerrada con llave.");  
                            }
                            else if (comprueboPuertas(habitacion,Room.PuntosCardinales.SUR) == Door.EstadosPuertas.ABIERTA)        
                            {
                                System.out.println("Ya esta abierta.");  
                            }
                            else 
                            {
                          
                                puerta.abrir();
                            }
                        }   
                        else  
                        {
                            
                            Room habitacionS = _mapa.plano[_actor.getFila()+1][_actor.getColumna()]; 
                            Door puertaN = getPuerta(habitacionS,Room.PuntosCardinales.NORTE);

                            if (comprueboPuertas(habitacionS,Room.PuntosCardinales.NORTE) == Door.EstadosPuertas.CERRADA_CON_LLAVE) 
                            {
                                System.out.println("Esta cerrada con llave.");  
                            }
                            else if (comprueboPuertas(habitacionS,Room.PuntosCardinales.NORTE) == Door.EstadosPuertas.ABIERTA)        
                            {
                                System.out.println("Ya esta abierta.");  
                            }
                            else 
                            {

                                puertaN.abrir();
                            }  
                        }
                        
                    }
                    
                    
                    if (sustantivo2.equals("este") == true)
                    {
                        Door puerta = getPuerta(habitacion,Room.PuntosCardinales.ESTE);
                        
                        // tiene la limitacion de que no puedn haber puertas dobles
                        if (puerta!=null)   // si hay puerta la norte
                        {
                            if (comprueboPuertas(habitacion,Room.PuntosCardinales.ESTE) == Door.EstadosPuertas.CERRADA_CON_LLAVE)        
                            {
                                System.out.println("Esta cerrada con llave.");  
                            }
                            else if (comprueboPuertas(habitacion,Room.PuntosCardinales.ESTE) == Door.EstadosPuertas.ABIERTA)        
                            {
                                System.out.println("Ya esta abierta.");  
                            }
                            else 
                            {
                          
                                puerta.abrir();
                            }
                        }   
                        else  
                        {
                            
                            Room habitacionE = _mapa.plano[_actor.getFila()][_actor.getColumna()+1]; 
                            Door puertaE = getPuerta(habitacionE,Room.PuntosCardinales.OESTE);

                            if (comprueboPuertas(habitacionE,Room.PuntosCardinales.OESTE) == Door.EstadosPuertas.CERRADA_CON_LLAVE) 
                            {
                                System.out.println("Esta cerrada con llave.");  
                            }
                            else if (comprueboPuertas(habitacionE,Room.PuntosCardinales.OESTE) == Door.EstadosPuertas.ABIERTA)        
                            {
                                System.out.println("Ya esta abierta.");  
                            }
                            else 
                            {
                                puertaE.abrir();
                            }  
                        }
                       
                    }
                        


                    if (sustantivo2.equals("oeste") == true)
                    {
                        Door puerta = getPuerta(habitacion,Room.PuntosCardinales.OESTE);

                        // tiene la limitacion de que no puedn haber puertas dobles
                        if (puerta!=null)   // si hay puerta la norte
                        {
                            if (comprueboPuertas(habitacion,Room.PuntosCardinales.OESTE) == Door.EstadosPuertas.CERRADA_CON_LLAVE)        
                            {
                                System.out.println("Esta cerrada con llave.");  
                            }
                            else if (comprueboPuertas(habitacion,Room.PuntosCardinales.OESTE) == Door.EstadosPuertas.ABIERTA)        
                            {
                                System.out.println("Ya esta abierta.");  
                            }
                            else 
                            {

                                puerta.abrir();
                            }
                        }   
                        else  
                        {

                            Room habitacionO = _mapa.plano[_actor.getFila()][_actor.getColumna()+1]; 
                            Door puertaO = getPuerta(habitacionO,Room.PuntosCardinales.ESTE);

                            if (comprueboPuertas(habitacionO,Room.PuntosCardinales.ESTE) == Door.EstadosPuertas.CERRADA_CON_LLAVE) 
                            {
                                System.out.println("Esta cerrada con llave.");  
                            }
                            else if (comprueboPuertas(habitacionO,Room.PuntosCardinales.ESTE) == Door.EstadosPuertas.ABIERTA)        
                            {
                                System.out.println("Ya esta abierta.");  
                            }
                            else 
                            {
                                puertaO.abrir();
                            }  
                        }
                    }
                }   
            }
            
            if (verbo.equals("usar") == true )
            {
                Stuff cosa = _actor.getCosa(sustantivo2);
                
                if (cosa == null)
                {
                    System.out.println("No puedes usar cosas que no tienes: " + sustantivo2);
                }
                else if (cosa instanceof IUsable == false)
                {
                    System.out.println("No se puede usar");
                }
                else
                {
                    // compruebo si esa cosa la puedo usar con algo de la habitacion en la que estoy
                    Room habitacion = _mapa.plano[_actor.getFila()][_actor.getColumna()]; 
                     
                    // se usa con el objeto que sea que no sea una puerta
                    if (habitacion.estaCosa(cosa.getUtilizable()) == true)
                    {
                        ((IUsable)cosa).usar();
                    }
                    else
                    {
                        // compruebo las puertas, tanto las de la habotacion con las de la habitaciones de alrdedor
                        if (cosa.getUtilizable() instanceof Door)   // si es una puerta
                        {
                            if (habitacion.puertaAbrible((Door)cosa.getUtilizable()))
                            {
                            
                            
                            }
                            else
                                System.out.println("No se puede usar");            
                        }
                        
                        
                    }
                
                
                
                }
                
            }
            
        }
        
        return false;
        
    }
    
    /**
     * Pone en marcha el juego
     */
    public void run()
    {
        boolean salida;
        
        imprimeBienvenida();
        
        // bucle principal del juego
        while (true)
        {
            imprimeUbicacion();
            
            Command comando = _parser.getComando();
            
            if (comando.getVerbo() == null)
            {
                System.out.println("Lo siento, no te entiendo.");
                continue;
            }
            
            
            salida = procesaComando(comando);
            if (salida == true) break;
        }
        
        System.out.println("Adios.");
    }
    
   
    
    private void imprimeBienvenida()
    {
        System.out.println("Bienvenido a Lord Of The Keys");
        System.out.println("");
        System.out.println("Este es un maravilloso juego que te hará");
        System.out.println("disfrutar de una apasionante aventura");
        System.out.println("en el IES Camp");
        System.out.println("Tu objetivo es conseguir la llave para abrir");
        System.out.println("el cajon donde están los exámenes.");
        System.out.println("");
    }
    
    /**
     * imprime la ubicación en la que se encuentra el actor
     */
    private void imprimeUbicacion()
    {
        System.out.println("Estas en " +
            _mapa.plano[_actor.getFila()]
                [_actor.getColumna()].getNombre());
    }
    
    
    /**
     * Comprueba si las puerta me dejan pasar o no
     * @return true si puedo pasar y false si NO puedo pasar
     */
    /*private boolean comprueboPuertas(Room habitacion, Room.PuntosCardinales direccion)
    {
        Door puerta = habitacion.getDoor(direccion);

        if (puerta!=null)
        {
            // hay puerta, asi que comprobamos si esta abierta
            if (puerta.estaAbierto() == false) // esta cerrada
            {
                return false;
            }
            else
               return true;     
        }
        
        
        return true;
    }
    */
    
    
    private Door.EstadosPuertas comprueboPuertas(Room habitacion, Room.PuntosCardinales direccion)
    {
        Door puerta = habitacion.getDoor(direccion);
        
        if (puerta!=null)
        {
            // hay puerta, asi que comprobamos si esta abierta
            if (puerta.estaAbierto() == true) // esta abierta
            {
                return Door.EstadosPuertas.ABIERTA;
            }
            else    // esta cerrada
            {
                if (puerta.getConLlave() == true) return Door.EstadosPuertas.CERRADA_CON_LLAVE;
                else return Door.EstadosPuertas.CERRADA_SIN_LLAVE;
            }
                 
        }
        
        return Door.EstadosPuertas.SINPUERTA;
    }
    
    
    /**
     * Devuelve una puerta de un habitacion en una direccion
     * @param habitacion
     * @param direccion
     * @return 
     */
    private Door getPuerta(Room habitacion, Room.PuntosCardinales direccion)
    {
        return habitacion.getDoor(direccion);
    }
}


