package ru.bashkeev.arithmetic;

import java.util.HashMap;
import java.util.Map;

public class FractionGenerator {
    private static FractionGenerator instance;
    private final Map<String, Fraction> fractionCache = new HashMap<>();

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

        int nod = nod(Math.abs(numerator), Math.abs(denominator));
        int canonicalNumerator = numerator / nod;
        int canonicalDenominator = denominator / nod;

        if (canonicalDenominator < 0) {
            canonicalNumerator = -canonicalNumerator;
            canonicalDenominator = -canonicalDenominator;
        }

        String key = canonicalNumerator + "/" + canonicalDenominator;

        final int tmpNum = canonicalNumerator;
        final int tmpDenominator = canonicalDenominator;
        return fractionCache.computeIfAbsent(key, k ->
                new Fraction(tmpNum, tmpDenominator)
        );
    }

    public Fraction createFraction(int wholeNumber) {
        return createFraction(wholeNumber, 1);
    }

    private static int nod(int a, int b) {
        return b == 0 ? a : nod(b, a % b);
    }
}