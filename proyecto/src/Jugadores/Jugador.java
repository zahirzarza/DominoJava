package Jugadores;

import java.io.Serializable;
/**
 *
 *
 *@author Axel Zarza
 *@author Diego Varela
*@author Emilio Perez
 *
 *
 *ESta clase "Jugador" representa a un jugador controlado por el usuario en el juego de dominó. Los métodos de la clase permiten gestionar las fichas en la mano del jugador y tomar decisiones durante el juego.
 *Tambien es la clase que es un hilo
 */

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import Fichas.*;

//Clase del jugador controlado por el usuario


public class Jugador extends Thread implements elJugador, Serializable { 
  
  //Atributos
  private String nombre;
  private int numFichas;
  private ArrayList<FichaDomino> fichasEnMano;
  public boolean puedeTirar;

  public boolean tiro;
  public FichaDomino ficha;
  public int escLado;
  public boolean pudColoc;
  
  //Constructor
  public Jugador(String nombre) {
      this.nombre = nombre;
      this.fichasEnMano = new ArrayList<>();
      this.tiro = false;
      this.pudColoc=false;
  }
  
  //Métodos 
  public String getNombre() {
    return nombre;
  } 

  public int getNumFichas() {
    return numFichas;
  }

  /**
  *
  *@return el valor de la mula mayor
  *
  */

  public int mulaMayor(){
    int mayor = -1;
    for (FichaDomino fich : fichasEnMano) {
      if(fich.getLado1() == fich.getLado2()) {
        if(fich.getLado1() > mayor) {
          mayor = fich.getLado1();
        }
      }
    }
    return mayor;
  }
  /**
  *
  *@return la posicion de la mula mayor
  *
  */

  public int posMula(){
    int mayor = -1;
    int pos = 0;
    int sMay = this.mulaMayor();
    for (FichaDomino fich : fichasEnMano) {
      if(fich.getLado1() == fich.getLado2()) {
        if(fich.getLado1() == sMay){
          return pos;
        }
      }
          pos++;

    }
    return pos+1;
  }
  
  public void recibirFicha(FichaDomino ficha) {
      fichasEnMano.add(ficha);
      this.numFichas++;
  }
  public void eliminarFicha(FichaDomino ficha) {
    fichasEnMano.remove(ficha);
    this.numFichas--;
  }

  public FichaDomino EscogerFicha() {
    FichaDomino ficha;
    int posicion = 0;
    boolean error = false;
    
    do{
      do{
        try {
          error = false;
          Scanner sc = new Scanner(System.in);
          System.out.println("Escoja la posicion de la ficha que desea tirar: ");
          posicion = sc.nextInt();
        } catch (InputMismatchException e) {
          error = true;
          System.out.println("No son validos los caracteres // " + e.getMessage());
        }
      }while(error);
    }while( !(-1 < posicion && posicion < fichasEnMano.size()));
    ficha = fichasEnMano.get(posicion);
    //fichasEnMano.remove(posicion);
    return ficha;
  }

  public int escogerlado(){
    Scanner sc = new Scanner(System.in);
    System.out.println("Escoja el lado de la ficha que desea tirar: ");
    System.out.println("Izquierda [-1] o Derecha [1]");
    int lado = sc.nextInt();
    return lado;
  }

  /**
  *
  *@param lado1 - valor del lado izquierdo de la primera ficha
  *@param lado2 - valor del lado derecho de la ultima ficha
  *@return si la ficha puede colocarse en la mesa
  *
  */
  
  public boolean fichaCompatible(int lado1, int lado2){
    for (FichaDomino fich : fichasEnMano) {
      if(fich.getLado1() == lado1 || fich.getLado1()==lado2) {
        return true;
      }
      else if(fich.getLado2() == lado1 || fich.getLado2()==lado2){
        return true;
      }
    }
    return false;
  }
  
  
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public FichaDomino EscogerFicha(int pos){
    return fichasEnMano.get(pos);
  }

  public void borrarFicha(int pos){
    fichasEnMano.remove(pos);
    this.numFichas--;
  }

  public boolean tieneFichas(){
    if(fichasEnMano.size() == 0){
      return false;
    }
    return true;
  }

  public int puntos(){
    int puntos = 0;
    for (FichaDomino fich : fichasEnMano) {
      puntos += fich.getPuntos();
    }
    return puntos;
  }

  public int tmList(){
    return this.fichasEnMano.size();
  }
  
  //Método toString
  /**
  *
  *se redifine el metodo que devuelve al mandarlo imprimir
  *
  */
  @Override
  public String toString(){
    String listFichas = "";
    for ( FichaDomino elemento : fichasEnMano) {
        listFichas += elemento.toString() + ", ";
    }
    return "Jugador: " + nombre + "\n " + listFichas + "\n";
  }

public void recibirFicha(List<FichaDomino> fichasJugador1) {
}


public void ponerFichaEnMesa(FichaDomino ficha){
  this.ficha= ficha;
}

public void retLadoEsc(int lado){
  this.escLado = lado;
}

 //Método run()
  /**
  *
  *@param fichasEnMesa resive este parametro para poder determinar si puede tirar
  *se utiliza para hacer el tiro de cada ficha
  *
  */


public void run(ArrayList<FichaDomino> fichasEnMesa){
  int ladoI = fichasEnMesa.get(0).getLado1();
  int ladoF = fichasEnMesa.get(fichasEnMesa.size()-1).getLado2();

  boolean detener = true;
  
  while (detener) {
    if(this.puedeTirar == true){
      this.tiro = true;
    if(this.fichaCompatible(ladoI,ladoF)==true){
      FichaDomino ficha;
      do{

        ficha = this.EscogerFicha();
         //comprober si se escogio la ficha corecta, metodo en ficha
      }while(!ficha.esPonible(ladoI,ladoF));
      this.eliminarFicha(ficha);
      int lado;
      if(ficha.escogerLado(ladoI, ladoF)){
        lado = this.escogerlado();
        ficha = ficha.girar(ladoI, ladoF, lado);
      }else{
       lado = ficha.eslado(ladoI, ladoF);
        ficha = ficha.girar(ladoI, ladoF, lado);
      }
      //determinar si es nesesario que se escoga el lado,
      //si si esta la posibilidad, ver si es necesario girar la ficha
      this.ponerFichaEnMesa(ficha);
      this.retLadoEsc(lado);
      this.tiro = false;
      detener = false;
      this.pudColoc = true;
    }else{
      detener = false;
    }
  }else{
    detener = false;
  }
  }
  

}

}

