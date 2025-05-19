package org.example;

public class Nota {
    private String contenido;

    public Nota(String x){
        contenido=x;
    }
    public void setNota(String x){
        contenido=x;
    }
    public String getNota(){
        return contenido;
    }
}
