package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barbezahlung;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class BarbezahlWerkzeug
{

    private boolean result = false;
    
    public static void main(String[] args)
    {
        new BarbezahlWerkzeug(1234);
    }
    
    public BarbezahlWerkzeug(int eurocent) {
        BarbezahlWerkzeugUI ui = new BarbezahlWerkzeugUI();
        ui.getBetragsLabel().setText(formatiereBetrag(eurocent));
        
        ui.getAbbrechenButton().addActionListener(e -> ui.getDialog().dispose());
        
        ui.getBestaetigenButton().addActionListener(e -> {
            result = true;
            ui.getDialog().dispose();
        });
        
        ui.getGegebenTextField().getDocument().addDocumentListener(new DocumentListener()
        {
            @Override
            public void removeUpdate(DocumentEvent e)
            {
                aktualisiereAnzeige();
            }
            
            @Override
            public void insertUpdate(DocumentEvent e)
            {
                aktualisiereAnzeige();
            }
            
            @Override
            public void changedUpdate(DocumentEvent e)
            {
                aktualisiereAnzeige();
            }
            
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
        });
        
        ui.getDialog().setVisible(true);
    }
    
    private String formatiereBetrag(int eurocent) {
        return String.format("%.02f €", eurocent / 100d);
    }
    
    private int parseBetrag(String betrag) throws NumberFormatException {
        for (int i = 0; i < betrag.length() - 1; i++) {
            if (betrag.charAt(i) == ',') {
                betrag = betrag.substring(0, i) + "." + betrag.substring(i + 1);
            }
        }
        
        double doubleBetrag = Double.parseDouble(betrag);
        int centBetrag = (int) Math.round(doubleBetrag * 100);
        
        return centBetrag;
    }
    
    public boolean warErfolgreich() {
        return result;
    }
    
}
