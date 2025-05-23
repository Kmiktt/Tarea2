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
    public void unirseAReunion(Reunion r){
        for (Empleado e : empleados){
            e.unirseAReunion(r);
        }
    }
    @Override
    public String toString() {
        String x="";
        for (int i = 0; i < empleados.size(); i++) {
            x=x+empleados.get(i).toString()+"\n";
        }
        return x;
    }
}
