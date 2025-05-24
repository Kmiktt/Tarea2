package org.example;

import java.io.FileWriter;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.*;
import java.util.*;
import java.io.File;
import java.io.IOException;

/**Objeto encargado de almacenar toda la información relevante para una Reunión:
 * Hora y Fecha, quien la organiza, como participar en ella, cuando y cuanto terminó durando y
 * quienes asistieron y llegaron atrasados, ademas del tipo de la reunión y las notas que se tomaron*/
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

    /**Getter de ArrayList de asistencias
     * @return List con todos los Invitables que llegaron a tiempo a la reunión */
    public List obtenerAsistencias(){
        return asistencias;
    }

    /**Devuelve lista de empleados ausentes, compara la lista de invitad
     * @return List con todos los Invitables que llegaron a tiempo a la reunión */
    public List obtenerAusencias(){
        ArrayList al = new ArrayList<>();
        for (Object i : invitacion.getInvitados()){
            if (!asistencias.contains(i)&&!retrasos.contains(i)){
                al.add(i);
            }
        }
        return al;
    }
    /**Getter de ArrayList de retrasos
     * @return List con todos los Invitables que llegaron atrasados a la reunión */
    public List obtenerRetrasos(){
        return retrasos;
    }

    /**Calcula n° de gente que asistió a la reunión, suma la cantidad de Empleados puntuales con
     * la cantidad de Empleados que llegaron atrasados
     * @return Int con el n° de empleados que asistieron*/
    public int obtenerTotalAsistencia(){
        return asistencias.size()+retrasos.size();
    }
    /**Guarda el Instant en el que se llama al metodo como el inicio de la reunión*/
    public void iniciar(){
        horaInicio = Instant.now();
    }

    /**Guarda el Instant en el que se llama al metodo como el fin de la reunión*/
    public void finalizar(){
        horaFin = Instant.now();
    }

    /**Devuelve la cantidad de minutos entre la hora de inicio y la hora de finalización de la reunión
     * @return float con la cantidad de minutos que duró la reunión*/
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
    /**Getter de la fecha de la reunión
     * @return Date con la fecha de la reunión y hora 0:00:00*/
    public Date getFecha() {
        return fecha;
    }
    /**Getter de hora prevista de la reunión, en formato Instant
     * @return Instant con la hora prevista (y fecha) de la reunión*/
    public Instant getHoraPrevista() {
        return horaPrevista;
    }

    /**Setter del organizador de la reunión, quien es un Empleado
     * @param organizador Empleado que organizó una reunión*/
    public void setOrganizador(Empleado organizador) {
        this.organizador = organizador;
    }
    /**Getter del empleado organizador
     * @return Empleado que organizó la reunión */
    public Empleado getOrganizador() {
        return organizador;
    }

    /**Añade al objeto Invitable a la lista de asistentes
     * @param e Invitable que asiste a la reunión*/
    public void addAsistencia(Invitable e){
        Asistencia aplace = new Asistencia(e);
        asistencias.add(aplace);
    }
    /**Añade al objeto Invitable a la lista de asistentes, hora de llegada es calculada antes de llamar al método
     * @param e Invitable que asiste a la reunión
     * @param h Instant en la que el Invitable llego tarde a la reunión*/
    public void addRetraso(Invitable e, Instant h){
        Asistencia aplace = new Retraso(e,h);
        retrasos.add(aplace);
    }
    /**Agrega una nota al array de Notas
     * @param x String que se convertira a Nota para guardar en notas*/
    public void addNotas(String x){
        Nota n = new Nota(x);
        notas.add(n);
    }

    public void crearInforme() throws SinSuficientesDatosException{
        try {
            String fileName = "Reunion_"+horaPrevista+"-"+organizador.toString()+".txt";
            fileName = fileName.replaceAll(" ", "_");
            fileName = fileName.replaceAll(":", "-");
            File myObj = new File(fileName);

            if(horaInicio==null||horaFin==null){
                throw new SinSuficientesDatosException();
            }

            if (myObj.createNewFile()) {
                System.out.println("Informe creado: " + myObj.getName());
                FileWriter fl = new FileWriter(fileName);
                fl.write("Test 1: \n Fecha de la reunion:" + getFecha()
                        + "\n Hora de la reunion" + getHoraPrevista()
                        + "\n Hora de inicio: " + horaInicio
                        + "\n Hora de finalizacion: " + horaFin
                        + "\n Duración total (en minutos):" + calcularTiempoReal()
                        + "\n Tipo de reunión: " + ReuTip
                        + "\n Enlace o Sala: " + fDUnirse
                        + "\n Participantes: \n Puntuales:");
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
            } else {
                System.out.println("Archivo con este nombre ya existe.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    @Override
    public String toString() {
        return ("Reunion"+organizador.toString()+"_"+fecha.toString()+"_"+horaPrevista.toString());
    }
}
/**Subclase de Reunión, para reuniones virtuales almacena el enlace de la reunión*/
class ReunionVirtual extends Reunion{
    private String enlace;
    /**Constructor de ReunionVirtual, además de las cosas de Reunion guarda el enlace de la reunión y crea la invitación para la reunión
     * @param date String con forma hh:mm d/M/yyyy que contiene la hora y fecha de la reunión
     * @param durationMins Int con la cantidad de minutos que durará la reunión
     * @param enl String con el enlace para unirse a la reunión.
     * @param tipo Número entre 1 y 3 que señaliza el carácter de la reunión (Tecnica/Marketing/Otro)*/
    public ReunionVirtual(String date, int durationMins, String enl, int tipo){
        super(date,durationMins,tipo);
        enlace = enl;
        fDUnirse = enlace;
        invitacion = new Invitacion(super.getHoraPrevista(), enlace,this);
    }
    @Override
    public String toString() {
        return ("ReunionV"+super.getOrganizador().toString()+"_"+super.getFecha().toString()+"_"+super.getHoraPrevista().toString());
    }
}
/**Subclase de Reunión, para reuniones presenciales almacena el nombre de la sala de la reunión*/
class ReunionPresencial extends Reunion{
    private String sala;
    /**Constructor de ReunionPresencial, además de las cosas de Reunion guarda el nombre de la sala
     * donde se realiza la reunión y crea la invitación para la reunión
     * @param date String con forma hh:mm d/M/yyyy que contiene la hora y fecha de la reunión
     * @param durationMins Int con la cantidad de minutos que durará la reunión
     * @param sal String con la sala a la que ir para participar en la reunión.
     * @param tipo Número entre 1 y 3 que señaliza el carácter de la reunión (Tecnica/Marketing/Otro)*/
    public ReunionPresencial(String date, int durationMins, String sal, int tipo){
        super(date,durationMins,tipo);
        sala = sal;
        fDUnirse = sala;
        invitacion = new Invitacion(super.getHoraPrevista(), sala,this);
    }
    @Override
    public String toString() {
        return ("ReunionP"+super.getOrganizador().toString()+"_"+super.getFecha().toString()+"_"+super.getHoraPrevista().toString());
    }
}