package gui;

import main.Calculator;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.math.BigInteger;

public class BinaryResultFormatter {
    private final JTextPane textField;

    private final int BITS_PER_GROUP = 4;

    public BinaryResultFormatter(JTextPane textField) {
        this.textField = textField;
        setupTextField();
    }

    private void setupTextField() {
        textField.setFont(new Font("Consolas", Font.PLAIN, 18));

        textField.setEditable(false);
        textField.setBackground(Color.WHITE);
        textField.setBorder(BorderFactory.createCompoundBorder(
                textField.getBorder(),
                BorderFactory.createEmptyBorder(2, 2, 2, 2)
        ));
        StyledDocument doc = textField.getStyledDocument();
        SimpleAttributeSet alignment = new SimpleAttributeSet();
        StyleConstants.setAlignment(alignment, StyleConstants.ALIGN_RIGHT);
        doc.setParagraphAttributes(0, doc.getLength(), alignment, false);
    }

    public void updateDisplay(BigInteger value, int BITS) {
        String binaryStr;

        BigInteger mask = BigInteger.ONE.shiftLeft(BITS).subtract(BigInteger.ONE);
        value = value.and(mask);


        binaryStr = value.toString(2);
        binaryStr = String.format("%" + BITS + "s", binaryStr).replace(' ', '0');

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
        String cleanBinary = value.replace(" ", ""); // Remove spaces from the input

        if (cleanBinary.isEmpty()) {
            textField.setText("0");
            return "0";
        }

        if (cleanBinary.length() > BITS) {
            cleanBinary = cleanBinary.substring(cleanBinary.length() - BITS);
        } else {
            if (cleanBinary.length() < BITSCurrentTypeWord) {
                cleanBinary = "0".repeat(BITS - cleanBinary.length()) + cleanBinary;
            }
        }

        char signBit = cleanBinary.charAt(0);
        if (signBit == '1' && cleanBinary.length() < BITS) {
            cleanBinary = "1".repeat(BITS - cleanBinary.length()) + cleanBinary;
        }

        String cleanBinaryCopy = new String(cleanBinary);

        while ((cleanBinaryCopy.length() > 1 && cleanBinaryCopy.charAt(0) == '0')) {
            cleanBinaryCopy = cleanBinaryCopy.substring(1);
        }

        while ((cleanBinary.length() > 1 && cleanBinary.charAt(0) == '0')&&(cleanBinary.length() !=BITS)) {
            cleanBinary = cleanBinary.substring(1);
        }


        StringBuilder formatted = new StringBuilder();
        for (int i = 0; i < cleanBinary.length(); i++) {
            if (i > 0 && i % BITS_PER_GROUP == 0) {
                formatted.append(' '); // Add a space after every 4 bits
            }
            formatted.append(cleanBinary.charAt(i));
        }

        textField.setText(formatted.toString());

        return cleanBinaryCopy;
    }




}