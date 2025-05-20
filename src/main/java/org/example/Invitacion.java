package org.example;

import java.time.Instant;
import java.util.ArrayList;

public class Invitacion {
    private Instant hora;
    private ArrayList invitados;
    private String formaDeUnirse;
    private Reunion reunionCorrespondiente;
    public Invitacion(Instant hor, String fDU, Reunion r){
        hora = hor;
        invitados = new ArrayList<Invitable>();
        formaDeUnirse = fDU;
        reunionCorrespondiente = r;
    }
    public void addInvitado(Invitable p){
        invitados.add(p);
    }

    public Reunion getReunionCorrespondiente() {
        return reunionCorrespondiente;
    }

    public String getFDUnirse() {
        return formaDeUnirse;
    }

    public Instant getHora() {
        return hora;
    }
}
