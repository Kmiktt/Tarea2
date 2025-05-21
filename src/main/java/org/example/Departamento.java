package org.example;

import java.util.ArrayList;

public class Departamento implements Invitable{
    private String nombre;
    private ArrayList<Empleado> empleados;
    public Departamento(String x){
        nombre=x;
        empleados=new ArrayList<Empleado>();
    }
    public void agregarEmpleado(Empleado em){
        empleados.add(em);
    }
    public int obtenerCantidadEmpleados(){
        return empleados.size();
    }
    public void invitar(Invitacion inv){
        for (int i = 0; i < empleados.size(); i++) {
            empleados.get(i).invitar(inv);
        }
    }
    @Override
    public String toString() {
        return "Departamento: contiene arrays de distintos empleados y tiene la " +
                "habilidad de añadirlos segun se requiera " +
                "(nota: placeholder, editar si es necesario)";
    }
}
