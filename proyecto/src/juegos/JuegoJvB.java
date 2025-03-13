package Juegos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 *
 *@author Axel Zarza
 *@author Diego Varela
*@author Emilio Perez
 *la clase "Juego1v1" es una variante del juego de dominó que permite a un jugador humano competir contra una inteligencia artificial (bot) y determinar al ganador al final del juego.
  */
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import Fichas.*;
import Jugadores.*;



public class JuegoJvB {

  //atributos
  private Jugador jugador1;
  private Jugador jugador2;

  private ArrayList<FichaDomino> fichasEnStack;
  private ArrayList<FichaDomino> fichasEnMesa;

  private String nombreGanador; // Variable de instancia para almacenar el nombre del ganador
  //constructor
  public JuegoJvB(String name1, String name2){

    jugador1 = new Jugador(name1);
    jugador2 = new JugadorCpu(name2);

    fichasEnStack = new ArrayList<>();   
    fichasEnMesa = new ArrayList<>();
  }
  
  //metodos

  //Creacion de las ficas
  public void crearFichas(){
    int cont = 0;
    //creacion de fichas
    FichaDominoBuilder fichaDominoBuilder= new FichaDominoBuilder();
    
    for(int i = 0; i <= 6; i++){
      for(int j = cont ;j<=6; j++){
        FichaDomino ficha;
        if(i==j){
          ficha=fichaDominoBuilder
          .setLado1(i)
          .setLado2(j)
          .setEsDoble(true)
          .build();
      }
        else{
          ficha=fichaDominoBuilder
          .setLado1(i)
          .setLado2(j)
          .setEsDoble(false)
          .build();
        }
      fichasEnStack.add(ficha);
    }
    cont++;
  }
  }
  //Reapartir las fichas iniciales
  public void repartirFichas(){
    
    Random rd = new Random();
    
    
    for(int i = 0; i < 7; i++){
      
      int maxF = fichasEnStack.size();

      int n = rd.nextInt(maxF);
      FichaDomino ficha = fichasEnStack.get(n);
      jugador1.recibirFicha(ficha);
      fichasEnStack.remove(n);
      
      maxF = fichasEnStack.size();
      int m = rd.nextInt(maxF);
      FichaDomino ficha2 = fichasEnStack.get(m);
      jugador2.recibirFicha(ficha2);
      fichasEnStack.remove(m);  
      
    }

  } 

  //Tomar fichas del stak
  public void tomarFicha(Jugador jugador){
    int ladoI = fichasEnMesa.get(0).getLado1();
    int ladoF = fichasEnMesa.get(fichasEnMesa.size()-1).getLado2();

    Random rd = new Random();
    while(!jugador.fichaCompatible(ladoI, ladoF)){
      int n = rd.nextInt(fichasEnStack.size());
      jugador.recibirFicha(fichasEnStack.get(n));
      System.out.println("El jugador: " + jugador.getNombre() + " tomo una ficha");
      fichasEnStack.remove(n);
    }
  }

  

  
  public void turnoJugador(Jugador jugador1, Jugador jugador2){
    if(jugador1.puedeTirar == true){
      jugador1.puedeTirar = false;
      jugador2.puedeTirar = true;
      System.out.println("Turno del jugador: " + jugador2.getNombre() );
    }
    else{
      jugador1.puedeTirar = true;
      jugador2.puedeTirar = false;
      System.out.println("Turno del jugador: " + jugador1.getNombre() );
    }
  }

  
  public void colocarFichaEnMesa(FichaDomino ficha, int lado){
    if(lado < 0){
      fichasEnMesa.add(0,ficha);
    }
    else{
      fichasEnMesa.add(ficha);
    }
  }

  public int SeleccionTipo(){
    int tipo = 0;
    Scanner sc = new Scanner(System.in);
    System.out.println("Seleccione el modo de juego: ");
    System.out.println("1. jugador - jugador");
    System.out.println("2. jugador - bot");
    System.out.println("3. bot - bot");
    do{
    tipo = sc.nextInt();
    }while(tipo >= 1 || tipo <= 3);
    return tipo;
  }

  /**
  *
  * @param jugador : nos sirve para identificar al jugador
  *
  *
  * Este metodo nos sirve para realizar el tiro que va a ejecutar el jugador que recibimos como parametro
  */
  

  public void tiro(Jugador jugador){
    int ladoI = fichasEnMesa.get(0).getLado1();
    int ladoF = fichasEnMesa.get(fichasEnMesa.size()-1).getLado2();

    if(jugador.puedeTirar == true){
      if(jugador.fichaCompatible(ladoI,ladoF)==true){
        FichaDomino ficha;
        do{
          ficha = jugador.EscogerFicha();
           //comprober si se escogio la ficha corecta, metodo en ficha
        }while(!ficha.esPonible(ladoI,ladoF));
        jugador.eliminarFicha(ficha);
        int lado;
        if(ficha.escogerLado(ladoI, ladoF)){
          lado = jugador.escogerlado();
          ficha = ficha.girar(ladoI, ladoF, lado);
        }else{
         lado = ficha.eslado(ladoI, ladoF);
          ficha = ficha.girar(ladoI, ladoF, lado);
        }
        //determinar si es nesesario que se escoga el lado,
        //si si esta la posibilidad, ver si es necesario girar la ficha
        this.colocarFichaEnMesa(ficha, lado);
      }

      else if(fichasEnStack.size()==0){
          System.out.println("No hay fichas en la pila, Paso");
      
      }else{
        this.tomarFicha(jugador);
        System.out.println(jugador);
        this.tiro(jugador);
      }
    }
  }

  public void ganoSinFichas(){
    if(!jugador1.tieneFichas()){
      nombreGanador = jugador1.getNombre();
      System.out.println("El ganador es: " + jugador1.getNombre()+ " gano por ya no tener mas fichas :O");
    }else{
      nombreGanador = jugador2.getNombre();
      System.out.println("El ganador es: " + jugador2.getNombre()+ " gano por ya no tener mas fichas :O");
    }
    guardarGanadorEnArchivo(nombreGanador);
  }
  
  public void quienGano(){
    if(!jugador1.tieneFichas() || !jugador2.tieneFichas()){
      this.ganoSinFichas();
    }
    else{
      this.ganoPorPuntos();
    }
  }
  
  private void guardarGanadorEnArchivo(String nombreGanador) {
    try {
        // Crear un objeto FileWriter sin especificar la ruta completa
        FileWriter fileWriter = new FileWriter("JuegoJvB.txt", true);

        // Crear un objeto BufferedWriter para escribir en el archivo
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        // Escribir el nombre del ganador en el archivo
        bufferedWriter.write(nombreGanador);
        bufferedWriter.newLine(); // Agregar un salto de línea para separar los nombres

        // Cerrar el BufferedWriter para liberar recursos
        bufferedWriter.close();
    } catch (IOException e) {
        e.printStackTrace(); // Manejo de excepciones
    }
}
  
  public void ganoPorPuntos(){
    if(jugador1.puntos() > jugador2.puntos()){
      nombreGanador = jugador1.getNombre();
      System.out.println("El ganador es: " + jugador1.getNombre()+ " gano por puntos :O");
    }
    else if(jugador2.puntos() > jugador1.puntos()){
      nombreGanador = jugador2.getNombre();
      System.out.println("El ganador es: " + jugador2.getNombre()+ " gano por puntos :O");
    }
    else{
      nombreGanador = "Empate";
      System.out.println(nombreGanador);
    }
  }
  

  public boolean sigueJuego(){
    int ladoI = fichasEnMesa.get(0).getLado1();
    int ladoF = fichasEnMesa.get(fichasEnMesa.size()-1).getLado2();
    //Determinar si se sigue el juego
    if((this.fichasEnStack.size() == 0 && !this.jugador1.fichaCompatible(ladoI,ladoF) && !jugador2.fichaCompatible(ladoI,ladoF)) || (!this.jugador1.tieneFichas() || !jugador2.tieneFichas()) ){
      return false;
    //Todavia puedan poner fichas o que ya no haya fichas
  }else{
      return true;
    }
  
  }

  public void printFichasEnMesa(){
    System.out.println("Fichas en la mesa: ");
    fichasEnMesa.forEach(elemento ->     System.out.print(elemento.toString() + ", "));
    System.out.println("\n");
  }



  //Asignar el orden de tiro
  public void PrimerTiro(Jugador jugador, Jugador jugador2){

    if(jugador.mulaMayor() > jugador2.mulaMayor()){
      jugador.puedeTirar = true;
      jugador2.puedeTirar = false;
      System.out.println("Jugador: " + jugador.getNombre() + ", va primero" );

      FichaDomino ficha = jugador.EscogerFicha(jugador.posMula());
      jugador.borrarFicha(jugador.posMula());
      this.colocarFichaEnMesa(ficha, 1);
      this.printFichasEnMesa();
    }
    else if(jugador.mulaMayor() < jugador2.mulaMayor()){
      jugador.puedeTirar = false;
      jugador2.puedeTirar = true;
      System.out.println("Jugador: " + jugador2.getNombre() + ", va primero" );

      FichaDomino ficha = jugador2.EscogerFicha(jugador2.posMula());
      jugador2.borrarFicha(jugador2.posMula());
      this.colocarFichaEnMesa(ficha, 1);
      this.printFichasEnMesa();
    }else{
      Random rd = new Random();
      int r = rd.nextInt(2);
      System.out.println("Elegido al azar");
      if(r==0){
        jugador.puedeTirar = true;
        jugador2.puedeTirar = false;
        System.out.println("Jugador: " + jugador.getNombre() + ", va primero" );

        this.tiro(jugador);
        this.printFichasEnMesa();
      }else{
       jugador.puedeTirar = false;
       jugador2.puedeTirar = true;
        System.out.println("Jugador: " + jugador2.getNombre() + ", va primero");
        this.tiro(jugador2);
        this.printFichasEnMesa();
      }
    }

  }

  public void girarfichas(){

    Random rd = new Random();
    int max = fichasEnStack.size();
    int limit = rd.nextInt(max);

    for (int i = 0; i < limit; i++) {
      int n = rd.nextInt(max);
      FichaDomino ficha = fichasEnStack.get(n);
      fichasEnStack.remove(n);
      ficha = ficha.seGiraFD();
      fichasEnStack.add(ficha);
    }
    
  }

  public void sinFichas(Jugador jugador){
    if(fichasEnStack.size()==0){
        System.out.println("No hay fichas en la pila, Paso");

    }else{
      this.tomarFicha(jugador);
      System.out.println(jugador);
      //this.tiro(jugador);
    }
  }

  //Metodo para jugar
  public void jugar(){
    this.crearFichas();
    this.girarfichas();
    this.repartirFichas();
    System.out.println(jugador1);
    System.out.println(jugador2);
    this.PrimerTiro(jugador1, jugador2);

    // Llama a los métodos del juego
    while(this.sigueJuego()){
      

      System.out.println(jugador1);
      System.out.println(jugador2);
      this.printFichasEnMesa();
      this.turnoJugador(jugador1,jugador2);

      /* 
      this.tiro(jugador1);
      this.tiro(jugador2);
      */

      do{
        jugador1.run(fichasEnMesa);
        if(!jugador1.pudColoc && jugador1.tiro){
          this.sinFichas(jugador1);
        }        
      }while(jugador1.tiro);
      if(jugador1.pudColoc){
        this.colocarFichaEnMesa(jugador1.ficha, jugador1.escLado);
        jugador1.pudColoc = false;
      }
      

      do{
        jugador2.run(fichasEnMesa);
        if(!jugador2.pudColoc && jugador2.tiro){
          this.sinFichas(jugador2);
        }        
      }while(jugador2.tiro);
      if(jugador2.pudColoc){
      this.colocarFichaEnMesa(jugador2.ficha, jugador2.escLado);
      jugador2.pudColoc = false;
      }

      try {
        jugador1.join();
        jugador2.join();
      }catch(Exception e){
        e.getMessage();
      }


      if(jugador2.puedeTirar){
        System.out.println("\nTermino el tiro de:" + jugador2 + "\n");

        System.out.println("=================================");
      }
    }
    this.printFichasEnMesa();
    System.out.println("Fin del juego");
    this.quienGano();
  }
  
}
