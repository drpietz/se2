package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barbezahlung;

import java.util.regex.Pattern;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Die Klasse BarbezahlWerkzeug zur Unterstüzung von Barzahlungen
 */
public class BarbezahlWerkzeug
{

	private BarbezahlWerkzeugUI _ui;
    private boolean result = false;
    
    /**
     * Initialisiert ein neues BarbezahlWerkzeug zur Abrechnung eines bestimmten Betrags
     * @param eurocent Zu bezahlender Betrag in Eurocent
     */
    public BarbezahlWerkzeug(int eurocent) {
        _ui = new BarbezahlWerkzeugUI();
        
        // Zu zahlenden Betrag anzeigen
        setzeRestbetragLabel(eurocent);
        setzeBetragLabel(eurocent);
        
        // Aktion bei Abbrechen Knopf: Dialog schließen
        _ui.getAbbrechenButton().addActionListener(e -> _ui.getDialog().dispose());
        
        // Aktion bei Bestätigen Knopf: Zustand auf erfolgreich setzen und Dialog schließen
        _ui.getBestaetigenButton().addActionListener(e -> {
            result = true;
            _ui.getDialog().dispose();
        });
        
        // Aktion wenn Betrag eingegeben wurde
        _ui.getGegebenTextField().getDocument().addDocumentListener(new DocumentListener()
        {
            private void aktualisiereAnzeige()
            {
                String eingegeben = _ui.getGegebenTextField().getText();
                boolean bezahlt = false;
                
                int gegeben;
                if (istGueltigerBetrag(eingegeben))
                {
                    gegeben = parseBetrag(eingegeben);
                    int restbetrag = eurocent - gegeben;
                    
                    if (restbetrag > 0)
                    {
                        bezahlt = false;
                    }
                    else
                    {
                        bezahlt = true;
                    }
                    
                    setzeRestbetragLabel(restbetrag);
                }
                else if (eingegeben.isEmpty())
                {
                    setzeRestbetragLabel(eurocent);
                }
                
                _ui.getBestaetigenButton().setEnabled(bezahlt);
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) { aktualisiereAnzeige(); }
            
            @Override
            public void insertUpdate(DocumentEvent e) { aktualisiereAnzeige(); }
            
            @Override
            public void changedUpdate(DocumentEvent e) { aktualisiereAnzeige(); }
        });
    }
    
    private void setzeRestbetragLabel(int eurocent) {
    	_ui.getRestbetragsLabel().setText(formatiereBetrag(eurocent));
    }
    
    private void setzeBetragLabel(int eurocent) {
    	_ui.getBetragsLabel().setText(formatiereBetrag(eurocent));
    }
    
    private String formatiereBetrag(int eurocent) {
        return String.format("%.02f €", eurocent / 100d);
    }
    
    private boolean istGueltigerBetrag(String betrag)
    {
        return Pattern.matches("^\\d{1,5}(?:(?:,|\\.)\\d{1,2})?$", betrag);
    }
    
    /**
     * @require istGueltigerBetrag(betrag)
     */
    private int parseBetrag(String betrag) {
        assert istGueltigerBetrag(betrag) : "Vorbedingung verletzt: istGueltigerBetrag(betrag)";
        
        betrag = betrag.replace(',', '.');
        
        double doubleBetrag = Double.parseDouble(betrag);
        int centBetrag = (int) Math.round(doubleBetrag * 100);
        
        return centBetrag;
    }
    
    /**
     * Führt den Bezahlvorgang aus
     * @return true genau dann wenn die Zahlung erfolgreich war
     */
    public boolean berechne() {
        _ui.getDialog().setVisible(true);
        
        return result;
    }
    
}
