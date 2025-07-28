package oop.hw.classes.chapterTwo;

import ru.bashkeev.arithmetic.Arithmetic;
import ru.bashkeev.arithmetic.Fraction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArithmeticTest {

    @Test
    void testSumWithIntegerFractionDouble() {
        // (Integer, Fraction, Double) → 5 + 3/4 + 2.5 = 8.25
        double result = Arithmetic.sum(5, new Fraction(3, 4), 2.5);
        assertEquals(8.25, result, 1e-9); // Погрешность 1e-9 для double
    }

    @Test
    void testSumWithDoubleFractionIntegerFraction() {
        // (Double, Fraction, Integer, Fraction) → 1.2 + 1/2 + 3 + 2/3 ≈ 5.366666666666666
        double result = Arithmetic.sum(1.2, new Fraction(1, 2), 3, new Fraction(2, 3));
        assertEquals(5.366666666666666, result, 1e-9);
    }

    @Test
    void testSumWithFractionInteger() {
        // (Fraction, Integer) → 7/8 + 4 = 4.875
        double result = Arithmetic.sum(new Fraction(7, 8), 4);
        assertEquals(4.875, result, 1e-9);
    }

    @Test
    void testSumWithNegativeNumbers() {
        // (-2.5, Fraction(-1, 2), 3) → -2.5 + (-0.5) + 3 = 0.0
        double result = Arithmetic.sum(-2.5, new Fraction(-1, 2), 3);
        assertEquals(0.0, result, 1e-9);
    }

    @Test
    void testSumWithZero() {
        // (0, Fraction(0, 1), 0.0) → 0 + 0 + 0 = 0
        double result = Arithmetic.sum(0, new Fraction(0, 1), 0.0);
        assertEquals(0.0, result, 1e-9);
    }

    @Test
    void testSumWithSingleNumber() {
        // (Fraction(5, 2)) → 2.5
        double result = Arithmetic.sum(new Fraction(5, 2));
        assertEquals(2.5, result, 1e-9);
    }
}