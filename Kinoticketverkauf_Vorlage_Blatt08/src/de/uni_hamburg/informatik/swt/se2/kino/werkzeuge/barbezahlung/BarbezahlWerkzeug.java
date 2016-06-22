package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barbezahlung;

import java.util.regex.Pattern;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class BarbezahlWerkzeug
{

    private boolean result = false;
    
    public BarbezahlWerkzeug(int eurocent) {
        BarbezahlWerkzeugUI ui = new BarbezahlWerkzeugUI();
        
        // Zu zahlenden Betrag anzeigen
        ui.getBetragsLabel().setText(formatiereBetrag(eurocent));
        ui.getRestbetragsLabel().setText(formatiereBetrag(eurocent));
        
        // Aktion bei Abbrechen Knopf: Dialog schließen
        ui.getAbbrechenButton().addActionListener(e -> ui.getDialog().dispose());
        
        // Aktion bei Bestätigen Knopf: Zustand auf erfolgreich setzen und Dialog schließen
        ui.getBestaetigenButton().addActionListener(e -> {
            result = true;
            ui.getDialog().dispose();
        });
        
        // Aktion wenn Betrag eingegeben wurde
        ui.getGegebenTextField().getDocument().addDocumentListener(new DocumentListener()
        {
            private void aktualisiereAnzeige()
            {
                boolean bezahlt = false;
                
                try {
                    int eingegeben = parseBetrag(ui.getGegebenTextField().getText());
                    int restbetrag = eurocent - eingegeben;
                    ui.getRestbetragsLabel().setText(formatiereBetrag(restbetrag));
                    
                    if (restbetrag > 0)
                        bezahlt = false;
                    else
                        bezahlt = true;
                } catch (NumberFormatException err) {
                    bezahlt = false;
                } finally {
                    ui.getBestaetigenButton().setEnabled(bezahlt);
                }
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) { aktualisiereAnzeige(); }
            
            @Override
            public void insertUpdate(DocumentEvent e) { aktualisiereAnzeige(); }
            
            @Override
            public void changedUpdate(DocumentEvent e) { aktualisiereAnzeige(); }
        });
        
        ui.getDialog().setVisible(true);
    }
    
    private String formatiereBetrag(int eurocent) {
        return String.format("%.02f €", eurocent / 100d);
    }
    
    private int parseBetrag(String betrag) throws NumberFormatException {
        if (betrag.isEmpty())
            return 0;
        
        betrag = betrag.replace(',', '.');
        
        if (Pattern.compile("\\.\\d{3}").matcher(betrag).find())
            throw new NumberFormatException("Mehr als 2 Nachkommastellen");
        
        double doubleBetrag = Double.parseDouble(betrag);
        int centBetrag = (int) Math.round(doubleBetrag * 100);
        
        return centBetrag;
    }
    
    public boolean warErfolgreich() {
        return result;
    }
    
}
