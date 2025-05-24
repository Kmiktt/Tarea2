package org.example;

import java.time.Instant;
import java.util.ArrayList;

/**Objeto que almacena la lista de invitados a la reunión a la que está asociada*/
public class Invitacion {
    private Instant hora;
    private ArrayList invitados;
    private String formaDeUnirse;
    private Reunion reunionCorrespondiente;
    /**Constructor de Invitación, guarda como variables privadas los parametros que se le entregan y crea lista de invitados
     * @param hor Instant con la hora de la reunión
     * @param fDU String con la forma de unirse a la reunión, ya sea enlace o sala
     * @param r Reunion a la que está asociada la invitación*/
    public Invitacion(Instant hor, String fDU, Reunion r){
        hora = hor;
        invitados = new ArrayList<Invitable>();
        formaDeUnirse = fDU;
        reunionCorrespondiente = r;
    }
    /**Añade un objeto Invitable a la lista de Invitados
     * @param p objeto Invitable, ya sea Empleado o PersonaExterna que está invitado a la reunión*/
    public void addInvitado(Invitable p){
        invitados.add(p);
    }
    /**Getter de la Reunion asociada con la Invitación
     * @return Reunión asociada*/
    public Reunion getReunionCorrespondiente() {
        return reunionCorrespondiente;
    }

    /**Getter de la forma de unirse a la Reunión asociada, ya sea sala o enlace
     * @return String con la forma de unirse, sala o enlace correspondiente.*/
    public String getFDUnirse() {
        return formaDeUnirse;
    }
    /**Getter del Instant en el que será la reunión asociada
     * @return Instant con la hora de la reunión*/
    public Instant getHora() {
        return hora;
    }
    @Override
    public String toString() {
        String x="";
        for (int i = 0; i < invitados.size(); i++) {
            x=x+invitados.get(i).toString()+"\n";
        }
        return x;
    }
    /**Getter de lista de Invitados
     * @return ArrayList con todos los Invitables que está invitados*/
    public ArrayList getInvitados() {
        return invitados;
    }
}
