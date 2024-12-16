import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JavaCalculator {

    private double total1 = 0.0;
    private double total2 = 0.0;
    private String operator = "";
    private boolean isOperatorPressed = false;
    private final String PLACEHOLDER = "0";

    private JPanel JavaCalculator;
    private JTextField binaryResult;
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
    private JList list1;
    private JList list2;
    private JButton fieldPlus;
    private JButton fieldMinus;
    private JButton fieldMultiplication;
    private JButton fieldDivide;
    private JButton fieldPlusOrMinus;
    private JButton fieldMPlus;

    public JavaCalculator() {

        displayResult.setText(PLACEHOLDER);
        displayResult.setForeground(java.awt.Color.GRAY);

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


        rówwnaSieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!operator.isEmpty() && !isPlaceholderActive() && !displayResult.getText().isEmpty()) {
                    double currentValue = Double.parseDouble(displayResult.getText());
                    switch (operator) {
                        case "+":
                            total2 = total1 + currentValue;
                            break;
                        case "-":
                            total2 = total1 - currentValue;
                            break;
                        case "*":
                            total2 = total1 * currentValue;
                            break;
                        case "/":
                            if (currentValue != 0) {
                                total2 = total1 / currentValue;
                            } else {
                                JOptionPane.showMessageDialog(null, "Nie można dzielić przez zero!");
                                return;
                            }
                            break;
                    }
                    displayResult.setText(Double.toString(total2));
                    displayResult.setForeground(java.awt.Color.BLACK);
                    operator = "";
                    isOperatorPressed = false;
                }
            }
        });

        fieldPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPlaceholderActive() && !displayResult.getText().isEmpty()) {
                    total1 = Double.parseDouble(displayResult.getText());
                    operator = "+";

                    isOperatorPressed = true;
                    displayResult.setText(displayResult.getText());
                }
            }
        });

    }

    private void appendNumber(String number) {
        if (isPlaceholderActive() || isOperatorPressed) {
            displayResult.setText(number);
            displayResult.setForeground(java.awt.Color.BLACK);
            isOperatorPressed = false;
        } else {
            displayResult.setText(displayResult.getText() + number);
        }
    }

    private void displayPlaceholder() {
        displayResult.setText(PLACEHOLDER);
        displayResult.setForeground(java.awt.Color.GRAY);
    }

    private boolean isPlaceholderActive() {
        return displayResult.getText().equals(PLACEHOLDER);
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("JavaCalculator");
        frame.setSize(400,600);
        frame.setContentPane(new JavaCalculator().JavaCalculator);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }




}
