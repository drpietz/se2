package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barbezahlung;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class BarbezahlWerkzeugUI
{

    private JDialog _dialog;
    private JLabel _betragsLabel;
    private JTextField _gegebenTextField;
    private JLabel _restbetragsLabel;
    private JButton _abbrechenButton;
    private JButton _bestaetigenButton;

    /**
     * Initialisiert die zum BarbezahlWerkzeug zugehörige UI
     */
    public BarbezahlWerkzeugUI()
    {
        _dialog = new JDialog();
        _dialog.setTitle("Barbezahlung");
        _dialog.setLayout(new BorderLayout());

        
        
        // ----- CONTENT -----
        JPanel content = new JPanel();
        content.setLayout(new GridLayout(3, 2));
        content.setBorder(new EmptyBorder(10, 20, 10, 20));

        _betragsLabel = new JLabel();
        content.add(new JLabel("Betrag:"));
        content.add(_betragsLabel);

        _gegebenTextField = new JTextField();
        _gegebenTextField.setColumns(10);
        JPanel gegebenPanel = new JPanel(new FlowLayout());
        gegebenPanel.add(_gegebenTextField);
        gegebenPanel.add(new JLabel("€"));
        content.add(new JLabel("Gegeben:"));
        content.add(gegebenPanel);

        _restbetragsLabel = new JLabel();
        content.add(new JLabel("Restbetrag:"));
        content.add(_restbetragsLabel);

        _dialog.add(content, BorderLayout.CENTER);

        
        
        // ----- BUTTONS -----
        JPanel buttonPanel = new JPanel(new FlowLayout());

        _abbrechenButton = new JButton("Abbrechen");
        buttonPanel.add(_abbrechenButton);

        _bestaetigenButton = new JButton("Bestätigen");
        _bestaetigenButton.setEnabled(false);
        buttonPanel.add(_bestaetigenButton);

        _dialog.add(buttonPanel, BorderLayout.SOUTH);

        
        
        // ----- DIALOG 2 -----
        _dialog.setModal(true);
        _dialog.pack();
    }

    /**
     * @return Label, in dem der zu zahlende Betrag steht
     */
    public JLabel getBetragsLabel()
    {
        return _betragsLabel;
    }

    /**
     * @return TextField, in das der bezahlte Betrag eingegeben wird
     */
    public JTextField getGegebenTextField()
    {
        return _gegebenTextField;
    }

    /**
     * @return Label, das den noch zu zahlenden Betrag angibt
     */
    public JLabel getRestbetragsLabel()
    {
        return _restbetragsLabel;
    }

    /**
     * @return Button zum Abbrechen des Bezahlvorgangs
     */
    public JButton getAbbrechenButton()
    {
        return _abbrechenButton;
    }

    /**
     * @return Button der Gedrückt wird, wenn die Zahlung erfolgreich war
     */
    public JButton getBestaetigenButton()
    {
        return _bestaetigenButton;
    }

    /**
     * @return Der Dialog der die UI umfasst
     */
    public JDialog getDialog()
    {
        return _dialog;
    }

}
