package ru.bashkeev.arithmetic;

import static java.lang.Math.pow;
import static java.lang.Integer.parseInt;

public class PowerCalculator {
    public static double calculatePower(String xStr, String yStr) {
        int x = parseInt(xStr);
        int y = parseInt(yStr);
        return pow(x, y);
    }
}
