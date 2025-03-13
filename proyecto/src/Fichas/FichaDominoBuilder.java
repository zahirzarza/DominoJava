/**
 *
 *
 *@author Axel Zarza
 *@author Diego Varela
 *@author Emilio Perez
 *la clase abstracta "FichaAbstracta" define una estructura base para representar fichas de dominó, y las clases derivadas deben implementar los detalles específicos de cada tipo de ficha.
  */




package Fichas;

public class FichaDominoBuilder {
    private int lado1;
    private int lado2;
    private boolean esDoble;

    // Constructor vacío
    public FichaDominoBuilder() {
    }

    // Métodos para configurar los atributos
    public FichaDominoBuilder setLado1(int lado1) {
        this.lado1 = lado1;
        return this;
    }

    public FichaDominoBuilder setLado2(int lado2) {
        this.lado2 = lado2;
        return this;
    }

    public FichaDominoBuilder setEsDoble(boolean esDoble) {
        this.esDoble = esDoble;
        return this;
    }

    // Método para construir la instancia de FichaDomino
    public FichaDomino build() {
        return new FichaDomino(lado1, lado2, esDoble);
    }
}
