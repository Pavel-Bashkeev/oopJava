package ru.bashkeev.arithmetic;

public class FractionGenerator {
    public Fraction createFraction(int numerator, int denominator) {
        return new Fraction(numerator, denominator);
    }

    public Fraction createFraction(int numerator) {
        return new Fraction(numerator, 1);
    }
}
