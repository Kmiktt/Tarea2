package org.example;

/**Objeto que guarde una nota de la reunión*/
public class Nota {
    private String contenido;
    /**Guarda la string que se le da como el contenido de la nota
     * @param x String que será el contenido de la nota*/
    public Nota(String x){
        contenido=x;
    }
    /**Getter de contenido de la Nota
     * @return String con el contenido de la Nota*/
    public String getNota(){
        return contenido;
    }
    @Override
    public String toString() {
        return contenido;
    }
}
