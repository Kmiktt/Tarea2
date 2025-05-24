package org.example;

import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

public class ReunionTest extends TestCase {
    Reunion reP;
    Reunion reV;
    Empleado Em1;
    Empleado Em2;
    @BeforeEach
    public void setUp(){
        reP=new ReunionPresencial("10:10 20/05/2026",10,"Sala A",1);
        reV=new ReunionVirtual("10:10 20/05/2026",10,"A.cl",1);
        Em1=new Empleado("a","jose","juan","juan@jose.cl");
        Em2=new Empleado("b","juan","pepito","pepito@juan.cl");
    }
    @AfterEach
    public void tearDown(){
        reP=null;
        reV=null;
        Em1=null;
        Em2=null;
    }
    @Test
    @DisplayName("Prueba de lista de Ausencia")
    public void testAusencia() {
        Em1.invitar(reP.invitacion);
        reP.finalizar();
        Em1.unirseAReunion(reP);
        assertEquals(reP.obtenerAusencias().getFirst(),Em1);
    }
    @Test
    @DisplayName("Caso Extremo de invitar null")
    public void testNull() {
        reP.invitacion.addInvitado(null);
    }

    @Test
    @DisplayName("Prueba de texto guardado")
    public void testArchivo(){
        reP.setOrganizador(Em1);
        String NameTest = "Reunion_"+reP.getHoraPrevista()+"-"+reP.getOrganizador().toString()+".txt";
        NameTest = NameTest.replaceAll(" ", "_");
        NameTest = NameTest.replaceAll(":", "-");
        Em1.invitar(reP.invitacion);
        Em2.invitar(reP.invitacion);
        Em1.unirseAReunion(reP);
        Em2.unirseAReunion(reP);
        reP.iniciar();
        reP.finalizar();
        reP.crearInforme();

        File myObj = new File(NameTest);
        assertTrue(myObj.canRead());
    }

    @Test
    @DisplayName("Caso extremo de falta de datos para informe")
    public void testFaltaDatos(){
        reP.setOrganizador(Em1);
        String NameTest = "Reunion_"+reP.getHoraPrevista()+"-"+reP.getOrganizador().toString()+".txt";
        NameTest = NameTest.replaceAll(" ", "_");
        NameTest = NameTest.replaceAll(":", "-");
        Em1.invitar(reP.invitacion);
        Em2.invitar(reP.invitacion);
        Em1.unirseAReunion(reP);
        Em2.unirseAReunion(reP);
        //no hay hora de inicio ni hora de finalizacion
        try{
            reP.crearInforme();
            fail("se esperaba excepcion SinSuficientesDatosException");
        }
        catch(SinSuficientesDatosException e){

        }



    }

}