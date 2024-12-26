package gui;

import main.Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

public class JavaCalculator {

    Calculator calculator = new Calculator(0);

    private BigInteger total1 = BigInteger.ZERO;
    private String operator = "";
    private boolean isNewLine = false;
    private boolean isOperatorPressed = false;
    private String placeHolder = "0";
    private String lastOperator = "";
    private BigInteger lastNumber = BigInteger.ZERO;

    private Calculator.TypeNumber currentTypeNumber = Calculator.TypeNumber.Dec;
    private Calculator.TypeWord currentTypeWord = Calculator.TypeWord.Qword;

    private JPanel JavaCalculator;
    private JTextField binaryResult;
    private BinaryResultFormatter binaryFormatter;
    private JButton andButton;
    private JButton rshButton;
    private JButton xorButton;
    private JButton roRButton;
    private JButton button5;
    private JButton modButton;
    private JButton button7;
    private JButton button8;
    private JButton roLButton;
    private JButton orButton;
    private JButton lshButton;
    private JButton notButton;
    private JButton mButton;
    private JButton sqrtButton;
    private JButton button15;
    private JButton a1XButton;
    private JButton rówwnaSieButton;
    private JButton MSButton;
    private JButton cButton1;
    private JButton a9Button;
    private JButton a6Button;
    private JButton a3Button;
    private JButton button24;
    private JButton MRButton;
    private JButton CEButton;
    private JButton a8Button;
    private JButton a5Button;
    private JButton a2Button;
    private JButton MCButton;
    private JButton button32;
    private JButton a7Button;
    private JButton a4Button;
    private JButton a1Button;
    private JButton a0Button;
    private JButton aButton;
    private JButton bButton;
    private JButton cButton;
    private JButton dButton;
    private JButton eButton;
    private JButton fButton;
    private JTextField displayResult;
    private JButton fieldPlus;
    private JButton fieldMinus;
    private JButton fieldMultiplication;
    private JButton fieldDivide;
    private JButton fieldPlusOrMinus;
    private JButton fieldMPlus;
    private JRadioButton hexRadioButton;
    private JRadioButton decRadioButton;
    private JRadioButton octRadioButton;
    private JRadioButton binRadioButton;
    private JRadioButton qwordRadioButton;
    private JRadioButton dwordRadioButton;
    private JRadioButton wordRadioButton;
    private JRadioButton bajtRadioButton;

    public JavaCalculator() {

        binaryFormatter = new BinaryResultFormatter(binaryResult);
        binaryFormatter.updateDisplay(BigInteger.ZERO, calculator.getBaseWord(currentTypeWord));

        decRadioButton.setSelected(true);
        qwordRadioButton.setSelected(true);

        displayResult.setText(placeHolder);
        displayResult.setForeground(java.awt.Color.GRAY);

        displayResult.setLayout(new FlowLayout(FlowLayout.RIGHT));
        binaryResult.setLayout(new FlowLayout(FlowLayout.RIGHT));

        displayResult.setHorizontalAlignment(JTextField.RIGHT);
        binaryResult.setHorizontalAlignment(JTextField.RIGHT);

        displayResult.setFont(new Font("Arial", Font.BOLD, 24));
        displayResult.setPreferredSize(new Dimension(500, 40));

        a0Button.addActionListener(e -> appendNumber("0"));
        a1Button.addActionListener(e -> appendNumber("1"));
        a2Button.addActionListener(e -> appendNumber("2"));
        a3Button.addActionListener(e -> appendNumber("3"));
        a4Button.addActionListener(e -> appendNumber("4"));
        a5Button.addActionListener(e -> appendNumber("5"));
        a6Button.addActionListener(e -> appendNumber("6"));
        a7Button.addActionListener(e -> appendNumber("7"));
        a8Button.addActionListener(e -> appendNumber("8"));
        a9Button.addActionListener(e -> appendNumber("9"));

        hexRadioButton.addActionListener(e -> updateBase(Calculator.TypeNumber.Hex));
        decRadioButton.addActionListener(e -> updateBase(Calculator.TypeNumber.Dec));
        octRadioButton.addActionListener(e -> updateBase(Calculator.TypeNumber.Oct));
        binRadioButton.addActionListener(e -> updateBase(Calculator.TypeNumber.Bin));

        qwordRadioButton.addActionListener(e -> updateWord(Calculator.TypeWord.Qword));
        dwordRadioButton.addActionListener(e -> updateWord(Calculator.TypeWord.Dword));
        wordRadioButton.addActionListener(e -> updateWord(Calculator.TypeWord.Word));
        bajtRadioButton.addActionListener(e -> updateWord(Calculator.TypeWord.Bajt));

        rówwnaSieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((!operator.isEmpty() && !isPlaceholderActive() && !displayResult.getText().isEmpty()) || !lastOperator.isEmpty()) {
                    BigInteger currentValue = new BigInteger(displayResult.getText(), calculator.getBaseValue(currentTypeNumber));
                    BigInteger result = BigInteger.ZERO;

                    if (!lastOperator.isEmpty() && operator.isEmpty()) {
                        operator = lastOperator;
                        currentValue = lastNumber;
                    }

                    switch (operator) {
                        case "+":
                            result = calculator.add(total1, currentValue);
                            break;
                        case "-":
                            result = calculator.subtract(total1, currentValue);
                            break;
                        case "*":
                            result = calculator.multiply(total1, currentValue);
                            break;
                        case "/":
                            if (!currentValue.equals(BigInteger.ZERO)) {
                                result = calculator.divide(total1, currentValue);
                            } else {
                                JOptionPane.showMessageDialog(null, "Nie można dzielić przez zero!");
                                return;
                            }
                            break;
                    }
                    displayResult.setText(calculator.convertNumber(result.toString(), Calculator.TypeNumber.Dec, currentTypeNumber, currentTypeWord));
                    displayResult.setForeground(java.awt.Color.BLACK);
                    binaryFormatter.updateDisplay(result, calculator.getBaseWord(currentTypeWord));
                    lastOperator = operator;
                    operator = "";
                    lastNumber = currentValue;
                    total1 = result;
                    isNewLine = true;
                    isOperatorPressed = false;
                }
            }
        });

        fieldPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPlaceholderActive() && !displayResult.getText().isEmpty()) {
                    BigInteger currentValue = new BigInteger(displayResult.getText(), calculator.getBaseValue(currentTypeNumber));
                    if (!isOperatorPressed) {
                        if (operator.isEmpty()) {
                            total1 = currentValue;
                            operator = "+";
                        } else {
                            total1 = calculator.add(total1, currentValue);
                            displayResult.setText(total1.toString());
                            binaryFormatter.updateDisplay(total1, calculator.getBaseWord(currentTypeWord));
                        }
                        lastNumber = currentValue;
                        isOperatorPressed = true;
                        lastOperator = "+";
                    }
                }
            }
        });


        fieldMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPlaceholderActive() && !displayResult.getText().isEmpty()) {
                    BigInteger currentValue = new BigInteger(displayResult.getText(), calculator.getBaseValue(currentTypeNumber));
                    if (!isOperatorPressed) {
                        if (operator.isEmpty()) {
                            total1 = currentValue;
                            operator = "-";

                        } else {
                            total1 = calculator.subtract(total1, currentValue);
                            displayResult.setText(total1.toString());
                            binaryFormatter.updateDisplay(total1,calculator.getBaseWord(currentTypeWord));

                        }
                        lastNumber = total1;
                        isOperatorPressed = true;
                        lastOperator = "-";

                    }

                }


            }
        });
        fieldMultiplication.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!isPlaceholderActive() && !displayResult.getText().isEmpty()) {
                    BigInteger currentValue = new BigInteger(displayResult.getText(), calculator.getBaseValue(currentTypeNumber));
                    if (!isOperatorPressed) {
                        if (operator.isEmpty()) {
                            total1 = currentValue;
                            operator = "*";

                        } else {
                            total1 = calculator.multiply(total1, currentValue);
                            displayResult.setText(total1.toString());
                            binaryFormatter.updateDisplay(total1,calculator.getBaseWord(currentTypeWord));

                        }
                        lastNumber = total1;
                        isOperatorPressed = true;
                        lastOperator = "*";

                    }

                }
            }
        });
        fieldDivide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPlaceholderActive() && !displayResult.getText().isEmpty()) {
                    BigInteger currentValue = new BigInteger(displayResult.getText(), calculator.getBaseValue(currentTypeNumber));
                    if (!isOperatorPressed) {
                        if (operator.isEmpty()) {
                            total1 = currentValue;
                            operator = "/";

                        } else {
                            total1 = calculator.divide(total1, currentValue);
                            displayResult.setText(total1.toString());
                            binaryFormatter.updateDisplay(total1,calculator.getBaseWord(currentTypeWord));

                        }
                        lastNumber = total1;
                        isOperatorPressed = true;
                        lastOperator = "/";

                    }
                }
            }
        });

        //usuniecie danej czynnosci czyli 5 + 4 (CE) -> 5 + ...
        CEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayPlaceholder();
                binaryFormatter.updateDisplay(BigInteger.ZERO,calculator.getBaseWord(currentTypeWord));
            }
        });

        cButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                total1 = BigInteger.ZERO;
                displayPlaceholder();
                lastOperator = "";
                binaryFormatter.updateDisplay(BigInteger.ZERO, calculator.getBaseWord(currentTypeWord));
            }
        });
    }



    private void updateWord(Calculator.TypeWord typeWord) {
        String currentText = displayResult.getText();

        if (!isPlaceholderActive() && !currentText.isEmpty()) {
            int baseWord = calculator.getBaseWord(typeWord);
            String s = calculator.convertNumber(currentText, currentTypeNumber, Calculator.TypeNumber.Dec, currentTypeWord);
            BigInteger value = new BigInteger(s);
            binaryFormatter.updateDisplay(value, baseWord);
            displayResult.setText(calculator.convertNumber(value.toString(), currentTypeNumber, currentTypeNumber, typeWord));
        } else {
            int baseWord = calculator.getBaseWord(typeWord);
            binaryFormatter.updateDisplay(BigInteger.ZERO, baseWord);
        }

        currentTypeWord = typeWord;
    }

    private void updateBase(Calculator.TypeNumber typeNumber) {
        String currentText = displayResult.getText();

        if (!isPlaceholderActive() && !currentText.isEmpty()) {

            String convertedNumber = calculator.convertNumber(currentText, currentTypeNumber, typeNumber,currentTypeWord);
            displayResult.setText(convertedNumber);
        }

        currentTypeNumber = typeNumber;
    }

    private void appendNumber(String number) {
        if (isPlaceholderActive() || isOperatorPressed || isNewLine) {
            displayResult.setText(number);
            displayResult.setForeground(java.awt.Color.BLACK);
            isOperatorPressed = false;
            isNewLine = false;
            BigInteger currentValue = new BigInteger(number, calculator.getBaseValue(currentTypeNumber));
            binaryFormatter.updateDisplay(currentValue, calculator.getBaseWord(currentTypeWord));
        } else {
            displayResult.setText(displayResult.getText() + number);
            BigInteger currentValue = new BigInteger(displayResult.getText(), calculator.getBaseValue(currentTypeNumber));
            binaryFormatter.updateDisplay(currentValue, calculator.getBaseWord(currentTypeWord));
        }
        lastNumber = new BigInteger(displayResult.getText(), calculator.getBaseValue(currentTypeNumber));
    }

    private void displayPlaceholder() {
        displayResult.setText(placeHolder);
        displayResult.setForeground(java.awt.Color.GRAY);
    }

    private boolean isPlaceholderActive() {
        return displayResult.getText().equals(placeHolder);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("JavaCalculator");
        frame.setSize(400, 600);
        frame.setContentPane(new JavaCalculator().JavaCalculator);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
