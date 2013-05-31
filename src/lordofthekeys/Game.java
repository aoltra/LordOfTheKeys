/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lordofthekeys;

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
        Key llave = new Key();
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
        
        // lo paso todo a minusculas
        verbo = verbo.toLowerCase();
        if (sustantivo != null)
            sustantivo = sustantivo.toLowerCase();
        
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
                else if (comprueboPuertas(habitacionActual,Room.PuntosCardinales.NORTE)==false)
                {
                     System.out.println("Hay una puerta cerrada.");
                }
                else
                {
                    Room habitacionNorte = _mapa.plano[_actor.getFila()-1]
                                    [_actor.getColumna()];
                    
                    if (comprueboPuertas(habitacionNorte,Room.PuntosCardinales.SUR)==false)
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
                else if (comprueboPuertas(habitacionActual,Room.PuntosCardinales.SUR)==false)
                {
                     System.out.println("Hay una puerta cerrada.");
                }
                else
                {
                    Room habitacionSur = _mapa.plano[_actor.getFila()+1]
                                    [_actor.getColumna()];
                    
                    if (comprueboPuertas(habitacionSur,Room.PuntosCardinales.NORTE)==false)
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
                else if (comprueboPuertas(habitacionActual,Room.PuntosCardinales.ESTE)==false)
                {
                     System.out.println("Hay una puerta cerrada.");
                }
                else
                {
                    Room habitacionEste = _mapa.plano[_actor.getFila()]
                                    [_actor.getColumna()+1];
                    
                    if (comprueboPuertas(habitacionEste,Room.PuntosCardinales.OESTE)==false)
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
                 else if (comprueboPuertas(habitacionActual,Room.PuntosCardinales.OESTE)==false)
                {
                     System.out.println("Hay una puerta cerrada.");
                }
                else
                {
                    Room habitacionOeste = _mapa.plano[_actor.getFila()]
                                    [_actor.getColumna()-1];
                    
                    if (comprueboPuertas(habitacionOeste,Room.PuntosCardinales.ESTE)==false)
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
    private boolean comprueboPuertas(Room habitacion, Room.PuntosCardinales direccion)
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
    
    
}


