package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GeldbetragTest
{

    Geldbetrag gn15, g50, g100, g3500;
    
    @Before
    public void setUp()
    {
        gn15 = Geldbetrag.valueOf(-15);
        g50 = Geldbetrag.valueOf(50);
        g100 = Geldbetrag.valueOf(100);
        g3500 = Geldbetrag.valueOf(3500);
    }

    @Test
    public void testParseFromString()
    {
        assertEquals(Geldbetrag.valueOf("123,15").toEurocent(), 12315);
        assertEquals(Geldbetrag.valueOf("12").toEurocent(), 1200);
        assertEquals(Geldbetrag.valueOf("0").toEurocent(), 0);
        assertEquals(Geldbetrag.valueOf("-7").toEurocent(), -700);
        assertEquals(Geldbetrag.valueOf("-7.12").toEurocent(), -712);
    }

    @Test
    public void testAdd()
    {
        assertEquals(g100.add(gn15).toEurocent(), 85);
        assertEquals(g100.add(g3500).toEurocent(), 3600);
    }

    @Test
    public void testSubtract()
    {
        assertEquals(g100.subtract(gn15).toEurocent(), 115);
        assertEquals(g100.subtract(g3500).toEurocent(), -3400);
    }

    @Test
    public void testMultiply()
    {
        assertEquals(gn15.multiply(10).toEurocent(), -150);
        assertEquals(g100.multiply(5).toEurocent(), 500);
        assertEquals(g3500.multiply(0).toEurocent(), 0);
    }

    @Test
    public void testToString()
    {
        assertEquals(gn15.toString(), "-0,15 €");
        assertEquals(g3500.toString(), "35,00 €");
    }

    @Test
    public void testEqualsAndHashCode()
    {
        Geldbetrag d50 = g100.subtract(g50);
        assertEquals(d50, g50);
        assertEquals(d50.hashCode(), d50.hashCode());
        
       assertNotEquals(gn15, g50);
    }
    
    
    

}
