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
    @Override
    public String toString() {
        return "Asistencia: (nota: placeholder, editar si es necesario)";
    }
}

class Retraso extends Asistencia {
    private Instant hora;
    public Retraso(Empleado emp, Instant h){
        super(emp);
        hora = h;
    }
    @Override
    public String toString() {
        return "Retraso: (nota: placeholder, editar si es necesario)";
    }
}

