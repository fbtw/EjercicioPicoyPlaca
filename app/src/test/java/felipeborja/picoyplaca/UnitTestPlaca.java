package felipeborja.picoyplaca;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public class UnitTestPlaca {

    @Test
    public void codigoPlaca() {
        String codigoPlaca = "PBC-1234";
        Placa placa=new Placa(codigoPlaca,0,0);
        assertEquals("PBC-1234",placa.getCodigoPlaca());
    }
    @Test
    public void ultimoDigito() {
        String codigoPlaca = "PBC-1234";
        Placa placa=new Placa(codigoPlaca,0,0);
        assertEquals(4,placa.getUltimoDigito());
    }
    @Test
    public void esHoraPico1() {
        Placa placa1 = new Placa("AAA-0000", 7, 1);
        assertTrue( placa1.isPico());
    }
    @Test
    public void esHoraPico2() {
        Placa placa2 = new Placa("AAA-0000", 9, 28);
        assertTrue( placa2.isPico());
    }
    @Test
    public void esHoraPico3() {
        Placa placa3 = new Placa("AAA-0000", 16, 59);
        assertTrue( placa3.isPico());
    }
    @Test
    public void esHoraPico4() {
        Placa placa4=new Placa("AAA-0000",19,15);
        assertTrue(placa4.isPico());
    }
    @Test
    public void noEsHoraPico1() {
        Placa placa1 =new Placa("AAA-0000",9,31);
        assertFalse(placa1.isPico());
    }
    @Test
    public void noEsHoraPico2() {
        Placa placa1 =new Placa("AAA-0000",15,59);
        assertFalse(placa1.isPico());
    }

    @Test
    public void tieneMulta() {
        Calendar calendario = Calendar.getInstance();
        calendario.set(2018,6,10,0,0);
        Placa placa =new Placa("PWA-567",0,0);
        System.out.print(calendario.get(Calendar.DAY_OF_WEEK));
        assertTrue(placa.tieneMulta(calendario,placa.getUltimoDigito()));
    }
    @Test
    public void noTieneMulta() {
        Calendar calendario = Calendar.getInstance();
        calendario.set(2018,6,10,0,0);
        Placa placa =new Placa("PWA-569",0,0);
        System.out.print(calendario.get(Calendar.DAY_OF_WEEK));
        assertFalse(placa.tieneMulta(calendario,placa.getUltimoDigito()));
    }
}