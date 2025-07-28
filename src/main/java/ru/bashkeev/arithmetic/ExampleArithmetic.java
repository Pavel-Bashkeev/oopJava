package ru.bashkeev.arithmetic;

import java.math.BigInteger;

public class ExampleArithmetic {
    public static void demo() {

        int a = 7;
        Fraction b = new Fraction(11, 3);
        float c = 3.21f;
        BigInteger d = new BigInteger("12345678912345678912");

        double result = Arithmetic.sum(a, b, c, d);
        System.out.println("Sum result: " + result);
    }
}