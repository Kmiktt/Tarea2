package org.example;

import java.util.ArrayList;

public class Departamento {
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

}
