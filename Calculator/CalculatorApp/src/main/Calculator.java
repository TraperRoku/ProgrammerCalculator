package main;

import java.math.BigInteger;

public class Calculator {

    private BigInteger value;

    public Calculator(double value) {
        this.value = BigInteger.valueOf((long) value);
    }

    public int getBaseValue(TypeNumber base) {
        return switch (base) {
            case Hex -> 16;
            case Oct -> 8;
            case Bin -> 2;
            default -> 10;
        };
    }

    public enum TypeNumber {
        Hex, Dec, Oct, Bin
    }

    public enum TypeWord {
        Qword, Dword, Word, Bajt
    }

    public String convertNumber(String number, TypeNumber fromType, TypeNumber toType, TypeWord currentTypeWord) {

        BigInteger bigInteger = switch (fromType) {
            case Hex -> new BigInteger(number, 16);
            case Bin -> new BigInteger(number, 2);
            case Oct ->  new BigInteger(number, 8);
            case Dec -> new BigInteger(number); // Decimal
        };

        int bitWidth = switch (currentTypeWord) {
            case Bajt -> 8;
            case Word -> 16;
            case Dword -> 32;
            case Qword -> 64;
        };

        BigInteger mask = BigInteger.ONE.shiftLeft(bitWidth).subtract(BigInteger.ONE);
        BigInteger twoComplementBase = BigInteger.ONE.shiftLeft(bitWidth);
        if (bigInteger.signum() < 0) {
            bigInteger = bigInteger.add(twoComplementBase);
        }

        bigInteger = bigInteger.and(mask);

        switch (toType) {
            case Hex:
                return bigInteger.toString(16).toUpperCase();
            case Bin:
                return bigInteger.toString(2);
            case Oct:
                return bigInteger.toString(8);
            case Dec:
                if (bigInteger.testBit(bitWidth - 1)) {
                    return bigInteger.subtract(twoComplementBase).toString();
                } else {
                    return bigInteger.toString();
                }
        }

        return null;
    }



    public int getBaseWord(TypeWord word) {
        return switch (word) {
            case Bajt -> 8;
            case Dword -> 32;
            case Word -> 16;
            default -> 64;
        };
    }

    public BigInteger add(BigInteger a, BigInteger b) {
        return a.add(b);
    }

    public BigInteger subtract(BigInteger a, BigInteger b) {
        return a.subtract(b);
    }

    public BigInteger multiply(BigInteger a, BigInteger b) {
        return a.multiply(b);
    }

    public BigInteger divide(BigInteger a, BigInteger b) throws IllegalArgumentException {
        if (b.equals(BigInteger.ZERO)) {
            throw new IllegalArgumentException("Nie można dzielić przez zero!");
        }
        return a.divide(b);
    }

    public BigInteger bitAnd(BigInteger a, BigInteger b) {
        return a.and(b);
    }

    public BigInteger bitOr(BigInteger a, BigInteger b) {
        return a.or(b);
    }

    public BigInteger bitXor(BigInteger a, BigInteger b) {
        return a.xor(b);
    }

    public BigInteger bitNot(BigInteger a) {
        return a.not();
    }
    public BigInteger mod(BigInteger a,BigInteger m){
        return a.mod(m);
    }

    public BigInteger shiftLeft(BigInteger a, BigInteger n) {
        int newN = n.intValue();
        return a.shiftLeft(newN);
    }

    public BigInteger shiftRight(BigInteger a, BigInteger n) {
        int newN = n.intValue();
        return a.shiftRight(newN);
    }

    public BigInteger rotateLeft(TypeWord typeWord, BigInteger value) {
        int bitSize = getBaseWord(typeWord);

        BigInteger mask = BigInteger.ONE.shiftLeft(bitSize).subtract(BigInteger.ONE);
        value = value.and(mask);

        return value.shiftLeft(1).or(value.shiftRight(bitSize - 1)).and(mask);
    }

    public BigInteger rotateRight(TypeWord typeWord, BigInteger value) {
        int bitSize = getBaseWord(typeWord);


        BigInteger mask = BigInteger.ONE.shiftLeft(bitSize).subtract(BigInteger.ONE);
        value = value.and(mask);

        return value.shiftRight(1).or(value.shiftLeft(bitSize - 1)).and(mask);
    }

    public BigInteger getValue() {
        return value;
    }

    public void setValue(BigInteger value) {
        this.value = value;
    }
}
