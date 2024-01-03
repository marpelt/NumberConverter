package com.marpelt;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;
import java.util.Objects;

public class Window extends JFrame {

    public Window() {
        super("Zahlensystem Konverter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        String[] selectionPossibilities = {"-", "Binary", "Decimal", "Hexadecimal", "Ascii"};

        JComboBox<String> selectionBox1 = new JComboBox<>(selectionPossibilities);
        JComboBox<String> selectionBox2 = new JComboBox<>(selectionPossibilities);

        JTextArea textField = new JTextArea();
        JLabel resultContent = new JLabel();

        JButton button = readInput(selectionBox1, selectionBox2, textField, resultContent);

        add(new JLabel(" Von"));
        add(selectionBox1);
        add(new JLabel(" Nach"));
        add(selectionBox2);
        add(new JLabel(" Eingabe:"));
        add(textField);
        add(new JLabel(" Ausgabe (Zwischenablage):"));
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

            Converter.convertToBinary((String) Objects.requireNonNull(selectionBox1.getSelectedItem()), textField.getText());
            resultText.setText(Converter.convertToResult((String) Objects.requireNonNull(selectionBox2).getSelectedItem()));
            resultText.setText(Converter.setErrorMessage());

            //TODO: Set color for error/correct progress

            StringSelection stringSelection = new StringSelection(Converter.convertToResult((String) Objects.requireNonNull(selectionBox2).getSelectedItem()));
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);

        });

        return button;

    }
}