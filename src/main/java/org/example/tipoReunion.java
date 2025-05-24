package org.example;


/**Almacenan los diferentes posibles temas de una reunion*/
public enum tipoReunion {
    TECNICA("Tecnica"),
    MARKETING("Marketing"),
    OTRO("otro");
    private String tipo;
    /**Constructor de enum
     *@param a String de el tipo de Reunion */
    tipoReunion(String a){
        tipo = a;
    }
    /**Getter de String del tipo*/
    public String getTipo(){
        return tipo;
    }
}
