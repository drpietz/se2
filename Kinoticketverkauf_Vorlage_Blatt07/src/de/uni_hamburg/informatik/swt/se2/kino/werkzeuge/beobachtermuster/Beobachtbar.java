package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.beobachtermuster;

import java.util.ArrayList;
import java.util.List;

public abstract class Beobachtbar
{
    private List<Beobachter> _beobachter;

    public Beobachtbar()
    {
        _beobachter = new ArrayList<>();
    }

    public void registriereBeobachter(Beobachter beobachter)
    {
        _beobachter.add(beobachter);
    }

    public void informiereUeberAenderung()
    {
        for (Beobachter beobachter : _beobachter)
        {
            beobachter.reagiereAufAenderung(this);
        }
    }
}
