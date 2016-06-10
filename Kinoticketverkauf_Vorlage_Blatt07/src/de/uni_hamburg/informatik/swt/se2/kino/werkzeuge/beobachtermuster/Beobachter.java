package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.beobachtermuster;


/**
 * Implementation von Observer des Observable-Patterns
 */
public interface Beobachter
{
    /**
     * Wird aufgerufen, wenn ein beobachtetes eine Änderung vorgenommen hat
     * 
     * @param beobachtbar   Beobachtbar der eine Änderung vorgenommen hat
     */
    void reagiereAufAenderung(Beobachtbar beobachtbar);
}
