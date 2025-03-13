import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import Fichas.*;
import Jugadores.*;
import Juegos.*;


//Clase principal
/**
 *
 *
 *@author Axel Zarza
 *@author Diego Varela
  *@author Emilio Perez
 *
 la clase "Domino" actúa como un controlador para iniciar y gestionar el juego de dominó con diferentes modos y configuraciones.
  */

public class Domino { 
  public static void main(String[] args) throws ClassNotFoundException, IOException {

    //Bienvenida
    System.out.println("========| D O M I N O |==========");
    //Creamos dos jugadores
    String name1, name2;
    boolean bandera = true;
    int chek;
    Scanner sc = new Scanner(System.in);

    do{
    int tipo = 0;
    System.out.println("Seleccione el modo de juego: ");
    System.out.println("1. jugador - jugador");
    System.out.println("2. jugador - bot");
    System.out.println("3. bot - bot");
    System.out.println("4. sbot - bot");
    System.out.println("5. mostrar ganadores pasados ");
    do{
    tipo = sc.nextInt();
    }while(!(tipo > 0 && tipo < 6));



    switch (tipo) {
      case 1:
        System.out.println("JUEGO: Usuario vs Usuario");
        System.out.println("Ingrese el nombre del jugador 1: ");
        name1 = sc.nextLine();
        name1 = sc.nextLine();
        System.out.println("Ingrese el nombre del jugador 2: ");
        name2 = sc.nextLine();
        JuegoJvJ juego = new JuegoJvJ(name1,name2);
        juego.jugar();

        break;
      
      case 2:
        System.out.println("JUEGO: Usuario vs Bot");
        System.out.println("Ingrese el nombre del jugador: ");
        name1 = sc.nextLine();
        name1 = sc.nextLine();
        System.out.println("Ingrese el nombre del bot: ");
        name2 = sc.nextLine();
        JuegoJvB juegouvb = new JuegoJvB(name1,name2);
        juegouvb.jugar();
        break;

      case 3:
        System.out.println("JUEGO: Bot vs Bot");
        System.out.println("Ingrese el nombre del bot 1: ");
        name1 = sc.nextLine();
        name1 = sc.nextLine();
        System.out.println("Ingrese el nombre del bot 2: ");
        name2 = sc.nextLine();
        JuegoBvB juegobvb = new JuegoBvB(name1,name2);
        juegobvb.jugar();
        break;
      case 4:
        System.out.println("JUEGO: SBot vs Bot");
        System.out.println("Ingrese el nombre del sbot 1: ");
        name1 = sc.nextLine();
        name1 = sc.nextLine();
        System.out.println("Ingrese el nombre del bot 2: ");
        name2 = sc.nextLine();
        SJuegoBvB juegossbvb = new SJuegoBvB(name1,name2);
        juegossbvb.crearFichas();
        juegossbvb.repartirFichasS();
        juegossbvb.jugar();
      break;
      case 5://segundo menu!
        int x = 0;
        System.out.println("Seleccione tipo de ganadores el modo de juego: ");
        System.out.println("1. jugador - jugador");
        System.out.println("2. jugador - bot");
        System.out.println("3. bot - bot");
        System.out.println("4. Sbot - bot");
        do{
        x = sc.nextInt();
        }while(!(x > 0 && x < 5));
        switch (x) {
          case 1:
          System.out.println("Mural de ganadores de modalidad Jugador vs Jugador: ");
            mostrarContenidoArchivoJuegoJvJ();
            break;
          case 2:
          System.out.println("Mural de ganadores de modalidad Jugador vs Bot: ");
          mostrarContenidoArchivoJuegoJvB();
            break;
          case 3: 
          System.out.println("Mural de ganadores de modalidad Bot vs Bot: ");
          mostrarContenidoArchivoJuegoBvB();
            break;
          case 4:
          System.out.println("Mural de ganadores de modalidad SBot vs Bot: ");
          mostrarContenidoArchivoJuegoSBvB();
          default:
          System.out.println("No se selecciono alguno");
            break;
        }
      default:
        System.out.println("No se selecciono alguno");
        break;

    }

    System.out.println("Desesa Jugar de nuevo ? :D");
    System.out.println("1. Si");
    System.out.println("2. Despues");
    do{
      chek = sc.nextInt();
      try {
        if (!(chek>0 && chek<3)) {
          throw new ExpFuera();
        }
      } catch (ExpFuera e) {
        
        System.out.println(e.getMessage());

        System.out.println("Desesa Jugar de nuevo ? :D");
        System.out.println("1. Si");
        System.out.println("2. Despues");

      }
    }while(!(chek>0 && chek<3));

    if (chek != 1) {
      bandera = false;
    }


  }while(bandera);
    
  }
  public static void mostrarContenidoArchivoJuegoJvJ() {
        try {
            // Crear un objeto FileReader con la ruta del archivo
            FileReader fileReader = new FileReader("JuegoJvJ.txt");

            // Crear un objeto BufferedReader para leer desde el archivo
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Variable para almacenar cada línea leída del archivo
            String linea;

            // Leer y mostrar cada línea del archivo en la consola
            while ((linea = bufferedReader.readLine()) != null) {
                System.out.println(linea);
            }

            // Cerrar el BufferedReader para liberar recursos
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
    }
    
    public static void mostrarContenidoArchivoJuegoJvB() {
        try {
            // Crear un objeto FileReader con la ruta del archivo
            FileReader fileReader = new FileReader("JuegoJvB.txt");

            // Crear un objeto BufferedReader para leer desde el archivo
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Variable para almacenar cada línea leída del archivo
            String linea;

            // Leer y mostrar cada línea del archivo en la consola
            while ((linea = bufferedReader.readLine()) != null) {
                System.out.println(linea);
            }

            // Cerrar el BufferedReader para liberar recursos
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
    }
    public static void mostrarContenidoArchivoJuegoBvB() {
        try {
            // Crear un objeto FileReader con la ruta del archivo
            FileReader fileReader = new FileReader("JuegoBvB.txt");

            // Crear un objeto BufferedReader para leer desde el archivo
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Variable para almacenar cada línea leída del archivo
            String linea;

            // Leer y mostrar cada línea del archivo en la consola
            while ((linea = bufferedReader.readLine()) != null) {
                System.out.println(linea);
            }

            // Cerrar el BufferedReader para liberar recursos
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
    }
    public static void mostrarContenidoArchivoJuegoSBvB() {
        try {
            // Crear un objeto FileReader con la ruta del archivo
            FileReader fileReader = new FileReader("SJuegoBvB.txt");

            // Crear un objeto BufferedReader para leer desde el archivo
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Variable para almacenar cada línea leída del archivo
            String linea;

            // Leer y mostrar cada línea del archivo en la consola
            while ((linea = bufferedReader.readLine()) != null) {
                System.out.println(linea);
            }

            // Cerrar el BufferedReader para liberar recursos
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
    }
}  

    