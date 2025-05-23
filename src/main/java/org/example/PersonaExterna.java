package org.example;

import java.time.Instant;
import java.time.ZoneOffset;

public class PersonaExterna implements Invitable {
    private String apellidos;
    private String nombre;
    private String correo;
    public PersonaExterna(String ap, String nom, String cor){
        apellidos=ap;
        nombre=nom;
        correo=cor;
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
    public void invitar(Invitacion inv) throws NoHayInvitacionException{
        if (inv==null){
            throw new NoHayInvitacionException();
        }
        inv.addInvitado(this);
        System.out.printf("%s %s (Persona Externa) ha sido invitado a una reunion en %s (%tc)\n",nombre, apellidos, inv.getFDUnirse(), inv.getHora().atZone(ZoneOffset.UTC));
    }
    public void unirseAReunion(Reunion r){
        Instant horadeIngreso = Instant.now();
        if (horadeIngreso.compareTo(r.getHoraPrevista())<=0){
            r.addAsistencia(this);
        } else {
            r.addRetraso(this, horadeIngreso);
        }
    }
    @Override
    public String toString() {
        return "PersonaExterna: (nota: placeholder, editar si es necesario)";
    }
}
