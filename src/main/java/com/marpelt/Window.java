package com.marpelt;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class Window extends JFrame {

    String[] selectionPossibilities = {"-", "Binär", "Dezimal", "Hexadezimal", "ASCII"};
    private final JLabel resultContent;

    public Window() {
        super("Zahlensystem Konverter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        String[] selection1 = selectionPossibilities;
        JComboBox<String> selectionBox1 = new JComboBox<>(selection1);

        String[] selection2 = selectionPossibilities;
        JComboBox<String> selectionBox2 = new JComboBox<>(selection2);

        JTextArea textField = new JTextArea();
        resultContent = new JLabel();

        JButton button = readInput(selectionBox1, selectionBox2, textField, resultContent);


        add(new JLabel(" Von"));
        add(selectionBox1);
        add(new JLabel(" Nach"));
        add(selectionBox2);
        add(new JLabel(" Eingabe:"));
        add(textField);
        add(new JLabel(" Ausgabe:"));
        add(resultContent);
        add(new JLabel());
        add(button);

        ImageIcon icon = new ImageIcon("src/main/resources/converter_Logo.jpg");
        setIconImage(icon.getImage());

        setSize(400, 200);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private static JButton readInput(JComboBox<String> selectionBox1, JComboBox<String> selectionBox2, JTextArea textField, JLabel resultText) {

        JButton button = new JButton("Konvertieren");

        button.addActionListener(e -> {

            String selectedOption1 = (String) selectionBox1.getSelectedItem();

            String selectedOption2 = (String) selectionBox2.getSelectedItem();

            String inputText = textField.getText();

            assert selectedOption1 != null;
            Converter.convert(selectedOption1, selectedOption2, inputText);
            resultText.setText(Converter.response());

            if (Converter.response().contains("Error") || Converter.response().contains("java.lang")) {
                resultText.setForeground(Color.red);
            } else {
                resultText.setForeground(Color.green);
            }

            StringSelection stringSelection = new StringSelection(Converter.response());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);

        });

        return button;
    }
}