package org.example;

public class Nota {
    private String contenido;
    public Nota(String x){
        contenido=x;
    }
    public String getNota(){
        return contenido;
    }
    @Override
    public String toString() {
        return "Nota: sirve para añadir notas y comentarios a las reuniones" +
                "(nota: placeholder, editar si es necesario)";
    }
}
