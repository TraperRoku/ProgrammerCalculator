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
    private JButton blankButton;
    private JButton button8;
    private JButton roLButton;
    private JButton orButton;
    private JButton lshButton;
    private JButton notButton;
    private JButton MMButton;
    private JButton sqrtButton;
    private JButton procentButton;
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
    private JButton MPField;
    private JRadioButton hexRadioButton;
    private JRadioButton decRadioButton;
    private JRadioButton octRadioButton;
    private JRadioButton binRadioButton;
    private JRadioButton qwordRadioButton;
    private JRadioButton dwordRadioButton;
    private JRadioButton wordRadioButton;
    private JRadioButton bajtRadioButton;

    public JavaCalculator() {


       turnOffButtons();

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

         aButton.addActionListener(e -> appendNumber("A"));
         bButton.addActionListener(e -> appendNumber("B"));
         cButton.addActionListener(e -> appendNumber("C"));
         dButton.addActionListener(e -> appendNumber("D"));
         eButton.addActionListener(e -> appendNumber("E"));
         fButton.addActionListener(e -> appendNumber("F"));

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

                        case "rsh":
                            result = calculator.shiftRight(total1,currentValue);
                            break;

                        case "lsh":
                            result = calculator.shiftLeft(total1,currentValue);
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
        roRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentText = displayResult.getText();

                String displayValue = calculator.convertNumber(currentText, currentTypeNumber, Calculator.TypeNumber.Dec, currentTypeWord);

                BigInteger value = new BigInteger(displayValue);

                BigInteger rotatedValue = calculator.rotateRight(currentTypeWord, value);

                displayResult.setText(calculator.convertNumber(rotatedValue.toString(), Calculator.TypeNumber.Dec, currentTypeNumber, currentTypeWord));
                binaryFormatter.updateDisplay(rotatedValue, calculator.getBaseWord(currentTypeWord));
            }
        });
        roLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentText = displayResult.getText();

                String displayValue = calculator.convertNumber(currentText, currentTypeNumber, Calculator.TypeNumber.Dec, currentTypeWord);

                BigInteger value = new BigInteger(displayValue);

                BigInteger rotatedValue = calculator.rotateLeft(currentTypeWord, value);

                displayResult.setText(calculator.convertNumber(rotatedValue.toString(), Calculator.TypeNumber.Dec, currentTypeNumber, currentTypeWord));
                binaryFormatter.updateDisplay(rotatedValue, calculator.getBaseWord(currentTypeWord));
            }
        });


        rshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPlaceholderActive() && !displayResult.getText().isEmpty()) {
                    BigInteger currentValue = new BigInteger(displayResult.getText(), calculator.getBaseValue(currentTypeNumber));
                    if (!isOperatorPressed) {
                        if (operator.isEmpty()) {
                            total1 = currentValue;
                            operator = "rsh";
                        } else {

                            total1 =  calculator.shiftRight(total1,currentValue);
                            displayResult.setText(total1.toString());
                            binaryFormatter.updateDisplay(total1, calculator.getBaseWord(currentTypeWord));
                        }
                        lastNumber = currentValue;
                        isOperatorPressed = true;
                        lastOperator = "rsh";
                    }
                }
            }
        });

        lshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPlaceholderActive() && !displayResult.getText().isEmpty()) {
                    BigInteger currentValue = new BigInteger(displayResult.getText(), calculator.getBaseValue(currentTypeNumber));
                    if (!isOperatorPressed) {
                        if (operator.isEmpty()) {
                            total1 = currentValue;
                            operator = "lsh";
                        } else {

                            total1 =  calculator.shiftLeft(total1,currentValue);
                            displayResult.setText(total1.toString());
                            binaryFormatter.updateDisplay(total1, calculator.getBaseWord(currentTypeWord));
                        }
                        lastNumber = currentValue;
                        isOperatorPressed = true;
                        lastOperator = "lsh";
                    }
                }
            }
        });
    }
    private void updateWord(Calculator.TypeWord typeWord) {
        String currentText = displayResult.getText();


        if (!isPlaceholderActive() && !currentText.isEmpty()) {

            int baseWord = calculator.getBaseWord(typeWord);
            int currentBaseWord = calculator.getBaseWord(currentTypeWord);

            if(currentTypeNumber != Calculator.TypeNumber.Bin){

                String displayValue = calculator.convertNumber(currentText, currentTypeNumber, Calculator.TypeNumber.Dec, currentTypeWord);
                BigInteger value = new BigInteger(displayValue);
                binaryFormatter.updateDisplay(value, baseWord);

                String result = calculator.convertNumber(displayValue, Calculator.TypeNumber.Dec, currentTypeNumber, typeWord);
                displayResult.setText(result);

            }else{

                displayResult.setText(binaryFormatter.updateFromDisplay(currentText, baseWord,currentBaseWord));



            }

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
        switch (typeNumber) {
            case Bin:
                enableButtonsForBinary();
                break;
            case Oct:
                enableButtonsForOctal();
                break;
            case Dec:
                enableButtonsForDecimal();
                break;
            case Hex:
                enableButtonsForHexadecimal();
                break;
        }
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


    private void resetAllButtons() {
        // Wyłącz wszystkie przyciski i ustaw ich kolor na szary
        a0Button.setEnabled(false);
        a1Button.setEnabled(false);
        a2Button.setEnabled(false);
        a3Button.setEnabled(false);
        a4Button.setEnabled(false);
        a5Button.setEnabled(false);
        a6Button.setEnabled(false);
        a7Button.setEnabled(false);
        a8Button.setEnabled(false);
        a9Button.setEnabled(false);
        aButton.setEnabled(false);
        bButton.setEnabled(false);
        cButton.setEnabled(false);
        dButton.setEnabled(false);
        eButton.setEnabled(false);
        fButton.setEnabled(false);



        Color disabledColor = Color.GRAY;
        a0Button.setForeground(disabledColor);
        a1Button.setForeground(disabledColor);
        a2Button.setForeground(disabledColor);
        a3Button.setForeground(disabledColor);
        a4Button.setForeground(disabledColor);
        a5Button.setForeground(disabledColor);
        a6Button.setForeground(disabledColor);
        a7Button.setForeground(disabledColor);
        a8Button.setForeground(disabledColor);
        a9Button.setForeground(disabledColor);
        aButton.setForeground(disabledColor);
        bButton.setForeground(disabledColor);
        cButton.setForeground(disabledColor);
        dButton.setForeground(disabledColor);
        eButton.setForeground(disabledColor);
        fButton.setForeground(disabledColor);


    }

    private void enableButtonsForBinary() {
        resetAllButtons();
        // Włącz tylko 0 i 1
        a0Button.setEnabled(true);
        a1Button.setEnabled(true);

        Color enabledColor = Color.BLACK;
        a0Button.setForeground(enabledColor);
        a1Button.setForeground(enabledColor);
    }

    private void enableButtonsForOctal() {
        resetAllButtons();
        // Włącz przyciski od 0 do 7
        a0Button.setEnabled(true);
        a1Button.setEnabled(true);
        a2Button.setEnabled(true);
        a3Button.setEnabled(true);
        a4Button.setEnabled(true);
        a5Button.setEnabled(true);
        a6Button.setEnabled(true);
        a7Button.setEnabled(true);

        Color enabledColor = Color.BLACK;
        a0Button.setForeground(enabledColor);
        a1Button.setForeground(enabledColor);
        a2Button.setForeground(enabledColor);
        a3Button.setForeground(enabledColor);
        a4Button.setForeground(enabledColor);
        a5Button.setForeground(enabledColor);
        a6Button.setForeground(enabledColor);
        a7Button.setForeground(enabledColor);
    }

    private void enableButtonsForDecimal() {
        resetAllButtons();
        // Włącz przyciski od 0 do 9
        a0Button.setEnabled(true);
        a1Button.setEnabled(true);
        a2Button.setEnabled(true);
        a3Button.setEnabled(true);
        a4Button.setEnabled(true);
        a5Button.setEnabled(true);
        a6Button.setEnabled(true);
        a7Button.setEnabled(true);
        a8Button.setEnabled(true);
        a9Button.setEnabled(true);

        Color enabledColor = Color.BLACK;
        a0Button.setForeground(enabledColor);
        a1Button.setForeground(enabledColor);
        a2Button.setForeground(enabledColor);
        a3Button.setForeground(enabledColor);
        a4Button.setForeground(enabledColor);
        a5Button.setForeground(enabledColor);
        a6Button.setForeground(enabledColor);
        a7Button.setForeground(enabledColor);
        a8Button.setForeground(enabledColor);
        a9Button.setForeground(enabledColor);
    }

    private void enableButtonsForHexadecimal() {
        resetAllButtons();
        // Włącz wszystkie przyciski od 0 do 9 i A-F
        enableButtonsForDecimal();

        aButton.setEnabled(true);
        bButton.setEnabled(true);
        cButton.setEnabled(true);
        dButton.setEnabled(true);
        eButton.setEnabled(true);
        fButton.setEnabled(true);

        Color enabledColor = Color.BLACK;
        aButton.setForeground(enabledColor);
        bButton.setForeground(enabledColor);
        cButton.setForeground(enabledColor);
        dButton.setForeground(enabledColor);
        eButton.setForeground(enabledColor);
        fButton.setForeground(enabledColor);
    }

    private void turnOffButtons(){
        sqrtButton.setEnabled(false);
        procentButton.setEnabled(false);
        a1XButton.setEnabled(false);
        blankButton.setEnabled(false);

        sqrtButton.setForeground(Color.GRAY);
        procentButton.setForeground(Color.GRAY);
        a1XButton.setForeground(Color.GRAY);
        blankButton.setForeground(Color.GRAY);
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
