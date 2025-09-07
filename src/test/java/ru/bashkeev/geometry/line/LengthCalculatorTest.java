package ru.bashkeev.geometry.line;

import ru.bashkeev.geometry.points.Point;
import ru.bashkeev.geometry.interfaces.Measurable;
import org.junit.jupiter.api.Test;
import ru.bashkeev.geometry.points.PointFactory;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LengthCalculatorTest {
    @Test
    void testCalculateTotalLength() {
        Point p1 = PointFactory.getInstance().createPoint(0, 0);
        Point p2 = PointFactory.getInstance().createPoint(3, 0);
        Point p3 = PointFactory.getInstance().createPoint(3, 4);

        Line line = new Line(p1, p2); // Длина = 3
        BrokenLine brokenLine = new BrokenLine(Arrays.asList(p1, p2, p3)); // Длина = 7

        Measurable[] objects = {line, brokenLine};
        assertEquals(10, LengthCalculator.calculateTotalLength(objects));
    }

    @Test
    void testEmptyArray() {
        Measurable[] objects = new Measurable[0];
        assertEquals(0, LengthCalculator.calculateTotalLength(objects));
    }
}