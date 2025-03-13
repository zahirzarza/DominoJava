package Jugadores;
/**
 *
 *
 *@author Axel Zarza
 *@author Diego
 *
 *Esta clase se extiende de "Jugador" y aqui redifinimos los metodos en el que interactua el usuario,p por metodos donde todo lo haga el propio programa
  */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import Fichas.*;


public class JugadorCpu extends Jugador implements Serializable{
   
    
        //Atributos
  private String nombre;
  private int numFichas;
  private ArrayList<FichaDomino> fichasEnMano;
  public boolean puedeTirar;
  
  //Constructor
  public JugadorCpu(String nombre) {
      super(nombre);
      super.tiro = false;
  }

  //Se redefinen los metodos donde el usuario accede datos

  /**
  *
  *se redefine el metodo para escoger una ficha remplazando el scanner por un random
  *
  */
    @Override
    public FichaDomino EscogerFicha() {
    FichaDomino ficha;
    int posicion;
    Random rd = new Random();
    do{
    posicion = rd.nextInt(super.tmList());
    }while( !(-1 < posicion && posicion < super.tmList()));
    ficha = EscogerFicha(posicion);
    //System.out.println(this.getNombre() + " Bot Escogio la posicion de la ficha que desea tirar: " + posicion);
    return ficha;
  }

    public int escogerlado(){
    Random rd = new Random();
    int lado = rd.nextInt(-2,2);
    return lado;
  }

  
  public void ponerFichaEnMesa(FichaDomino ficha){
    super.ficha= ficha;
  }

  public void retLadoEsc(int lado){
    super.escLado = lado;
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
      if(super.puedeTirar == true){
        super.tiro = true;
      if(super.fichaCompatible(ladoI,ladoF)==true){
        FichaDomino ficha;
        do{

          ficha = this.EscogerFicha();
           //comprober si se escogio la ficha corecta, metodo en ficha
        }while(!ficha.esPonible(ladoI,ladoF));
        super.eliminarFicha(ficha);
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
        super.ponerFichaEnMesa(ficha);
        super.retLadoEsc(lado);
        super.tiro = false;
        detener = false;
        super.pudColoc = true;
      }else{
        detener = false;
      }
    }else{
      detener = false;
    }
    }
    
  
}



}

