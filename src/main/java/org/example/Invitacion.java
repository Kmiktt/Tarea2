package org.example;

import java.time.Instant;
import java.util.ArrayList;

public class Invitacion {
    private Instant hora;
    private ArrayList invitados;
    private String formaDeUnirse;
    public Invitacion(Instant hor, String fDU){
        hora = hor;
        invitados = new ArrayList<Invitable>();
        formaDeUnirse = fDU;
    }
}
