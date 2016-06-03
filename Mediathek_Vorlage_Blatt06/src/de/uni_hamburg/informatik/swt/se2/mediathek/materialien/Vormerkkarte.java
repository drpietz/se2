package de.uni_hamburg.informatik.swt.se2.mediathek.materialien;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;

public class Vormerkkarte
{

    private final Kunde _kunde;
    private final Medium _medium;

    public Vormerkkarte(Kunde kunde, Medium medium)
    {
        _kunde = kunde;
        _medium = medium;
    }

    public Kunde getVormerker()
    {
        return _kunde;
    }

    public Medium getMedium()
    {
        return _medium;
    }

}
