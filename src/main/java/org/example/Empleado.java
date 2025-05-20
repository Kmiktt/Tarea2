package org.example;

import java.lang.reflect.Array;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;

public class Empleado implements Invitable {
    private String id;
    private String apellidos;
    private String nombre;
    private String correo;

    public Empleado(String i, String apel, String nomb, String c){
        id=i;
        apellidos=apel;
        nombre=nomb;
        correo=c;
    }
    public String getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public String getApellidos(){
        return apellidos;
    }

    public String getCorreo(){
        return correo;
    }
    //quiza dejarlo en protected, pero la opcion de cambiar correo y nombre a veces se hace necesaria
    public void setCorreo(String x){
        correo=x;
    }

    public void setNombre(String x){
        nombre=x;
    }

    public void organizarReunionV(String date, int durationMins, String enl, int tipo){
        Reunion rplace = new ReunionVirtual(date, durationMins, enl,tipo);
    }
    public void organizarReunionP(String date, int durationMins, String sal, int tipo){
        Reunion rplace = new ReunionPresencial(date, durationMins, sal, tipo);
        rplace.setOrganizador(this);
    }
    public void participarReunion(Reunion r){
        Instant horadeIngreso = Instant.now();
        if (horadeIngreso.compareTo(r.getHoraPrevista())<=0){
            r.addAsistencia(this);
        } else {
            r.addRetraso(this, horadeIngreso);
        }
    }

    @Override
    public void invitar(Invitacion inv){
        inv.addInvitado(this);
        System.out.printf("%s %s (ID: %s) ha sido invitado a una reunion en %s (%tc)\n",nombre, apellidos, id, inv.getFDUnirse(), inv.getHora().atZone(ZoneOffset.UTC));
    }
}
