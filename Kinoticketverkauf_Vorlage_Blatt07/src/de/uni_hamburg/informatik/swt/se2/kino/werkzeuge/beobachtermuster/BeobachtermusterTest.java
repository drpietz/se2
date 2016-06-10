package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.beobachtermuster;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class BeobachtermusterTest extends Beobachtbar implements Beobachter
{

    List<Beobachtbar> benachrichtigungen;
    Beobachtbar b1, b2;

    public BeobachtermusterTest()
    {
        benachrichtigungen = new ArrayList<>();
        
        b1 = new BeobachtermusterTestImpl(this);
        b2 = new BeobachtermusterTestImpl(this);
        
        b1.registriereBeobachter(this);
        b2.registriereBeobachter(this);
    }
    
    @Test
    public void testeMehrereBenachrichtigungen() {
        informiereUeberAenderung();
        assertEquals(benachrichtigungen.size(), 2);
    }

    @Override
    public void reagiereAufAenderung(Beobachtbar beobachtbar)
    {
        benachrichtigungen.add(beobachtbar);
    }

    private class BeobachtermusterTestImpl extends Beobachtbar implements Beobachter
    {
        public BeobachtermusterTestImpl(BeobachtermusterTest b)
        {
            b.registriereBeobachter(this);
        }
        
        @Override
        public void reagiereAufAenderung(Beobachtbar beobachtbar)
        {
            informiereUeberAenderung();
        }
    }

}
