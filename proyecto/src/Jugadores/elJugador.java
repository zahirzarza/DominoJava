package Jugadores;
import Fichas.*;

//Interface Jugador
/**
 *
 *
 *@author Axel Zarza
 *@author Diego Varela
 *la interfaz "elJugador" establece un contrato que las clases deben seguir para representar a un jugador en un juego de dominó. Cada uno de estos métodos es esencial para la interacción de un jugador con las fichas y las reglas del juego.
  */
public interface elJugador {

  
  public void recibirFicha(FichaDomino ficha);

  public void eliminarFicha(FichaDomino ficha);

  public FichaDomino EscogerFicha();

  public int escogerlado();


}