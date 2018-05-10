package felipeborja.picoyplaca;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UnitTestPicoPlaca {

    @Test
    public void diaSemana1() {
        Calendar calendario=Calendar.getInstance();
        calendario.set(2018,6,7,0,0);
        String diaSemana=PicoPlaca.getDiaSemana(calendario);
        assertEquals("Lunes",diaSemana);
    }
    @Test
    public void diaSemana2() {
        Calendar calendario=Calendar.getInstance();
        calendario.set(2018,6,10,0,0);// 10-05-18
        String diaSemana=PicoPlaca.getDiaSemana(calendario);
        assertEquals("Jueves",diaSemana);
    }
    @Test
    public void diaSemana3() {
        Calendar calendario=Calendar.getInstance();
        calendario.set(2018,6,9,0,0);
        String diaSemana=PicoPlaca.getDiaSemana(calendario);
        assertEquals("Miércoles",diaSemana);
    }

}