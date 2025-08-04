package ru.bashkeev.arithmetic;

import java.util.ArrayList;
import java.util.List;

public class NumberDivider {

    public static List<Double> divideFirstByNumbers(List<String> strings) {
        List<Double> numbers = new ArrayList<>();
        List<Double> results = new ArrayList<>();

        for (String s : strings) {
            try {
                numbers.add(Double.parseDouble(s));
            } catch (NumberFormatException e) {}
        }

        if (numbers.size() < 2) {
            return results;
        }

        double firstNumber = numbers.getFirst();

        for (int i = 1; i < numbers.size(); i++) {
            try {
                results.add(firstNumber / numbers.get(i));
            } catch (ArithmeticException e) {
                results.add(Double.POSITIVE_INFINITY);
            }
        }

        return results;
    }
}
