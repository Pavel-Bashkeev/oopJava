package ru.bashkeev.arithmetic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FractionTest {

    @Test
    void testConstructorAndGetters() {
        Fraction f = new Fraction(3, 4);
        assertEquals(3, f.getNumerator());
        assertEquals(4, f.getDenominator());
    }

    @Test
    void testConstructorWithNegativeDenominator() {
        Fraction f = new Fraction(1, -2);
        assertEquals(-1, f.getNumerator());
        assertEquals(2, f.getDenominator());
    }


    @Test
    void testToString() {
        Fraction f = new Fraction(3, 4);
        assertEquals("3/4", f.toString());
    }

    @Test
    void testPlusFraction() {
        Fraction f1     = new Fraction(1, 2);
        Fraction f2     = new Fraction(1, 3);
        Fraction result = f1.plus(f2);
        assertEquals(5, result.getNumerator());
        assertEquals(6, result.getDenominator());
    }

    @Test
    void testPlusInteger() {
        Fraction f      = new Fraction(1, 2);
        Fraction result = f.plus(1);
        assertEquals(3, result.getNumerator());
        assertEquals(2, result.getDenominator());
    }

    @Test
    void testMinusFraction() {
        Fraction f1     = new Fraction(1, 2);
        Fraction f2     = new Fraction(1, 3);
        Fraction result = f1.minus(f2);
        assertEquals(1, result.getNumerator());
        assertEquals(6, result.getDenominator());
    }

    @Test
    void testMinusInteger() {
        Fraction f      = new Fraction(3, 2);
        Fraction result = f.minus(1);
        assertEquals(1, result.getNumerator());
        assertEquals(2, result.getDenominator());
    }

    @Test
    void testMultiplyFraction() {
        Fraction f1     = new Fraction(1, 2);
        Fraction f2     = new Fraction(2, 3);
        Fraction result = f1.multiply(f2);
        assertEquals(1, result.getNumerator());
        assertEquals(3, result.getDenominator());
    }

    @Test
    void testMultiplyInteger() {
        Fraction f      = new Fraction(1, 2);
        Fraction result = f.multiply(3);
        assertEquals(3, result.getNumerator());
        assertEquals(2, result.getDenominator());
    }

    @Test
    void testDivideFraction() {
        Fraction f1     = new Fraction(1, 2);
        Fraction f2     = new Fraction(3, 4);
        Fraction result = f1.divide(f2);
        assertEquals(2, result.getNumerator());
        assertEquals(3, result.getDenominator());
    }

    @Test
    void testDivideInteger() {
        Fraction f      = new Fraction(3, 4);
        Fraction result = f.divide(2);
        assertEquals(3, result.getNumerator());
        assertEquals(8, result.getDenominator());
    }

    @Test
    void testOperationChain() {
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(1, 3);
        Fraction f3 = new Fraction(1, 4);

        Fraction result = f1.plus(f2).multiply(f3).minus(1);

        assertEquals(-19, result.getNumerator());
        assertEquals(24, result.getDenominator());
        assertEquals("-19/24", result.toString());
    }

    @Test
    void testNegativeFractionsOperations() {
        Fraction f1 = new Fraction(-1, 2);
        Fraction f2 = new Fraction(1, -3);

        Fraction sum = f1.plus(f2);
        assertEquals(-5, sum.getNumerator());
        assertEquals(6, sum.getDenominator());

        Fraction product = f1.multiply(f2);
        assertEquals(1, product.getNumerator());
        assertEquals(6, product.getDenominator());
    }

    @Test
    void testPrintResult() {
        Fraction f1 = new Fraction(1, 3);
        Fraction f2 = new Fraction(2, 3);
        Fraction f3 = new Fraction(3, 4);

        assertEquals("Сложение: 1/3 + 2/3 = 1", "Сложение: " + f1 + " + " + f2 + " = " + f1.plus(f2));
        assertEquals("Вычитание: 1/3 - 2/3 = -1/3", "Вычитание: " + f1 + " - " + f2 + " = " + f1.minus(f2));
        assertEquals("Умножение: 1/3 * 2/3 = 2/9", "Умножение: " + f1 + " * " + f2 + " = " + f1.multiply(f2));
        assertEquals("Деление: 1/3 / 2/3 = 1/2", "Деление: " + f1 + " / " + f2 + " = " + f1.divide(f2));

        assertEquals("Сложение с целым: 1/3 + 2 = 7/3", "Сложение с целым: " + f1 + " + 2 = " + f1.plus(2));
        assertEquals("Вычитание целого: 1/3 - 1 = -2/3", "Вычитание целого: " + f1 + " - 1 = " + f1.minus(1));
        assertEquals("Умножение на целое: 1/3 * 4 = 4/3", "Умножение на целое: " + f1 + " * 4 = " + f1.multiply(4));
        assertEquals("Деление на целое: 1/3 / 2 = 1/6", "Деление на целое: " + f1 + " / 2 = " + f1.divide(2));

        assertEquals("1/3 * 2/3 = 2/9", f1 + " * " + f2 + " = " + f1.multiply(f2));

        Fraction result = f1.plus(f2).divide(f3).minus(5);
        assertEquals("(1/3 + 2/3) / 3/4 - 5 = -11/3", "(" + f1 + " + " + f2 + ") / " + f3 + " - 5 = " + result);
    }

    @Test
    void testSingletonGenerator() {
        FractionGenerator g1 = FractionGenerator.getInstance();
        FractionGenerator g2 = FractionGenerator.getInstance();

        assertSame(g1, g2, "Генератор должен быть Singleton");
    }

    @Test
    void testEqualFractionsShouldBeEqual() {
        Fraction a = FractionGenerator.getInstance().createFraction(1, 2);
        Fraction b = FractionGenerator.getInstance().createFraction(2, 4);

        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        
        assertTrue(a == b, "Дроби 1/2 и 2/4 должны быть равны");
        assertEquals(a, b, "Дроби 1/2 и 2/4 должны быть равны");
        assertEquals(a.hashCode(), b.hashCode(), "Хэши равных дробей должны совпадать");
    }

    @Test
    void testDifferentFractionsShouldNotBeEqual() {
        Fraction a = FractionGenerator.getInstance().createFraction(1, 2);
        Fraction b = FractionGenerator.getInstance().createFraction(2, 3);

        assertNotEquals(a, b, "Дроби 1/2 и 2/3 не должны быть равны");
    }

    @Test
    void testSameReferenceIsEqual() {
        Fraction a = FractionGenerator.getInstance().createFraction(3, 5);
        Fraction b = a;

        assertEquals(a, b);
    }
}