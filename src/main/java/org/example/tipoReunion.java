package org.example;

public enum tipoReunion {
    TECNICA("Tecnica"),
    MARKETING("Marketing"),
    OTRO("otro");
    private String tipo;
    tipoReunion(String a){
        tipo = a;
    }
    public String getTipo(){
        return tipo;
    }
}
