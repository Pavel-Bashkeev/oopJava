package ru.bashkeev.utils;

import ru.bashkeev.generics.Box;

import java.util.List;

public class BoxUtils {
    public static double findMaxValue(List<Box<? extends Number>> boxes) {
        if (boxes == null || boxes.isEmpty()) {
            throw new IllegalArgumentException("Список коробок пуст");
        }

        double max = Double.NEGATIVE_INFINITY;

        for (Box<? extends Number> box : boxes) {
            Number value = box.get();
            if (value != null) {
                double doubleValue = value.doubleValue();
                if (doubleValue > max) {
                    max = doubleValue;
                }
            }
        }

        return max;
    }
}
