package gui;

import javax.swing.*;
import java.awt.*;

public class BinaryResultFormatter {
    private final JTextField textField;
    private final int BITS = 64;
    private final int BITS_PER_GROUP = 4;

    public BinaryResultFormatter(JTextField textField) {
        this.textField = textField;
        setupTextField();
    }

    private void setupTextField() {
        textField.setFont(new Font("Consolas", Font.PLAIN, 12));
        textField.setHorizontalAlignment(JTextField.LEFT);
        textField.setEditable(false);
        textField.setBackground(Color.WHITE);
        textField.setBorder(BorderFactory.createCompoundBorder(
                textField.getBorder(),
                BorderFactory.createEmptyBorder(2, 2, 2, 2)
        ));
    }

    public void updateDisplay(int value) {

        String binaryStr = String.format("%64s", Integer.toBinaryString(value))
                .replace(' ', '0');

        // Insert spaces between groups of 4 bits
        StringBuilder formatted = new StringBuilder();
        for (int i = 0; i < BITS; i++) {
            if (i > 0 && i % BITS_PER_GROUP == 0) {
                formatted.append(' ');
            }
            formatted.append(binaryStr.charAt(i));
        }

        textField.setText(formatted.toString());
    }
}