/**
 *
 *
 *@author Axel Zarza
 *@author Diego Varela
  *@author Emilio Perez
 *la clase abstracta "FichaAbstracta" define una estructura base para representar fichas de dominó, y las clases derivadas deben implementar los detalles específicos de cada tipo de ficha.
  */
package Fichas;

import java.io.Serializable;

//Clase abstracta de Fichas
public abstract class FichaAbstracta implements Serializable  {
  
  //Atributos 
  private int lado1;
  private int lado2;
  
  //Constructor   
  public FichaAbstracta(int lado1, int lado2) {
    this.lado1 = lado1;
    this.lado2 = lado2;
  }

  //Getters y Setters 
  public int getLado1() {  
  return lado1;
    }
  public void setLado1(int lado1) {
    this.lado1 = lado1;
  }
  public int getLado2() {
    return lado2;
  }
  public void setLado2(int lado2) {
    this.lado2 = lado2;
  }
  
  
  //Método que devuelve la ficha en forma de String (ej  
  public String toString(){
    return "[" + this.lado1 + "|" + this.lado2 + "]";
  }
  
}  
