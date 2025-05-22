package org.example;

import java.io.FileWriter;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.*;
import java.util.*;
import java.io.File;
import java.io.IOException;

public abstract class Reunion {
    private Date fecha;
    private Instant horaPrevista;
    private Duration duracionPrevista;
    private Instant horaInicio;
    private Instant horaFin;
    protected Invitacion invitacion;
    private Empleado organizador;
    private ArrayList asistencias;
    private ArrayList retrasos;
    private ArrayList<Nota> notas;
    private String ReuTip;

    public List obtenerAsistencias(){
        ArrayList al = new ArrayList<>();
        return al;
    }
    public List obtenerAusencias(){
        ArrayList al = new ArrayList<>();
        return al;
    }
    public List obtenerRetrasos(){
        ArrayList al = new ArrayList<>();
        return al;
    }
    public int obtenerTotalAsistencia(){
        return 2;
    }

    public void iniciar(){
        horaInicio = Instant.now();
    }

    public void finalizar(){
        horaFin = Instant.now();
    }

    public float calcularTiempoReal(){
        Duration d = Duration.between(horaInicio,horaFin);
        return ((float) d.toMillis() /1000 / 60);
    }
    
    public Reunion(String date, int durationMins, int tipo){
        String pattern = "kk:mm d/M/yyyy";
        DateTimeFormatter DTF = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime LDT = LocalDateTime.parse(date, DTF);
        fecha = new Date(LDT.getYear()-1900,LDT.getMonthValue()-1,LDT.getDayOfMonth());
        horaPrevista = LDT.toInstant(ZoneOffset.UTC);
        duracionPrevista = Duration.of(durationMins, ChronoUnit.MINUTES);
        asistencias = new ArrayList<Asistencia>();
        retrasos = new ArrayList<Asistencia>();
        notas = new ArrayList<Nota>();
        switch(tipo){
            case 1:{
                ReuTip = tipoReunion.TECNICA.getTipo();
                break;
            }
            case 2:{
                ReuTip = tipoReunion.MARKETING.getTipo();
                break;
            }
            default:{
                ReuTip = tipoReunion.OTRO.getTipo();
            }
        }
    }

    public Date getFecha() {
        return fecha;
    }

    public Instant getHoraPrevista() {
        return horaPrevista;
    }

    public void setOrganizador(Empleado organizador) {
        this.organizador = organizador;
    }
    public void addAsistencia(Empleado e){
        Asistencia aplace = new Asistencia(e);
        asistencias.add(aplace);
    }
    public void addRetraso(Empleado e, Instant h){
        Asistencia aplace = new Retraso(e,h);
        retrasos.add(aplace);
    }
    public void addNotas(String x){
        Nota n = new Nota(x);
        notas.add(n);
    }

    public void crearInforme(){
        try {
            String fileName = "Reunion_" + this.toString()+".txt";
            System.out.println(fileName);
            File myObj = new File(fileName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileWriter fl = new FileWriter(fileName);
            fl.write("Test 1: \n fecha de la reunion:" + getFecha()
                    + "\n hora de la reunion" + getHoraPrevista()
                    + "\n hora de inicio: ###"
                    + "\n hora de finalizacion ###"
                    + "\n duraciont total:" + calcularTiempoReal()
                    + "\n tipo de reunión: " + ReuTip
                    + "\n enlace o sala: ###"
                    + "\n participantes: ###"
                    + "\n notas: ");
            for(int i = 0; i < notas.size(); i++){
                Nota n = notas.get(i);
                fl.write(n.getNota() + "\n");
            }
            fl.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    @Override
    public String toString() {
        return "Reunion: esta clase abstracta es el plano inicial de una reunion" +
                "cuya variante no se especifica todavía, dentro tiene las " +
                "instrucciones para hacer el informe final de la reunion" +
                "(nota: placeholder, editar si es necesario)";
    }
}

class ReunionVirtual extends Reunion{
    private String enlace;
    public ReunionVirtual(String date, int durationMins, String enl, int tipo){
        super(date,durationMins,tipo);
        enlace = enl;
        invitacion = new Invitacion(super.getHoraPrevista(), enlace,this);
    }
    @Override
    public String toString() {
        return "ReunionVirtual: esta clase es una variante de la clase reunion" +
                "donde se añade la variable enlace en referencia a una " +
                "reunion virtual (nota: placeholder, editar si es necesario)";
    }
}
class ReunionPresencial extends Reunion{
    private String sala;
    public ReunionPresencial(String date, int durationMins, String sal, int tipo){
        super(date,durationMins,tipo);
        sala = sal;
        invitacion = new Invitacion(super.getHoraPrevista(), sala,this);
    }
    @Override
    public String toString() {
        return "ReunionPresencial: una variante de Reunion donde se añade" +
                "la variable sala en referencia a la sala designada para" +
                "la reunion presencial (nota: placeholder, editar si es necesario)";
    }
}