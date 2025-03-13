
/**
 *
 *
 *@author Axel Zarza
 *@author Diego
 *ExpFuera" es una clase que extiende la clase base "Exception" y se utiliza para definir una excepción personalizada en el programa. Esta clase define dos constructores, uno con un mensaje predeterminado y otro que permite especificar un mensaje personalizado. Su propósito es manejar excepciones relacionadas con opciones no válidas en el programa
  */
public class ExpFuera extends Exception {
    
    public ExpFuera(){
        super("EXCEPCION: Opcion no valida \n");
    }

    public ExpFuera(String mensage){
        super(mensage);
    }
}
