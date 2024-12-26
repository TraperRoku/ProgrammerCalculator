package gui;

import javax.swing.*;
import java.awt.*;
import java.math.BigInteger;

public class BinaryResultFormatter {
    private final JTextField textField;

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

    public void updateDisplay(BigInteger value, int BITS) { // Accept BigInteger instead of int or long
        String binaryStr;

        // Constrain the value to the specified bit-width using a mask
        BigInteger mask = BigInteger.ONE.shiftLeft(BITS).subtract(BigInteger.ONE);
        value = value.and(mask); // Apply the mask to constrain to BITS

        // Generate the binary string
        binaryStr = value.toString(2); // Convert BigInteger to binary string
        binaryStr = String.format("%" + BITS + "s", binaryStr).replace(' ', '0'); // Pad with zeros

        // Add spacing for readability
        StringBuilder formatted = new StringBuilder();
        for (int i = 0; i < binaryStr.length(); i++) {
            if (i > 0 && i % BITS_PER_GROUP == 0) {
                formatted.append(' ');
            }
            formatted.append(binaryStr.charAt(i));
        }

        textField.setText(formatted.toString());
    }


}