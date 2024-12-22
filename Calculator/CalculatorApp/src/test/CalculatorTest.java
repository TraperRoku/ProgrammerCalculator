package test;

import main.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private final Calculator calculator = new Calculator(0);


    // Dodawanie Odejmowanie Mnozenie Dzielenie
    @org.junit.jupiter.api.Test
    void add() {
        assertEquals(5, calculator.add(2, 3));
        assertEquals(-1, calculator.add(2, -3));
    }

    @org.junit.jupiter.api.Test
    void subtract() {
        assertEquals(-1, calculator.subtract(2, 3));
        assertEquals(5, calculator.subtract(2, -3));
    }

    @org.junit.jupiter.api.Test
    void multiply() {
        assertEquals(6, calculator.multiply(2, 3));
        assertEquals(-6, calculator.multiply(2, -3));
    }

    @org.junit.jupiter.api.Test
    void divide() {
        assertEquals( 2, calculator.divide(6, 3));
        assertEquals(2, calculator.divide(4, 2));
    }



    // przesuniecia bitowe

    @org.junit.jupiter.api.Test
    void bitAnd() {
    }

    @org.junit.jupiter.api.Test
    void bitOr() {
    }

    @org.junit.jupiter.api.Test
    void bitXor() {
    }

    @org.junit.jupiter.api.Test
    void bitNot() {
    }

    @org.junit.jupiter.api.Test
    void shiftLeft() {
    }

    @org.junit.jupiter.api.Test
    void shiftRight() {
    }

    @Test
    void rotateLeft() {
    }

    @Test
    void rotateRight() {
    }








    //Test typow z ograniczeniami bitowymi
    @Test
    void testWordType(){
        assertEquals("Qword",Calculator.TypeWord.Qword.name());
        assertEquals("Dword",Calculator.TypeWord.Dword.name());
        assertEquals("Word",Calculator.TypeWord.Word.name());
        assertEquals("Bajt",Calculator.TypeWord.Bajt.name());

        assertEquals(4, Calculator.TypeNumber.values().length);
    }






    // Konwersja typów na inne typy

    @Test
    void testNumberType(){
        assertEquals("Hex", Calculator.TypeNumber.Hex.name());
        assertEquals("Dec", Calculator.TypeNumber.Dec.name());
        assertEquals("Bin", Calculator.TypeNumber.Bin.name());
        assertEquals("Oct", Calculator.TypeNumber.Oct.name());

        assertEquals(4, Calculator.TypeWord.values().length);
    }

    @Test
    void testTypeNumberConversion() {
        int decimalValue = 255;

        assertEquals("FF", Integer.toHexString(decimalValue).toUpperCase());
        assertEquals("377", Integer.toOctalString(decimalValue));
        assertEquals("11111111", Integer.toBinaryString(decimalValue));
        assertEquals("255", Integer.toString(decimalValue));
    }

    @Test
    void testTypeWordLimits() {

        assertEquals(64, getBitSize(Calculator.TypeWord.Qword));
        assertEquals(32, getBitSize(Calculator.TypeWord.Dword));
        assertEquals(16, getBitSize(Calculator.TypeWord.Word));
        assertEquals(8, getBitSize(Calculator.TypeWord.Bajt));
    }


    private int getBitSize(Calculator.TypeWord wordType) {
        switch (wordType) {
            case Qword: return 64;
            case Dword: return 32;
            case Word:  return 16;
            case Bajt:  return 8;
            default: throw new IllegalArgumentException("Nieznany typ!");
        }
    }



    @Test
    void testConvertHexToDec() {
        String result = calculator.convertNumber("A", Calculator.TypeNumber.Hex, Calculator.TypeNumber.Dec);
        assertEquals("10", result);
    }

    @Test
    void testConvertBinToDec() {
        String result = calculator.convertNumber("1010", Calculator.TypeNumber.Bin, Calculator.TypeNumber.Dec);
        assertEquals("10", result);
    }

    @Test
    void testConvertOctToDec() {
        String result = calculator.convertNumber("12", Calculator.TypeNumber.Oct, Calculator.TypeNumber.Dec);
        assertEquals("10", result);
    }

    @Test
    void testConvertDecToHex() {
        String result = calculator.convertNumber("10", Calculator.TypeNumber.Dec, Calculator.TypeNumber.Hex);
        assertEquals("A", result);
    }

    @Test
    void testConvertOctToHex() {
        String result = calculator.convertNumber("12", Calculator.TypeNumber.Oct, Calculator.TypeNumber.Hex);
        assertEquals("A", result);
    }

    @Test
    void testConvertBinToHex() {
        String result = calculator.convertNumber("1010", Calculator.TypeNumber.Bin, Calculator.TypeNumber.Hex);
        assertEquals("A", result);
    }

    @Test
    void testConvertDecToBin() {
        String result = calculator.convertNumber("10", Calculator.TypeNumber.Dec, Calculator.TypeNumber.Bin);
        assertEquals("1010", result);
    }
    @Test
    void testConvertOctToBin() {
        String result = calculator.convertNumber("12", Calculator.TypeNumber.Oct, Calculator.TypeNumber.Bin);
        assertEquals("1010", result);
    }

    @Test
    void testConvertHexToBin() {
        String result = calculator.convertNumber("A", Calculator.TypeNumber.Hex, Calculator.TypeNumber.Bin);
        assertEquals("1010", result);
    }

    @Test
    void testConvertDecToOct() {
        String result = calculator.convertNumber("10", Calculator.TypeNumber.Dec, Calculator.TypeNumber.Oct);
        assertEquals("12", result);
    }

    @Test
    void testConvertHexToOct() {
        String result = calculator.convertNumber("1010", Calculator.TypeNumber.Bin, Calculator.TypeNumber.Oct);
        assertEquals("12", result);
    }

    @Test
    void testConvertBinToOct() {
        String result = calculator.convertNumber("A", Calculator.TypeNumber.Hex, Calculator.TypeNumber.Oct);
        assertEquals("12", result);
    }

}