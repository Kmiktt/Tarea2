package org.example;

import java.util.ArrayList;

/**Almacena un conjunto de Empleados que pertenecen al Departamento. Debido a que implementa la interfaz Invitable,
 * se puede invitar a todo un departamento. El departamento tiene su propio nombre y una cantidad de empleados*/
public class Departamento implements Invitable{
    private String nombre;
    private ArrayList<Empleado> empleados;
    /**Constructor de Departamento, crea la arraylist de empleados y se le asigna el nombre
     * @param x Nombre del Departamento*/
    public Departamento(String x){
        nombre=x;
        empleados=new ArrayList<Empleado>();
    }
    /**Añade empleado al arrayList del departamento
     * @param em Empleado a añadir a Departamento*/
    public void agregarEmpleado(Empleado em){
        empleados.add(em);
    }
    /**Devuelve el numero de empleados en Departamento
     * @return  Int con el tamaño del array de Empleados*/
    public int obtenerCantidadEmpleados(){
        return empleados.size();
    }
    /**Metodo de interfaz Invitable, añade como invitados a todos los Empleados del Departamento
     * @param inv Invitación de la reunión a la que está invitada el Departamento*/
    public void invitar(Invitacion inv){
        for (Empleado empleado : empleados) {
            empleado.invitar(inv);
        }
    }
    /**Metodo de interfaz Invitable, indica como presentes a todos los Empleados del Departamento
     * Nota: Los va a anotar como que llegarón todos en tiempos casi identicos, puede ser muy situacional
     * en un uso común y corriente, es sugerido usar el metodo de unirse para cada empleado
     * @param r Reunión a la que está asistiendo todo el Departamento*/
    public void unirseAReunion(Reunion r){
        for (Empleado e : empleados){
            e.unirseAReunion(r);
        }
    }
    @Override
    /**Override de toString, devuelve nombre de Departamento y nombre de todos sus Empleados
     * @return String de la forma "nombreDeDepartamento:nombreEmpleado1ApellidoEmpleado1_nombreE2appelidoE2_..."*/
    public String toString() {
        String x=nombre+":";
        for (int i = 0; i < empleados.size(); i++) {
            x=x+empleados.get(i).toString()+"_";
        }
        return x;
    }
}
