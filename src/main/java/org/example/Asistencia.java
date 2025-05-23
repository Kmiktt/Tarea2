package org.example;

import java.time.Instant;

public class Asistencia {
    protected Invitable e;
    public Asistencia(Invitable emp){
        e = emp;
    }
    public Invitable getE() {
        return e;
    }
    @Override
    public String toString() {
        return e.toString();
    }
}

class Retraso extends Asistencia {
    private Instant hora;
    public Retraso(Invitable emp, Instant h){
        super(emp);
        hora = h;
    }
    @Override
    public String toString() {
        return (super.e.toString() + " | Hora de llegada: "+ hora);
    }
}

