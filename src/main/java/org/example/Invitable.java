package org.example;

/**Interfaz para objetos Invitables, con metodos para ser invitados y unirse a reuniones*/
public interface Invitable {
    public void invitar(Invitacion inv);
    public void unirseAReunion(Reunion r);
}
