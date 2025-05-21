package org.example;
public class PersonaExterna implements Invitable {
    private String apellidos;
    private String nombre;
    private String correo;
    public PersonaExterna(String x, String y, String z, String c){
        apellidos=y;
        nombre=z;
        correo=c;
    }
    public String getNombre(){
        return nombre;
    }
    public String getApellidos(){
        return apellidos;
    }
    public String getCorreo(){
        return correo;
    }
    public void setCorreo(String x){
        correo=x;
    }
    public void setNombre(String x){
        nombre=x;
    }
    public void invitar(Invitacion inv){
    }
    @Override
    public String toString() {
        return "PersonaExterna: (nota: placeholder, editar si es necesario)";
    }
}
