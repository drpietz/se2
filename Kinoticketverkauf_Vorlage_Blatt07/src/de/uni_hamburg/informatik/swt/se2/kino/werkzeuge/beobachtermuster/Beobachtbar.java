package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.beobachtermuster;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation von Observable des Observable-Patterns
 */
public abstract class Beobachtbar
{
    private List<Beobachter> _beobachter;

    /**
     * Initialisert den Beobachtbar
     */
    public Beobachtbar()
    {
        _beobachter = new ArrayList<>();
    }

    /**
     * Fügt einen zu benachrichtigenden Beobachter hinzu
     * 
     * @param beobachter    Neuer Beobachter dieser Klasse
     */
    public void registriereBeobachter(Beobachter beobachter)
    {
        _beobachter.add(beobachter);
    }

    /**
     * Benachrichtigt die Beobachter dieser Klasse über eine Änderung
     */
    public void informiereUeberAenderung()
    {
        for (Beobachter beobachter : _beobachter)
        {
            beobachter.reagiereAufAenderung(this);
        }
    }
}
