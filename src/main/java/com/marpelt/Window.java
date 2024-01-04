package com.marpelt;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Objects;

public class Window extends JFrame {

    public Window() {
        super("Number system Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        String[] selectionPossibilities = {"-", "Binary", "Decimal", "Hexadecimal", "Ascii"};

        JComboBox<String> selectionBox1 = new JComboBox<>(selectionPossibilities);
        JComboBox<String> selectionBox2 = new JComboBox<>(selectionPossibilities);

        JTextArea textField = new JTextArea();
        JLabel resultContent = new JLabel();

        JButton button = readInput(selectionBox1, selectionBox2, textField, resultContent);

        add(new JLabel(" Start"));
        add(selectionBox1);
        add(new JLabel(" Result"));
        add(selectionBox2);
        add(new JLabel(" Input:"));
        add(textField);
        add(new JLabel(" Converted (Clipboard):"));
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

        JButton button = new JButton("Convert");

        button.addActionListener(e -> {

            Converter.convertToBinary((String) Objects.requireNonNull(selectionBox1.getSelectedItem()), textField.getText());
            resultText.setText(Converter.convertToResult((String) Objects.requireNonNull(Objects.requireNonNull(selectionBox2).getSelectedItem())));
            /*if (Converter.setErrorMessage(textField.getText()).isEmpty()) {
                resultText.setText(Converter.setErrorMessage(textField.getText()));
            }*/
            resultText.setForeground(new Color(0, 255, 0));

            //TODO: Set color for error/correct progress

            StringSelection stringSelection = new StringSelection(Converter.convertToResult((String) Objects.requireNonNull(selectionBox2).getSelectedItem()));
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);

        });

        return button;

    }
}