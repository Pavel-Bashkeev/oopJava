package ru.bashkeev.geometry.line;

import ru.bashkeev.geometry.points.Point;
import ru.bashkeev.geometry.interfaces.Measurable;

import java.util.Arrays;

public class Example {
    public static void demo() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 4);
        Point p3 = new Point(6, 8);
        Point p4 = new Point(9, 12);

        Line line1 = new Line(p1, p2);
        Line line2 = new Line(p2, p3);
        BrokenLine brokenLine = new BrokenLine(Arrays.asList(p1, p2, p3, p4));

        Measurable[] objects = {line1, line2, brokenLine};

        double totalLength = LengthCalculator.calculateTotalLength(objects);
        System.out.println("Общая длина: " + totalLength);
    }
}
