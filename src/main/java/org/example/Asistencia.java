package org.example;

import java.time.Instant;

/**Clase encargada de almacenar el objeto invitable (Empleado o PersonaExterna) que asiste a la reunión
 * a la que pertenece Asistencia*/
public class Asistencia {
    private Invitable e;
    /**Constructor de Asistencia, almacena el objeto Invitable que se le entrega como parametro
     * @param emp Objeto con interfaz Invitable, se queda almacenado como variable private*/
    public Asistencia(Invitable emp){
        e = emp;
    }
    /**Getter de objeto Invitable asociado
     * @return Invitable que está asociado con esta Asistencia*/
    public Invitable getE() {
        return e;
    }
    @Override
    public String toString() {
        return e.toString();
    }
}
/**Subclase de Asistencia, almacena tanto un objeto Invitable como Empleado o PersonaExterna y la hora
 * a la que llegaron atrasados */
class Retraso extends Asistencia {
    private Instant hora;
    /**Constructor de Asistencia, almacena el objeto Invitable que se le entrega como parametro y el Instant
     * en el que se une a la reunión
     * @param emp Objeto con interfaz Invitable, se queda almacenado como variable private
     * @param h Instant en el que Invitable se une a la reunion*/
    public Retraso(Invitable emp, Instant h){
        super(emp);
        hora = h;
    }
    /**Getter de Instant a la que llego el Invitable atrasado
     * @return  Instant de la hora de llegada*/
    public Instant getHora() {
        return hora;
    }

    @Override
    public String toString() {
        return (super.getE().toString() + " | Hora de llegada: "+ hora);
    }
}

