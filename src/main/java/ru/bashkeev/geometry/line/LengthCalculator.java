package ru.bashkeev.geometry.line;

import ru.bashkeev.geometry.interfaces.Measurable;

public class LengthCalculator {
    public static double calculateTotalLength(Measurable[] objects) {
        double totalLength = 0;
        for (Measurable obj : objects) {
            totalLength += obj.getLength();
        }
        return totalLength;
    }
}
