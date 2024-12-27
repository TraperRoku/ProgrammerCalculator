package gui;

import main.Calculator;

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

    public String updateFromDisplay(String value, int BITS, int BITSCurrentTypeWord) {
        String cleanBinary = value.replace(" ", "");

        StringBuilder formatted = new StringBuilder();

        // If the input is empty, return a single zero
        if (cleanBinary.isEmpty()) {
            return "0";
        }

        if (BITS < cleanBinary.length()) {
            // Need to truncate - keep rightmost BITS
            cleanBinary =  cleanBinary.substring(cleanBinary.length() - BITS);

            for (int i = BITS; i >0; i--) {
                if ( i % BITS_PER_GROUP == 0) {
                    formatted.append(' ');
                }
                formatted.append(cleanBinary.charAt((cleanBinary.length()) - i));
            }

            textField.setText(formatted.toString());
            return cleanBinary;

        } else {
            // If our binary is shorter than current word type, pad it first
            if (cleanBinary.length() < BITSCurrentTypeWord) {
                cleanBinary = "0".repeat(BITSCurrentTypeWord - cleanBinary.length()) + cleanBinary;
            }

            char signBit = cleanBinary.charAt(0);

            if (signBit == '1') {
                cleanBinary= "1".repeat(BITS - cleanBinary.length()) + cleanBinary;

                for (int i = 0; i < BITS; i++) {
                    if (i > 0 && i % BITS_PER_GROUP == 0) {
                        formatted.append(' ');
                    }
                    formatted.append(cleanBinary.charAt(i));
                }

                textField.setText(formatted.toString());

            } else {

                for (int i = BITS; i >0; i--) {
                    if ( i % BITS_PER_GROUP == 0) {
                        formatted.append(' ');
                    }
                    formatted.append(cleanBinary.charAt((cleanBinary.length()) - i));
                }

                textField.setText(formatted.toString());

                while (cleanBinary.length() > 1 && cleanBinary.charAt(0) == '0') {
                    cleanBinary = cleanBinary.substring(1);
                }
            }
            return cleanBinary;
        }
    }



}