package org.example;

import java.time.Instant;

public class Asistencia {
    private Empleado e;
    public Asistencia(Empleado emp){
        e = emp;
    }
    public Empleado getE() {
        return e;
    }
}

class Retraso extends Asistencia {
    private Instant hora;
    public Retraso(Empleado emp, Instant h){
        super(emp);
        hora = h;
    }
}

