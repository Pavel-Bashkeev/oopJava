package oop.hw.classes;

public final class Fraction {
    private final int numerator;
    private final int denominator;

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("Знаменатель не может быть 0");
        }

        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }

        this.numerator = numerator;
        this.denominator = denominator;
    }

    public int getNumerator() {
        return numerator;
    }
    public int getDenominator() {
        return denominator;
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    public Fraction plus(Fraction secondOperand) {
        int numerator = this.numerator * secondOperand.getDenominator() +  this.denominator * secondOperand.getNumerator();
        int denominator = this.denominator * secondOperand.getDenominator();

        return new Fraction(numerator, denominator);
    }

    public Fraction plus(int secondOperand) {
        return this.plus(new Fraction(secondOperand, 1));
    }

    public Fraction minus(Fraction secondOperand) {
        int numerator = this.numerator * secondOperand.getDenominator() -  this.denominator * secondOperand.getNumerator();
        int denominator = this.denominator * secondOperand.getDenominator();

        return new Fraction(numerator, denominator);
    }

    public Fraction minus(int secondOperand) {
        return this.minus(new Fraction(secondOperand, 1));
    }

    public Fraction multiply(Fraction secondOperand) {
        int numerator = this.numerator * secondOperand.getNumerator();
        int denominator = this.denominator * secondOperand.getDenominator();

        return new Fraction(numerator, denominator);
    }

    public Fraction multiply(int secondOperand) {
        return this.multiply(new Fraction(secondOperand, 1));
    }

    public Fraction divide(Fraction secondOperand) {
        int numerator = this.numerator * secondOperand.getDenominator();
        int denominator = this.denominator * secondOperand.getNumerator();

        return new Fraction(numerator, denominator);
    }

    public Fraction divide(int secondOperand) {
        return this.divide(new Fraction(secondOperand, 1));
    }
}
