package ru.bashkeev.geometry.processing;

import org.junit.jupiter.api.Test;
import ru.bashkeev.geometry.line.BrokenLine;
import ru.bashkeev.geometry.points.Point;
import ru.bashkeev.geometry.points.PointFactory;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PointProcessorTest {

    @Test
    void testProcessPoints() {
        PointFactory factoryPoint = PointFactory.getInstance();

        Point p1 = factoryPoint.createPoint(3, 4);
        Point p2 = factoryPoint.createPoint(1, 2);
        Point p3 = factoryPoint.createPoint(3, 4);
        Point p4 = factoryPoint.createPoint(5, -6);
        Point p5 = factoryPoint.createPoint(2, -3);
        Point p6 = factoryPoint.createPoint(1, 2);

        List<Point> points = Arrays.asList(p1, p2, p3, p4, p5, p6);

        BrokenLine result = PointProcessor.processPoints(points);

        List<Point> resultPoints = result.getPoints();
        assertEquals(4, resultPoints.size());
        System.out.println(resultPoints);
        assertEquals(1, resultPoints.get(0).getX());
        assertEquals(2, resultPoints.get(1).getX());
        assertEquals(3, resultPoints.get(2).getX());
        assertEquals(5, resultPoints.get(3).getX());
        assertEquals(6, resultPoints.get(3).getY());
        assertEquals(3, resultPoints.get(1).getY());
    }

    @Test
    void testProcessPointsWithAllNegativeY() {
        PointFactory factoryPoint = PointFactory.getInstance();

        Point p1 = factoryPoint.createPoint(1, -5);
        Point p2 = factoryPoint.createPoint(2, -10);
        Point p3 = factoryPoint.createPoint(3, -15);

        List<Point> points = Arrays.asList(p1, p2, p3);

        BrokenLine result = PointProcessor.processPoints(points);

        List<Point> resultPoints = result.getPoints();
        assertTrue(resultPoints.stream().allMatch(p -> p.getY() >= 0));
        assertEquals(5, resultPoints.get(0).getY());
        assertEquals(10, resultPoints.get(1).getY());
        assertEquals(15, resultPoints.get(2).getY());
    }
}
