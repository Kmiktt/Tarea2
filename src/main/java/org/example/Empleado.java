package org.example;

import java.lang.reflect.Array;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;

/**Objeto capaz de organizar y participar de reuniones, ademas de recibir invitaciones
 * Almacena la reunion que crea para poder invitar a otros objetos Invitables, y a la
 * hora de ingresar a una reunion, calcula la hora de ingreso para determinar si llega
 * a la hora o con retraso a la reunión*/
public class Empleado implements Invitable {
    private String id;
    private String apellidos;
    private String nombre;
    private String correo;
    private Reunion reunionOrganizada;
    /**Metodo constructor, define las variables privadas que representan al
     * empleado (nombre completo, ID y correo).
     * @param i String con ID del empleado.
     * @param apel String con el/los apellidos del empleado.
     * @param nomb String con el nombre del empleado
     * @param c String con el correo del empleado*/
    public Empleado(String i, String apel, String nomb, String c){
        id=i;
        apellidos=apel;
        nombre=nomb;
        correo=c;
    }
    /**Getter de ID
     * @return String con ID de empleado */
    public String getId(){
        return id;
    }
    /**Getter del Nombre
     * @return String con Nombre de empleado */
    public String getNombre(){
        return nombre;
    }
    /**Getter de Apellidos
     * @return String con Apellidos de empleado */
    public String getApellidos(){
        return apellidos;
    }
    /**Getter de correo
     * @return String con el correo del empleado */
    public String getCorreo(){
        return correo;
    }
    //quiza dejarlo en protected, pero la opcion de cambiar correo y nombre a veces se hace necesaria
    public void setCorreo(String x){
        correo=x;
    }

    public void setNombre(String x){
        nombre=x;
    }
    /**Metodo para organizar reuniones Virtuales, dejando guardada en la reunión el empleado que llama a este metodo
     * @param date String con forma hh:mm d/M/yyyy que contiene la hora y fecha de la reunión
     * @param durationMins Int con la cantidad de minutos que durará la reunión
     * @param enl String que es el enlace para conectarse a la reunión virtual
     * @param tipo Número entre 1 y 3 que señaliza el carácter de la reunión (Tecnica/Marketing/Otro*/
    public Reunion organizarReunionV(String date, int durationMins, String enl,int tipo){
        Reunion rplace = new ReunionVirtual(date, durationMins, enl,tipo);
        rplace.setOrganizador(this);
        return rplace;
    }
    /**Metodo para organizar reuniones Presenciales, dejando guardada en la reunión el empleado que llama a este metodo
     * @param date String con forma hh:mm d/M/yyyy que contiene la hora y fecha de la reunión
     * @param durationMins Int con la cantidad de minutos que durará la reunión
     * @param sal String con el nombre de la sala en la que se llevara a cabo la reunión.
     * @param tipo Número entre 1 y 3 que señaliza el carácter de la reunión (Tecnica/Marketing/Otro*/
    public Reunion organizarReunionP(String date, int durationMins, String sal,int tipo){
        Reunion rplace = new ReunionPresencial(date, durationMins, sal,tipo);
        rplace.setOrganizador(this);
        return rplace;
    }
    /**Calcula la hora a la que llega a la reunión, y la compara con la hora de la reunión para saber si añadirse a la
     * lista de Asistencia (gente que llegó a tiempo) o de Retraso de la reunion. Metodo de interfaz Invitable
     * @param r Reunion a la cual se está asistiendo*/
    public void unirseAReunion(Reunion r){
        Instant horadeIngreso = Instant.now();
        if (horadeIngreso.compareTo(r.getHoraPrevista())<=0){
            r.addAsistencia(this);
        } else {
            r.addRetraso(this, horadeIngreso);
        }
    }
    /**Forma de ser invitado a una reunión, se añade a la lista de invitados e imprime en la consola una notificación correspondiente
     * Metodo proveniente de Interface Invitable
     * @param inv Invitación proveniente de una reunión determinada*/
    @Override
    public void invitar(Invitacion inv) throws NoHayInvitacionException{
        if (inv==null){
            throw new NoHayInvitacionException();
        }
        inv.addInvitado(this);
        System.out.printf("%s %s (ID: %s) ha sido invitado a una reunion en %s (%tc)\n",nombre, apellidos, id, inv.getFDUnirse(), inv.getHora().atZone(ZoneOffset.UTC));
    }
    @Override
    public String toString() {
        return (nombre+" "+apellidos);
    }
}
