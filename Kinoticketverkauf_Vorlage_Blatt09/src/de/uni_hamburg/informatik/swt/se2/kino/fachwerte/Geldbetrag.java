package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Geldbetrag implements Comparable<Geldbetrag>
{
    private static final Pattern EINGABE_PATTERN = Pattern.compile("^(-?\\d{1,4}(?:[,.]\\d{1,2})?)\\s*€?$");

    private final int _betrag;
    
    /**
     * Gibt den Geldbetrag mit einem bestimmten Wert zurück
     * @param betrag Geldbetrag in Eurocent
     * @return Geldbetrag, der dem eingegeben Betrag entspricht
     */
    public static Geldbetrag valueOf(int betrag)
    {
        return new Geldbetrag(betrag);
    }
    
    /**
     * Überprüft, ob ein Geldbetrag gültig ist
     * @param betrag Betrag als String bla bla ^(-?\\d{1,4}(?:[,.]\\d{1,2})?)\\s*€?$
     * @return True, gdw. es sich um einen gültigen Betrag handelt.
     */
    public static boolean istGueltigerBetrag(String betrag)
    {
        return EINGABE_PATTERN.matcher(betrag).matches();
    }
    
    
    /**
     * Parst einen String Geldbetrag
     * @param betrag String, der den Betrag enthält
     * @return Geldbetrag, der der Eingabe entspricht.
     * 
     * @require istGueltigerBetrag(betrag)
     */
    public static Geldbetrag valueOf(String betrag) {
        assert istGueltigerBetrag(betrag) : "Vorbedingung verletzt: istGueltigerBetrag(betrag)";
        
        Matcher m = EINGABE_PATTERN.matcher(betrag);
        m.find();
        betrag = m.group(1); // Eurosymbol entfernen
        
        betrag = betrag.replace(',', '.');
        
        double doubleBetrag = Double.parseDouble(betrag);
        int centBetrag = (int) Math.round(doubleBetrag * 100);
        
        return new Geldbetrag(centBetrag);
    }
    
    private Geldbetrag(int betrag)
    {
        _betrag = betrag;
    }


    /**
     * Addiert einen anderen Geldbetrag von diesem
     * @param geldbetrag Geldbetrag, der addiert wird
     * @return Ergebnis der Addition
     * 
     * @require istAddierenMoeglich(geldbetrag)
     */
    public Geldbetrag add(Geldbetrag geldbetrag)
    {
        assert istAddierenMoeglich(geldbetrag) : "Vorbedingung verletzt: istAddierenMoeglich(geldbetrag)";
        
        return new Geldbetrag(_betrag + geldbetrag._betrag);
    }
    
    /**
     * Wertet aus, ob Addition eines Betrags moeglich ist
     * @param geldbetrag Geldbetrag, der addiert wird
     * @return Ob die Addition möglich ist
     */
    public boolean istAddierenMoeglich(Geldbetrag geldbetrag)
    {
        return ((istPositiv() != geldbetrag.istPositiv()) || istPositiv() == (_betrag + geldbetrag._betrag >= 0));
    }

    /**
     * Subtrahiert einen anderen Geldbetrag von diesem
     * @param geldbetrag Geldbetrag, der subtrahiert wird
     * @return Ergebnis der Subtraktion
     * 
     * @require istSubtrahierenMoeglich(geldbetrag)
     */
    public Geldbetrag subtract(Geldbetrag geldbetrag)
    {
        assert istSubtrahierenMoeglich(geldbetrag) : "Vorbedingung verletzt: istSubtrahierenMoeglich(geldbetrag)";
        
        return new Geldbetrag(_betrag - geldbetrag._betrag);
    }
    
    /**
     * Wertet aus, ob Subtraktion eines Betrags moeglich ist
     * @param geldbetrag Geldbetrag, der subtrahiert wird
     * @return Ob die Subtraktion möglich ist
     */
    public boolean istSubtrahierenMoeglich(Geldbetrag geldbetrag)
    {
        return ((istPositiv() == geldbetrag.istPositiv()) || istPositiv() == (_betrag - geldbetrag._betrag >= 0));
    }
    
    /**
     * Multipliziert den Betrag mit einem anderen Geldbetrag
     * @param faktor Faktor, mit dem multipliziert wird
     * @return Ergebnis der Multiplikation
     * 
     * @require istMultiplizierenMoeglich(faktor)
     */
    public Geldbetrag multiply(int faktor)
    {
        assert istMultiplizierenMoeglich(faktor) : "Vorbedingung verletzt: istMultiplizierenMoeglich(faktor)";
        
        return new Geldbetrag(faktor * _betrag);
    }
    
    /**
     * Testet, ob Multiplikation mit einem Faktor >= 0 erlaubt ist
     * @param faktor Faktor mit dem multipliziert wird.
     * @return Ob multiplizieren mit dem Faktor erlaubt ist
     */
    public boolean istMultiplizierenMoeglich(int faktor)
    {
        if (faktor < 0)
        {
            return false;
        }
        else if (_betrag == 0)
        {
            return true;
        }
        else if (istPositiv())
        {
            return (Integer.MAX_VALUE / _betrag) >= faktor;
        }
        else
        {
            return (Integer.MIN_VALUE / _betrag) >= faktor;
        }
    }
    
    /**
     * Gibt den Geldbetrag in Eurocent zurück
     * @return Geldbetrag in Eurocent
     */
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
    
    /**
     * Gibt zurück, ob der Betrag positiv ist
     * @return True, wenn der Betrag >= 0 ist
     */
    public boolean istPositiv()
    {
        return _betrag >= 0;
    }

    @Override
    public int compareTo(Geldbetrag o)
    {
        return _betrag - o._betrag;
    }
    
    /**
     * Wertet aus, ob ein Betrag größer oder gleich einem anderen ist.
     * @param betrag Betrag, mit dem verglichen wird
     * @return True, wenn der Betrag größer gleich dem Vergleichbetrags ist
     */
    public boolean istGroesserGleich(Geldbetrag betrag)
    {
        return compareTo(betrag) > 0;
    }
}
