package test;

import gui.BinaryResultFormatter;
import gui.JavaCalculator;
import main.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private Calculator calc;
    private JavaCalculator calculator;
    private BinaryResultFormatter binaryFormatter;
    private JTextField binaryResult;

    @BeforeEach
    void setUp() {
        calc = new Calculator(0);



        binaryResult = new JTextField();
        binaryFormatter = new BinaryResultFormatter(binaryResult);
        calculator = new JavaCalculator();




    }

    @Test
    void testBasicParentheses() {
        // Test opening parenthesis
        calculator.handleLeftParenthesis();
        assertEquals("( 1",calculator.toStringBlankButton());
        assertTrue(calculator.isInParentheses());
        assertEquals("0", calculator.toStringDisplay());
        assertEquals(Color.GRAY, calculator.getDisplayResult().getForeground());

        // Test closing parenthesis
        calculator.handleRightParenthesis();
        assertEquals("", calculator.toStringBlankButton());
        assertFalse(calculator.isInParentheses());
    }

    @Test
    void testNestedParentheses() {
        // First level
        calculator.handleLeftParenthesis();
        assertEquals("( 1", calculator.toStringBlankButton());

        // Second level
        calculator.handleLeftParenthesis();
        assertEquals("( 2", calculator.toStringBlankButton()
        );

        // Close inner parenthesis
        calculator.handleRightParenthesis();
        assertEquals("( 1", calculator.toStringBlankButton());

        // Close outer parenthesis
        calculator.handleRightParenthesis();
        assertEquals("", calculator.toStringBlankButton());
    }

    @Test
    void testParenthesesWithAddition() {
        calculator.handleLeftParenthesis();
        calculator.total1 = BigInteger.valueOf(5);
        calculator.operator = "+";
        calculator.setDisplayResult("3");
        calculator.handleRightParenthesis();

        assertEquals("8", calculator.getDisplayResult().getText());
        assertEquals(Color.BLACK, calculator.getDisplayResult().getForeground());
    }

    @Test
    void testParenthesesWithMultiplication() {
        calculator.handleLeftParenthesis();
        calculator.total1 = BigInteger.valueOf(5);
        calculator.operator = "*";
        calculator.setDisplayResult("3");
        calculator.handleRightParenthesis();

        assertEquals("15", calculator.getDisplayResult().getText());
    }

    @Test
    void testParenthesesWithBitwiseOperations() {
        // Test AND operation
        calculator.handleLeftParenthesis();
        calculator.total1 = BigInteger.valueOf(12); // 1100 in binary
        calculator.operator = "and";
        calculator.setDisplayResult("10"); // 1010 in binary
        calculator.handleRightParenthesis();
        assertEquals("8", calculator.getDisplayResult().getText()); // 1000 in binary

        // Test OR operation
        calculator.handleLeftParenthesis();
        calculator.total1 = BigInteger.valueOf(12); // 1100 in binary
        calculator.operator = "or";
        calculator.setDisplayResult("10"); // 1010 in binary
        calculator.handleRightParenthesis();
        assertEquals("14", calculator.getDisplayResult().getText()); // 1110 in binary
    }


    @Test
    void testComplexNestedExpression() {
        // Testing (5 + (3 * 2))
        calculator.handleLeftParenthesis(); // Outer
        calculator.total1 = BigInteger.valueOf(5);
        calculator.operator = "+";

        calculator.handleLeftParenthesis(); // Inner
        calculator.total1 = BigInteger.valueOf(3);
        calculator.operator = "*";
        calculator.setDisplayResult("2");
        calculator.handleRightParenthesis(); // Close inner

        calculator.handleRightParenthesis(); // Close outer
        assertEquals("11", calculator.getDisplayResult().getText());
    }

    @Test
    void testParenthesesWithShiftOperations() {
        // Test left shift
        calculator.handleLeftParenthesis();
        calculator.total1 = BigInteger.valueOf(2);
        calculator.operator = "lsh";
        calculator.setDisplayResult("1");
        calculator.handleRightParenthesis();
        assertEquals("4", calculator.getDisplayResult().getText());

        // Test right shift
        calculator.handleLeftParenthesis();
        calculator.total1 = BigInteger.valueOf(8);
        calculator.operator = "rsh";
        calculator.setDisplayResult("2");
        calculator.handleRightParenthesis();
        assertEquals("2", calculator.getDisplayResult().getText());
    }

    @Test
    void testEmptyParentheses() {
        calculator.handleLeftParenthesis();
        calculator.handleRightParenthesis();
        assertEquals("0", calculator.getDisplayResult().getText());
        assertEquals("", calculator.toStringBlankButton());
    }

    // Basic Arithmetic Tests
    @Test
    void testArithmeticOperations() {
        BigInteger a = BigInteger.valueOf(10);
        BigInteger b = BigInteger.valueOf(5);

        assertEquals(BigInteger.valueOf(15), calc.add(a, b));
        assertEquals(BigInteger.valueOf(5), calc.subtract(a, b));
        assertEquals(BigInteger.valueOf(50), calc.multiply(a, b));
        assertEquals(BigInteger.valueOf(2), calc.divide(a, b));

        // Test with negative numbers
        BigInteger c = BigInteger.valueOf(-10);
        assertEquals(BigInteger.valueOf(-5), calc.add(c, b));
        assertEquals(BigInteger.valueOf(-15), calc.subtract(c, b));
        assertEquals(BigInteger.valueOf(-50), calc.multiply(c, b));
        assertEquals(BigInteger.valueOf(-2), calc.divide(c, b));
    }

    @Test
    void testDivisionByZero() {
        BigInteger a = BigInteger.valueOf(10);
        BigInteger zero = BigInteger.ZERO;

        assertThrows(IllegalArgumentException.class, () -> calc.divide(a, zero));
    }

    // Bitwise Operation Tests
    @Test
    void testBitwiseOperations() {
        BigInteger a = BigInteger.valueOf(12); // 1100 in binary
        BigInteger b = BigInteger.valueOf(10); // 1010 in binary

        assertEquals(BigInteger.valueOf(8), calc.bitAnd(a, b));  // 1100 & 1010 = 1000 (8)
        assertEquals(BigInteger.valueOf(14), calc.bitOr(a, b));  // 1100 | 1010 = 1110 (14)
        assertEquals(BigInteger.valueOf(6), calc.bitXor(a, b));  // 1100 ^ 1010 = 0110 (6)

        // Test NOT operation (depends on word size)
        BigInteger notResult = calc.bitNot(a);
        assertTrue(notResult.compareTo(BigInteger.ZERO) != 0); // Basic check that NOT does something
    }

    // Shift Operation Tests
    @Test
    void testShiftOperations() {
        BigInteger value = BigInteger.valueOf(8);
        BigInteger shift = BigInteger.valueOf(2);

        assertEquals(BigInteger.valueOf(32), calc.shiftLeft(value, shift));  // 8 << 2 = 32
        assertEquals(BigInteger.valueOf(2), calc.shiftRight(value, shift));  // 8 >> 2 = 2
    }

    // Rotation Tests
    @Test
    void testRotateOperations() {
        BigInteger value = BigInteger.valueOf(6); // 0110 in binary

        // Test rotate left
        BigInteger rotatedLeft = calc.rotateLeft(Calculator.TypeWord.Bajt, value);
        // For 8-bit rotation of 0000 0110, expect 0000 1100 (12)
        assertEquals(BigInteger.valueOf(12), rotatedLeft);

        // Test rotate right
        BigInteger rotatedRight = calc.rotateRight(Calculator.TypeWord.Bajt, value);
        // For 8-bit rotation of 0000 0110, expect 0000 0011 (3)
        assertEquals(BigInteger.valueOf(3), rotatedRight);
    }

    // Number Type Conversion Tests
    @Test
    void testNumberConversion() {
        // Test decimal to other bases
        assertEquals("64", calc.convertNumber("100", Calculator.TypeNumber.Dec, Calculator.TypeNumber.Hex, Calculator.TypeWord.Bajt));
        assertEquals("144", calc.convertNumber("100", Calculator.TypeNumber.Dec, Calculator.TypeNumber.Oct, Calculator.TypeWord.Bajt));
        assertEquals("1100100", calc.convertNumber("100", Calculator.TypeNumber.Dec, Calculator.TypeNumber.Bin, Calculator.TypeWord.Bajt));

        // Test hexadecimal to other bases
        assertEquals("-1", calc.convertNumber("FF", Calculator.TypeNumber.Hex, Calculator.TypeNumber.Dec, Calculator.TypeWord.Bajt));
        assertEquals("377", calc.convertNumber("FF", Calculator.TypeNumber.Hex, Calculator.TypeNumber.Oct, Calculator.TypeWord.Bajt));
        assertEquals("11111111", calc.convertNumber("FF", Calculator.TypeNumber.Hex, Calculator.TypeNumber.Bin, Calculator.TypeWord.Bajt));

        // Test binary to other bases
        assertEquals("15", calc.convertNumber("1111", Calculator.TypeNumber.Bin, Calculator.TypeNumber.Dec, Calculator.TypeWord.Bajt));
        assertEquals("F", calc.convertNumber("1111", Calculator.TypeNumber.Bin, Calculator.TypeNumber.Hex, Calculator.TypeWord.Bajt));
        assertEquals("17", calc.convertNumber("1111", Calculator.TypeNumber.Bin, Calculator.TypeNumber.Oct, Calculator.TypeWord.Bajt));
    }

    // Word Type Tests
    @Test
    void testWordTypes() {
        // Test bit sizes for different word types
        assertEquals(8, calc.getBaseWord(Calculator.TypeWord.Bajt));
        assertEquals(16, calc.getBaseWord(Calculator.TypeWord.Word));
        assertEquals(32, calc.getBaseWord(Calculator.TypeWord.Dword));
        assertEquals(64, calc.getBaseWord(Calculator.TypeWord.Qword));
    }

    // Base Value Tests
    @Test
    void testBaseValues() {
        assertEquals(16, calc.getBaseValue(Calculator.TypeNumber.Hex));
        assertEquals(10, calc.getBaseValue(Calculator.TypeNumber.Dec));
        assertEquals(8, calc.getBaseValue(Calculator.TypeNumber.Oct));
        assertEquals(2, calc.getBaseValue(Calculator.TypeNumber.Bin));
    }

    // Modulo Operation Tests
    @Test
    void testModulo() {
        BigInteger a = BigInteger.valueOf(17);
        BigInteger b = BigInteger.valueOf(5);

        assertEquals(BigInteger.valueOf(2), calc.mod(a, b));  // 17 % 5 = 2

        // Test with negative numbers
        BigInteger c = BigInteger.valueOf(-17);
        assertEquals(BigInteger.valueOf(3), calc.mod(c, b));  // -17 % 5 = 3
    }

    // Test number conversion with different word sizes
    @Test
    void testNumberConversionWithDifferentWordSizes() {
        // Test with Byte (8-bit)
        assertEquals("7F", calc.convertNumber("127", Calculator.TypeNumber.Dec, Calculator.TypeNumber.Hex, Calculator.TypeWord.Bajt));
        assertEquals("-128", calc.convertNumber("80", Calculator.TypeNumber.Hex, Calculator.TypeNumber.Dec, Calculator.TypeWord.Bajt));

        // Test with Word (16-bit)
        assertEquals("7FFF", calc.convertNumber("32767", Calculator.TypeNumber.Dec, Calculator.TypeNumber.Hex, Calculator.TypeWord.Word));
        assertEquals("-32768", calc.convertNumber("8000", Calculator.TypeNumber.Hex, Calculator.TypeNumber.Dec, Calculator.TypeWord.Word));

        // Test with DWord (32-bit)
        assertEquals("7FFFFFFF", calc.convertNumber("2147483647", Calculator.TypeNumber.Dec, Calculator.TypeNumber.Hex, Calculator.TypeWord.Dword));
        assertEquals("-2147483648", calc.convertNumber("80000000", Calculator.TypeNumber.Hex, Calculator.TypeNumber.Dec, Calculator.TypeWord.Dword));
    }
}