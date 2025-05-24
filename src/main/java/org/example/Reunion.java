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
    private ArrayList<Asistencia> asistencias;
    private ArrayList<Asistencia> retrasos;
    private ArrayList<Nota> notas;
    private String ReuTip;
    protected String fDUnirse;

    public List obtenerAsistencias(){
        return asistencias;
    }

    public List obtenerAusencias(){
        ArrayList al = new ArrayList<>();
        for (Object i : invitacion.getInvitados()){
            if (!asistencias.contains(i)&&!retrasos.contains(i)){
                al.add(i);
            }
        }
        return al;
    }

    public List obtenerRetrasos(){
        return retrasos;
    }

    public int obtenerTotalAsistencia(){
        return asistencias.size()+retrasos.size();
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
    /**Constructor de Reunion, usa la fecha, hora, y duracion entregadas como parametros para
     * definir sus propios parametros de fecha, hora prevista y duración prevista, ademas de definir
     * los ArrayLists para Notas y Asistencias
     * @param date String con forma hh:mm d/M/yyyy que contiene la hora y fecha de la reunión
     * @param durationMins Int con la cantidad de minutos que durará la reunión
     * @param tipo Número entre 1 y 3 que señaliza el carácter de la reunión (Tecnica/Marketing/Otro)*/
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
    public void addAsistencia(Invitable e){
        Asistencia aplace = new Asistencia(e);
        asistencias.add(aplace);
    }
    public void addRetraso(Invitable e, Instant h){
        Asistencia aplace = new Retraso(e,h);
        retrasos.add(aplace);
    }
    public void addNotas(String x){
        Nota n = new Nota(x);
        notas.add(n);
    }

    public void crearInforme(){
        try {
            String fileName = "Reunion_"+fecha.getDate()+"-"+(fecha.getMonth()+1)+"-"+(fecha.getYear()+1990)+"-"+organizador.toString()+".txt";
            String test = "Lol";
            System.out.println(fileName);
            fileName.replaceAll(" ", "_");
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
                    + "\n hora de inicio: " + horaInicio
                    + "\n hora de finalizacion: " + horaFin
                    + "\n duraciont total:" + calcularTiempoReal()
                    + "\n tipo de reunión: " + ReuTip
                    + "\n enlace o sala: " + fDUnirse
                    + "\n participantes: \n Puntuales:");
            for (Asistencia a : asistencias){
                fl.write("\n " + a.toString());
            }
            fl.write("\n Atrasados:");
            for (Asistencia a : retrasos){
                fl.write("\n " + a.toString());
            }
            fl.write("\n Notas: \n");
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
}

class ReunionVirtual extends Reunion{
    private String enlace;
    public ReunionVirtual(String date, int durationMins, String enl, int tipo){
        super(date,durationMins,tipo);
        enlace = enl;
        fDUnirse = enlace;
        invitacion = new Invitacion(super.getHoraPrevista(), enlace,this);
    }
}
class ReunionPresencial extends Reunion{
    private String sala;
    public ReunionPresencial(String date, int durationMins, String sal, int tipo){
        super(date,durationMins,tipo);
        sala = sal;
        fDUnirse = sala;
        invitacion = new Invitacion(super.getHoraPrevista(), sala,this);
    }
}