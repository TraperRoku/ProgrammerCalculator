package main;

public class Calculator {

    private double value;
    public Calculator(double value){
    this.value = value;
    }


    public enum TypeNumber{
    Hex,Dec,Oct,Bin
    }
    public enum TypeWord{
        Qword,Dword,Word,Bajt
    }

    public String convertNumber(String number, TypeNumber fromType, TypeNumber toType) {
        int decimalValue = 0;
        switch (fromType) {
            case Hex:
                decimalValue = Integer.parseInt(number, 16);
                break;
            case Bin:
                decimalValue = Integer.parseInt(number, 2);
                break;
            case Oct:
                decimalValue = Integer.parseInt(number, 8);
                break;
            case Dec:
                decimalValue = Integer.parseInt(number);
                break;
        }


        switch (toType) {
            case Hex:
                return Integer.toHexString(decimalValue).toUpperCase();
            case Bin:
                return Integer.toBinaryString(decimalValue);
            case Oct:
                return Integer.toOctalString(decimalValue);
            case Dec:
                return Integer.toString(decimalValue);
        }
        return null;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) throws IllegalArgumentException {
        if (b == 0) {
            throw new IllegalArgumentException("Nie można dzielić przez zero!");
        }
        return a / b;
    }


    public int bitAnd(int a, int b) {
        return a & b;
    }

    public int bitOr(int a, int b) {
        return a | b;
    }

    public int bitXor(int a, int b) {
        return a ^ b;
    }

    public int bitNot(int a) {
        return ~a;
    }

    public int shiftLeft(int a, int n) {
        return a << n;
    }

    public int shiftRight(int a, int n) {
        return a >> n;
    }

    public int rotateLeft(int value, int n) {
        int size = Integer.SIZE; // Dla int to 32 bity
        return (value << n) | (value >>> (size - n));
    }

    public int rotateRight(int value, int n) {
        int size = Integer.SIZE; // Dla int to 32 bity
        return (value >>> n) | (value << (size - n));
    }


    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
