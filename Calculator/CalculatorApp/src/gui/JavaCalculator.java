package gui;

import main.Calculator;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.*;
import java.math.BigInteger;
import java.util.Stack;

public class JavaCalculator {

    Calculator calculator = new Calculator(0);

    private Stack<BigInteger> numbers = new Stack<>();
    private Stack<String> operators = new Stack<>();
    private int parenthesesCount = 0;
    private boolean isInParentheses = false;

    public boolean isInParentheses() {
        return isInParentheses;
    }

    private BigInteger memoryValue = BigInteger.ZERO;

    public BigInteger total1 = BigInteger.ZERO;
    public String operator = "";
    private boolean isNewLine = false;
    private boolean isOperatorPressed = false;
    private String placeHolder = "0";
    private String lastOperator = "";
    private BigInteger lastNumber = BigInteger.ZERO;
    private boolean flagToSign = false;


    private Calculator.TypeNumber currentTypeNumber = Calculator.TypeNumber.Dec;
    private Calculator.TypeWord currentTypeWord = Calculator.TypeWord.Qword;

    private JPanel JavaCalculator;
    private JTextPane binaryResult;
    private BinaryResultFormatter binaryFormatter;
    private JButton andButton;
    private JButton rshButton;
    private JButton xorButton;
    private JButton roRButton;
    private JButton rightBrackets;
    private JButton modButton;


    public String toStringBlankButton() {
        return blankButton.getText();

    }

    public void setDisplayResult(String dis) {
        displayResult.setText(dis);
    }

    public JTextField getDisplayResult() {
        return displayResult;
    }

    public String toStringDisplay() {
        return displayResult.getText();

    }



    private JButton blankButton;
    private JButton leftBrackets;
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
    private JButton cofniecie;
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
    private JLabel ifTrueMS;

    public JavaCalculator() {

        binaryResult.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int pos = binaryResult.viewToModel2D(e.getPoint());
                StyledDocument doc = binaryResult.getStyledDocument();

                try {

                    String bit = doc.getText(pos, 1);

                    if (bit.equals("0")) {
                        doc.remove(pos, 1);
                        doc.insertString(pos, "1", null);
                    } else if (bit.equals("1")) {
                        doc.remove(pos, 1);
                        doc.insertString(pos, "0", null);
                    }
                    displayResult.requestFocusInWindow();


                    String s = calculator.convertNumber(binaryResult.getText(), Calculator.TypeNumber.Bin, currentTypeNumber, currentTypeWord);
                    displayResult.setText(s);


                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        });


        displayResult.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(c == KeyEvent.VK_BACK_SPACE){
            cofniecie.doClick();
                }
                if(c == KeyEvent.VK_ENTER) {
                    rówwnaSieButton.doClick();
                }else {
                    if (isAllowedCharacter(c)) {
                        if(!flagToSign){
                            appendNumber(Character.toString(c));
                        }else{
                            switch (Character.toString(c)) {
                                case "+":
                                   fieldPlus.doClick();
                                    break;
                                case "-":
                                    fieldMinus.doClick();
                                    break;
                                case "/":
                                    fieldDivide.doClick();
                                    break;
                                case "*":
                                    fieldMultiplication.doClick();
                                    break;

                                default:
                            }

                        }

                        flagToSign = false;

                    }
                }

                e.consume();
            }
        });



        ifTrueMS.setEnabled(false);
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
        StyledDocument doc = binaryResult.getStyledDocument();
        SimpleAttributeSet alignment = new SimpleAttributeSet();
        StyleConstants.setAlignment(alignment, StyleConstants.ALIGN_RIGHT);
        doc.setParagraphAttributes(0, doc.getLength(), alignment, false);

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
                if ((!operator.isEmpty() && !isPlaceholderActive() && !displayResult.getText().isEmpty()) || !lastOperator.isEmpty() ) {
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
                        case "xor":
                            result = calculator.bitXor(total1,currentValue);
                            break;

                        case "or":
                            result = calculator.bitOr(total1,currentValue);
                            break;
                        case "and":
                            result = calculator.bitAnd(total1,currentValue);
                            break;
                        case "mod":
                            result = calculator.mod(total1,currentValue);
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
                if (!isPlaceholderActive() && !displayResult.getText().isEmpty() ) {
                    BigInteger currentValue = new BigInteger(displayResult.getText(), calculator.getBaseValue(currentTypeNumber));
                    if (!isOperatorPressed ) {
                        if (operator.isEmpty()  ) {
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
                operator ="";
                binaryFormatter.updateDisplay(BigInteger.ZERO, calculator.getBaseWord(currentTypeWord));
                isInParentheses = false;
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
        notButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPlaceholderActive() && !displayResult.getText().isEmpty()) {
                    BigInteger currentValue = new BigInteger(displayResult.getText(), calculator.getBaseValue(currentTypeNumber));

                            total1 =  calculator.bitNot(currentValue);

                            displayResult.setText( calculator.convertNumber(total1.toString(), Calculator.TypeNumber.Dec,currentTypeNumber,currentTypeWord));
                            binaryFormatter.updateDisplay(total1, calculator.getBaseWord(currentTypeWord));
                    lastOperator = "";
                    operator = "";
                    }

            }
        });
        andButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPlaceholderActive() && !displayResult.getText().isEmpty()) {
                    BigInteger currentValue = new BigInteger(displayResult.getText(), calculator.getBaseValue(currentTypeNumber));
                    if (!isOperatorPressed) {
                        if (operator.isEmpty()) {
                            total1 = currentValue;
                            operator = "and";
                        } else {

                            total1 =  calculator.bitAnd(total1,currentValue);
                            displayResult.setText(total1.toString());
                            binaryFormatter.updateDisplay(total1, calculator.getBaseWord(currentTypeWord));
                        }
                        lastNumber = currentValue;
                        isOperatorPressed = true;
                        lastOperator = "and";
                    }
                }
            }
        });
        orButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPlaceholderActive() && !displayResult.getText().isEmpty()) {
                    BigInteger currentValue = new BigInteger(displayResult.getText(), calculator.getBaseValue(currentTypeNumber));
                    if (!isOperatorPressed) {
                        if (operator.isEmpty()) {
                            total1 = currentValue;
                            operator = "or";
                        } else {

                            total1 =  calculator.bitOr(total1,currentValue);
                            displayResult.setText(total1.toString());
                            binaryFormatter.updateDisplay(total1, calculator.getBaseWord(currentTypeWord));
                        }
                        lastNumber = currentValue;
                        isOperatorPressed = true;
                        lastOperator = "or";
                    }
                }
            }
        });
        xorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPlaceholderActive() && !displayResult.getText().isEmpty()) {
                    BigInteger currentValue = new BigInteger(displayResult.getText(), calculator.getBaseValue(currentTypeNumber));
                    if (!isOperatorPressed) {
                        if (operator.isEmpty()) {
                            total1 = currentValue;
                            operator = "xor";
                        } else {

                            total1 =  calculator.bitXor(total1,currentValue);
                            displayResult.setText(total1.toString());
                            binaryFormatter.updateDisplay(total1, calculator.getBaseWord(currentTypeWord));
                        }
                        lastNumber = currentValue;
                        isOperatorPressed = true;
                        lastOperator = "xor";
                    }
                }
            }
        });
        fieldPlusOrMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPlaceholderActive() && !displayResult.getText().isEmpty()) {
                    BigInteger currentValue = new BigInteger(displayResult.getText(), calculator.getBaseValue(currentTypeNumber));

                    total1 =  currentValue.multiply(new BigInteger("-1"));

                    displayResult.setText( calculator.convertNumber(total1.toString(), Calculator.TypeNumber.Dec,currentTypeNumber,currentTypeWord));
                    binaryFormatter.updateDisplay(total1, calculator.getBaseWord(currentTypeWord));
                    lastOperator = "";
                    operator = "";
                }
            }
        });
        modButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPlaceholderActive() && !displayResult.getText().isEmpty()) {
                    BigInteger currentValue = new BigInteger(displayResult.getText(), calculator.getBaseValue(currentTypeNumber));
                    if (!isOperatorPressed) {
                        if (operator.isEmpty()) {
                            total1 = currentValue;
                            operator = "mod";
                        } else {

                            total1 =  calculator.mod(total1,currentValue);
                            displayResult.setText(total1.toString());
                            binaryFormatter.updateDisplay(total1, calculator.getBaseWord(currentTypeWord));
                        }
                        lastNumber = currentValue;
                        isOperatorPressed = true;
                        lastOperator = "mod";
                    }
                }
            }
        });

        //Czysci wartosc w pamieci
        MCButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                memoryValue = BigInteger.ZERO;
                ifTrueMS.setEnabled(false);
            }
        });

        // Wyświetla wartość z pamięci
        MRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayResult.setText(memoryValue.toString());
                binaryFormatter.updateDisplay(memoryValue, calculator.getBaseWord(currentTypeWord));
                placeHolder = String.valueOf(memoryValue);
            }
        });

        MSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    memoryValue = new BigInteger(displayResult.getText(), calculator.getBaseValue(currentTypeNumber));
                    placeHolder = String.valueOf(memoryValue);
                    ifTrueMS.setEnabled(true);


            }
        });
        // Dodaje bieżącą wartość do pamięci
        MPField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if (!isPlaceholderActive() && !displayResult.getText().isEmpty()) {
                if(!displayResult.getText().isEmpty()){
                    BigInteger currentValue = new BigInteger(displayResult.getText(), calculator.getBaseValue(currentTypeNumber));
                    memoryValue = memoryValue.add(currentValue);
                    placeHolder = String.valueOf(memoryValue);
                }
            }
        });
        // Odejmuje bieżącą wartość od pamięci
        MMButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if (!isPlaceholderActive() && !displayResult.getText().isEmpty()) {
                if(!displayResult.getText().isEmpty()){
                    BigInteger currentValue = new BigInteger(displayResult.getText(), calculator.getBaseValue(currentTypeNumber));
                    memoryValue = memoryValue.subtract(currentValue);
                    placeHolder = String.valueOf(memoryValue);
                }
            }
        });
        cofniecie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String displayValue = displayResult.getText();

                if (!displayValue.isEmpty()) {
                    String newDisplayValue = displayValue.substring(0, displayValue.length() - 1);
                    displayResult.setText(newDisplayValue);


                   if(!newDisplayValue.isEmpty()) {
                       String convertedValue = calculator.convertNumber(newDisplayValue, currentTypeNumber, Calculator.TypeNumber.Dec, currentTypeWord);


                       binaryFormatter.updateDisplay(new BigInteger(convertedValue), calculator.getBaseWord(currentTypeWord));
                   }else{
                       binaryFormatter.updateDisplay(new BigInteger("0"),calculator.getBaseWord(currentTypeWord));
                       displayResult.setText("0");

                   }
                }



            }
        });
        leftBrackets.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                handleLeftParenthesis();
            }
        });
        rightBrackets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRightParenthesis();
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
        isNewLine = true;
        lastNumber = null;
        displayResult.requestFocusInWindow();
    }

    private boolean isAllowedCharacter(char c) {
        // Sprawdź typ systemu liczbowego (binarny, szesnastkowy itp.)
        switch (currentTypeNumber) {
            case Bin:
                flagToSign = isOperator(c);
                return c == '0' || c == '1'|| isOperator(c);

            case Dec: // Dozwolone cyfry 0-9 i operatory
                flagToSign = isOperator(c);
                return Character.isDigit(c) || isOperator(c);

            case Oct: // Dozwolone cyfry 0-7 i operatory
                flagToSign = isOperator(c);
                return (c >= '0' && c <= '7') || isOperator(c);

            case Hex: // Dozwolone cyfry 0-9, litery A-F i operatory
                flagToSign = isOperator(c);
                return Character.isDigit(c) || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f') || isOperator(c);

            default:
                return false; // Jeśli typ jest nieznany, nie akceptuj znaków
        }
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')' || c == '=';
    }

    public void handleLeftParenthesis() {
        if (!operator.isEmpty()) {
            operators.push(operator);
            numbers.push(total1);
            operator = "";
        }
        parenthesesCount++;

        blankButton.setText("( "+parenthesesCount);

        isInParentheses = true;
        isOperatorPressed = true;
        total1 = BigInteger.ZERO; // Reset total1 for calculations inside parentheses

        // Reset display for new input inside parentheses
        displayResult.setText("0");
        displayResult.setForeground(java.awt.Color.GRAY);
    }

    public void handleRightParenthesis() {
        if (parenthesesCount > 0) {
            if (!isPlaceholderActive() && !displayResult.getText().isEmpty()) {
                // Jeśli jest aktywny operator wewnątrz nawiasu, wykonaj ostatnią operację
                if (!operator.isEmpty()) {
                    BigInteger currentValue = new BigInteger(
                            calculator.convertNumber(
                                    displayResult.getText(),
                                    currentTypeNumber,
                                    Calculator.TypeNumber.Dec,
                                    currentTypeWord
                            )
                    );

                    switch (operator) {
                        case "+":
                            total1 = calculator.add(total1, currentValue);
                            break;
                        case "-":
                            total1 = calculator.subtract(total1, currentValue);
                            break;
                        case "*":
                            total1 = calculator.multiply(total1, currentValue);
                            break;
                        case "/":
                            if (!currentValue.equals(BigInteger.ZERO)) {
                                total1 = calculator.divide(total1, currentValue);
                            } else {
                                JOptionPane.showMessageDialog(null, "Nie można dzielić przez zero!");
                                return;
                            }
                            break;
                        case "mod":
                            total1 = calculator.mod(total1, currentValue);
                            break;
                        case "and":
                            total1 = calculator.bitAnd(total1, currentValue);
                            break;
                        case "or":
                            total1 = calculator.bitOr(total1, currentValue);
                            break;
                        case "xor":
                            total1 = calculator.bitXor(total1, currentValue);
                            break;
                        case "lsh":
                            total1 = calculator.shiftLeft(total1, currentValue);
                            break;
                        case "rsh":
                            total1 = calculator.shiftRight(total1, currentValue);
                            break;
                    }
                    operator = "";
                } else {
                    // Jeśli nie ma operatora, weź aktualną wartość
                    total1 = new BigInteger(
                            calculator.convertNumber(
                                    displayResult.getText(),
                                    currentTypeNumber,
                                    Calculator.TypeNumber.Dec,
                                    currentTypeWord
                            )
                    );
                }

                // Teraz wykonaj operację ze stosu (jeśli istnieje)
                if (!operators.isEmpty()) {
                    String stackOperator = operators.pop();
                    BigInteger stackNumber = numbers.pop();
                    BigInteger result;

                    switch (stackOperator) {
                        case "+":
                            result = calculator.add(stackNumber, total1);
                            break;
                        case "-":
                            result = calculator.subtract(stackNumber, total1);
                            break;
                        case "*":
                            result = calculator.multiply(stackNumber, total1);
                            break;
                        case "/":
                            if (!total1.equals(BigInteger.ZERO)) {
                                result = calculator.divide(stackNumber, total1);
                            } else {
                                JOptionPane.showMessageDialog(null, "Nie można dzielić przez zero!");
                                return;
                            }
                            break;
                        case "mod":
                            result = calculator.mod(stackNumber, total1);
                            break;
                        case "and":
                            result = calculator.bitAnd(stackNumber, total1);
                            break;
                        case "or":
                            result = calculator.bitOr(stackNumber, total1);
                            break;
                        case "xor":
                            result = calculator.bitXor(stackNumber, total1);
                            break;
                        case "lsh":
                            result = calculator.shiftLeft(stackNumber, total1);
                            break;
                        case "rsh":
                            result = calculator.shiftRight(stackNumber, total1);
                            break;
                        default:
                            result = total1;

                    }
                    lastOperator = stackOperator;
                    total1 = result;
                    lastNumber = stackNumber;
                }

                // Wyświetl wynik
                displayResult.setText(calculator.convertNumber(
                        total1.toString(),
                        Calculator.TypeNumber.Dec,
                        currentTypeNumber,
                        currentTypeWord
                ));
                displayResult.setForeground(java.awt.Color.BLACK);

                // Aktualizuj wyświetlacz binarny
                binaryFormatter.updateDisplay(total1, calculator.getBaseWord(currentTypeWord));

            }

            parenthesesCount--;
            blankButton.setText("( "+ parenthesesCount);
            if (parenthesesCount == 0) {
                isInParentheses = false;
                blankButton.setText("");
            }
            isOperatorPressed = false;
        }
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
        displayResult.requestFocusInWindow();
    }

    private void appendNumber(String number) {

        BigInteger maxValue = BigInteger.ONE.shiftLeft(calculator.getBaseWord(currentTypeWord) - 1).subtract(BigInteger.ONE);
        BigInteger minValue = BigInteger.ONE.shiftLeft(calculator.getBaseWord(currentTypeWord) - 1).negate();


        if (isPlaceholderActive() || isOperatorPressed || isNewLine) {

            BigInteger currentValue = new BigInteger(number, calculator.getBaseValue(currentTypeNumber));
            if(currentValue.compareTo(maxValue) > 0 || currentValue.compareTo(minValue) < 0) {
            return;
            }

            displayResult.setText(number);
            displayResult.setForeground(java.awt.Color.BLACK);
            isOperatorPressed = false;
            isNewLine = false;

            binaryFormatter.updateDisplay(currentValue, calculator.getBaseWord(currentTypeWord));
        } else {


            String s1 = calculator.convertNumber(displayResult.getText(), currentTypeNumber, Calculator.TypeNumber.Dec, currentTypeWord);
            String s2 = calculator.convertNumber(number, currentTypeNumber, Calculator.TypeNumber.Dec, currentTypeWord);

            BigInteger combinedValue = new BigInteger(s1 + s2, calculator.getBaseValue(currentTypeNumber));

            if (combinedValue.compareTo(maxValue) > 0 || combinedValue.compareTo(minValue) < 0) {
                return;
            }
            displayResult.setText(displayResult.getText() + number);
            BigInteger cV = new BigInteger(displayResult.getText(), calculator.getBaseValue(currentTypeNumber));
            binaryFormatter.updateDisplay(cV, calculator.getBaseWord(currentTypeWord));


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

        aButton.setEnabled(false);
        bButton.setEnabled(false);
        cButton.setEnabled(false);
        dButton.setEnabled(false);
        eButton.setEnabled(false);
        fButton.setEnabled(false);

        sqrtButton.setForeground(Color.GRAY);
        procentButton.setForeground(Color.GRAY);
        a1XButton.setForeground(Color.GRAY);
        blankButton.setForeground(Color.GRAY);

        aButton.setForeground(Color.GRAY);
        bButton.setForeground(Color.GRAY);
        cButton.setForeground(Color.GRAY);
        dButton.setForeground(Color.GRAY);
        eButton.setForeground(Color.GRAY);
        fButton.setForeground(Color.GRAY);
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
