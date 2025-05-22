package org.example;

import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReunionTest extends TestCase {
    Reunion reP;
    Reunion reV;
    @BeforeEach
    public void setUp(){
        reP=new ReunionPresencial("10:10 20/05/2025",10,"Sala A",1);
        reV=new ReunionVirtual("10:10 20/05/2025",10,"A.cl",1);
    }
    @AfterEach
    public void tearDown(){
        reP=null;
        reV=null;
    }
    @Test
    @DisplayName("Caso Extremo de invitar null")
    public void testNull() {
        reP.invitacion.addInvitado(null);
    }
}