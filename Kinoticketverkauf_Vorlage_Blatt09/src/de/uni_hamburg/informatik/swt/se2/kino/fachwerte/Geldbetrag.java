package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import java.util.regex.Pattern;

public class Geldbetrag
{

    private final int _betrag;
    
    /**
     * Gibt den Geldbetrag mit einem bestimmten Wert zurück
     * @param betrag    
     * @return          
     */
    public static Geldbetrag valueOf(int betrag)
    {
        return new Geldbetrag(betrag);
    }
    
    public static Geldbetrag valueOf(String betrag) throws NumberFormatException {
        betrag = betrag.replace(',', '.');
        
        if (Pattern.compile("\\.\\d{3}").matcher(betrag).find())
        {
            throw new NumberFormatException("Mehr als zwei Nachkommastellen");
        }
        
        double doubleBetrag = Double.parseDouble(betrag);
        int centBetrag = (int) Math.round(doubleBetrag * 100);
        
        return new Geldbetrag(centBetrag);
    }
    
    private Geldbetrag(int betrag)
    {
        _betrag = betrag;
    }
    
    public Geldbetrag add(Geldbetrag geldbetrag)
    {
        return new Geldbetrag(_betrag + geldbetrag._betrag);
    }

    public Geldbetrag subtract(Geldbetrag geldbetrag)
    {
        return new Geldbetrag(_betrag - geldbetrag._betrag);
    }
    
    public Geldbetrag multiply(int faktor)
    {
        return new Geldbetrag(faktor * _betrag);
    }
    
    public int toEurocent()
    {
        return _betrag;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Geldbetrag)
        {
            Geldbetrag g = (Geldbetrag) obj;
            return _betrag == g._betrag;
        }
        else
        {
            return false;
        }
    }

    @Override
    public int hashCode()
    {
        return _betrag;
    }

    @Override
    public String toString()
    {
        return String.format("%.02f €", _betrag / 100d);
    }
    
}
