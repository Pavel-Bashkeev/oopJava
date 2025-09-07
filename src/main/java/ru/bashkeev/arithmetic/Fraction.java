package ru.bashkeev.arithmetic;

import java.util.Objects;

public final class Fraction extends Number {
    private final int numerator;
    private final int denominator;

    Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("Знаменатель не может быть 0");
        }

        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }

        int nod = nod(Math.abs(numerator), Math.abs(denominator));
        this.numerator = numerator / nod;
        this.denominator = denominator / nod;
    }

    Fraction(int wholeNumber) {
        this(wholeNumber, 1);
    }

    private static int nod(int a, int b) {
        return b == 0 ? a : nod(b, a % b);
    }

    @Override
    public int intValue() {
        return numerator / denominator;
    }

    @Override
    public long longValue() {
        return (long) numerator / denominator;
    }

    @Override
    public float floatValue() {
        return (float) numerator / denominator;
    }

    @Override
    public double doubleValue() {
        return (double) numerator / denominator;
    }

    public Fraction plus(Fraction secondOperand) {
        int newNumerator = this.numerator * secondOperand.denominator +
                this.denominator * secondOperand.numerator;
        int newDenominator = this.denominator * secondOperand.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    public Fraction plus(int secondOperand) {
        return this.plus(new Fraction(secondOperand));
    }

    public Fraction minus(Fraction secondOperand) {
        int newNumerator = this.numerator * secondOperand.denominator -
                this.denominator * secondOperand.numerator;
        int newDenominator = this.denominator * secondOperand.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    public Fraction minus(int secondOperand) {
        return this.minus(new Fraction(secondOperand));
    }

    public Fraction multiply(Fraction secondOperand) {
        return new Fraction(
                this.numerator * secondOperand.numerator,
                this.denominator * secondOperand.denominator
        );
    }

    public Fraction multiply(int secondOperand) {
        return this.multiply(new Fraction(secondOperand));
    }

    public Fraction divide(Fraction secondOperand) {
        return new Fraction(
                this.numerator * secondOperand.denominator,
                this.denominator * secondOperand.numerator
        );
    }

    public Fraction divide(int secondOperand) {
        return this.divide(new Fraction(secondOperand));
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Fraction other = (Fraction) obj;
        return this.numerator == other.numerator && this.denominator == other.denominator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    @Override
    public Fraction clone() {
        try {
            return (Fraction) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Fraction cloning failed", e);
        }
    }

    @Override
    public String toString() {
        return denominator == 1 ?
                String.valueOf(numerator) :
                numerator + "/" + denominator;
    }
}