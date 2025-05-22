package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import junit.framework.TestCase;


public class EmpleadoTest extends TestCase {
    private Empleado EmPrueba1;
    private Empleado EmPrueba2;
    private Reunion reunion;
    @BeforeEach
    public void setUp() {
        EmPrueba1 = new Empleado("a","jose","juan","juan@jose.cl");
        EmPrueba2 = new Empleado("b","juan","pepito","pepito@juan.cl");
    }
    @AfterEach
    public void tearDown() {
        EmPrueba1=null;
        EmPrueba2=null;
    }

    @Test
    @DisplayName("Verificar si la invitacion se guardo correctamente")
    public void testInvitar() {
        reunion=EmPrueba1.organizarReunionP("10:10 20/05/2025",10,"Sala A");
        EmPrueba2.invitar(reunion.invitacion);
        assertEquals("Sala A",reunion.invitacion.getFDUnirse());
        assertEquals(reunion,reunion.invitacion.getReunionCorrespondiente());
    }
    @Test
    @DisplayName("Verificar caso extremo de invitar a una reunion nula")
    public void NullInvitacion() {
        EmPrueba2.invitar(null);
    }
}