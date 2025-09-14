package ru.bashkeev.arithmetic;

public class FractionGenerator {
    private static FractionGenerator instance;

    private FractionGenerator() {
    }

    public static FractionGenerator getInstance() {
        if (instance == null) {
            instance = new FractionGenerator();
        }

        return instance;
    }

    public Fraction createFraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("Знаменатель не может быть 0");
        }
        return new Fraction(numerator, denominator);
    }

    public Fraction createFraction(int wholeNumber) {
        return new Fraction(wholeNumber);
    }
}
