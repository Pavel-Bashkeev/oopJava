package ru.bashkeev.arithmetic;

import java.math.BigInteger;

public class Arithmetic {
    public static double sum(Number... numbers) {
        double total = 0.0;
        for (Number num : numbers) {
            if (num instanceof BigInteger) {
                total += ((BigInteger) num).doubleValue();
            } else {
                total += num.doubleValue();
            }
        }
        return total;
    }
}
