package org.example;

import java.time.Instant;
import java.time.ZoneOffset;

/**Para personas que pueden participar en reuniones, pero no son parte de la empresa por lo que no son empleados*/
public class PersonaExterna implements Invitable {
    private String apellidos;
    private String nombre;
    private String correo;
    /**Constructor de PersonaExterna, almacena el nombre completo y el correo del sujeto
     * @param ap Apellidos de la persona Externa
     * @param nom Nombre de la persona Externa
     * @param cor Correo de la persona Externa*/
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
    /**Metodo de interfaz Invitable, permite unirse a la lista de invitados de  una reunión via la invitación correspondiente,
     * imprime un mensaje en la consola para indicar que fue invitado exitosamente.
     * @param inv Invitación de la reunión a la que PersonaExterna es invitada*/
    public void invitar(Invitacion inv) throws NoHayInvitacionException{
        if (inv==null){
            throw new NoHayInvitacionException();
        }
        inv.addInvitado(this);
        System.out.printf("%s %s (Persona Externa) ha sido invitado a una reunion en %s (%tc)\n",nombre, apellidos, inv.getFDUnirse(), inv.getHora().atZone(ZoneOffset.UTC));
    }
    /**Metodo de interfaz Invitable, permite unirse a una reunión, uniendose a la lista de Asistentes o de Atrasados
     * de acuerdo a la hora de llegada.
     * @param r Reunión a la que PersonaExterna asiste*/
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
        return (nombre+" "+apellidos);
    }
}
