package org.example;

import java.time.Instant;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Empleado e1 = new Empleado("123632", "Gonzalez", "Roberto", "rG12@udec.cl");
        Empleado e2= new Empleado("126032", "Tapia", "Mario", "mTG60@udec.cl");
        Empleado e3= new Empleado("302240", "Fernandez", "Gabriela", "gF30@udec.cl");
        Empleado e4= new Empleado("554211", "Acevedo", "Antonia", "aA55@udec.cl");
        Departamento dep = new Departamento("Test Department");
        dep.agregarEmpleado(e3);
        dep.agregarEmpleado(e4);
        PersonaExterna p1 = new PersonaExterna("Sepúlveda","Manuel", "mSP@uss.cl");
        Reunion r1 = e1.organizarReunionP("13:30 24/05/2025", 60, "A-6 Udec", 3);
        dep.invitar(r1.invitacion);
        p1.invitar(r1.invitacion);
        Thread.sleep(500);
        e3.unirseAReunion(r1);
        Thread.sleep(500);
        e4.unirseAReunion(r1);
        Thread.sleep(500);
        r1.iniciar();
        r1.addNotas("Persona externa no llegó a tiempo, a tener en cuenta para futuras reuniones");
        Thread.sleep(1000);
        p1.unirseAReunion(r1);
        Thread.sleep(1000);
        r1.addNotas("Buenas ideas se han entregado sobre como mejorar la compañia, hablar con CEO");
        Thread.sleep(500);
        r1.finalizar();
        r1.crearInforme();

    }
}
