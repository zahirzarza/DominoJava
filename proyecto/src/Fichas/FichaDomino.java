package Fichas;

import java.io.Serializable;

/**
 *
 *
 *@author Axel Zarza
 *@author Diego
 * la clase "FichaDomino" representa una ficha de dominó con características específicas y métodos para manipular y evaluar la ficha en el contexto de un juego de dominó.
  */
//Clase de la ficha

public class FichaDomino extends FichaAbstracta implements Serializable {
  
  //Atributos 
  private boolean isDoble;
  
  //Constructor
  public FichaDomino(int lado1, int lado2, boolean esDoble){
    super(lado1, lado2);
    this.isDoble = esDoble;
  }

  //Getters y Setters
  public boolean getDoble(){
    return this.isDoble;
  }

  //Métodos abstractos
  public String tipo(){
    return "Ficha";
  }

  public int getPuntos(){
      return this.getLado1() + this.getLado2();
        }

  public boolean esPonible(int ladoI, int ladoF){
    if(this.getLado1() == ladoI || this.getLado1()==ladoF) {
      return true;
    }
    else if(this.getLado2() == ladoI|| this.getLado2()==ladoF){
      return true;
    }else{
      return false;
    }
  }

  public boolean escogerLado(int ladoI,int ladoF){
    if((this.getLado1() == ladoI || this.getLado2()==ladoI ) && (this.getLado2() == ladoF ||  this.getLado1()==ladoF)){
      return true;
    }else{
      return false;
    }
  }

  public int eslado(int ladoI, int ladoF){
    if((this.getLado1() == ladoI || this.getLado2()==ladoI )){
      return -1;
    }else{
      return 1;
    }
  }
  


  public FichaDomino girar(int ladoI,int ladoF, int ladoPos){
    if(ladoPos<0){
      if (this.getLado1() == ladoI) {
        FichaDomino fich = new FichaDomino(this.getLado2(), this.getLado1(), this.isDoble);
      return fich;
      }else{
      return this;
      }
    }else{
      if(this.getLado2() == ladoF){
        FichaDomino fich = new FichaDomino(this.getLado2(), this.getLado1(), this.isDoble);
      return fich;
      }else{
      return this;
      }
    }
  }

  public FichaDomino seGiraFD(){
    int a,b;
    a = super.getLado1();
    b = super.getLado2();

    this.setLado1(b);
    this.setLado2(a);

    return this;
  }



  @Override
  public String toString(){
    return super.toString();
  }
  
}
