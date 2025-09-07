package ru.bashkeev.geometry.line;

import ru.bashkeev.geometry.points.Point;
import org.junit.jupiter.api.Test;
import ru.bashkeev.geometry.points.PointFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClosedBrokenLineTest {

    @Test
    void testEmptyLineThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new ClosedBrokenLine(List.of()));
    }

    @Test
    void testSinglePointThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new ClosedBrokenLine(List.of(PointFactory.getInstance().createPoint(0, 0))));
    }

    @Test
    void testTwoPointsThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new ClosedBrokenLine(List.of(PointFactory.getInstance().createPoint(0, 0), PointFactory.getInstance().createPoint(0, 0))));
    }

    @Test
    void testThreePointsValid() {
        Point p1 = PointFactory.getInstance().createPoint(0, 0);
        Point p2 = PointFactory.getInstance().createPoint(1, 0);
        assertDoesNotThrow(() ->
                new ClosedBrokenLine(List.of(p1, p2, p1)));
    }

    @Test
    void testTriangleLength() {
        Point p1 = PointFactory.getInstance().createPoint(0, 0);
        Point p2 = PointFactory.getInstance().createPoint(3, 0);
        Point p3 = PointFactory.getInstance().createPoint(0, 4);
        ClosedBrokenLine line = new ClosedBrokenLine(List.of(p1, p2, p3, p1));

        assertEquals(12.0, line.getLength()); // 3 + 4 + 5
    }

    @Test
    void testNotClosedLineThrowsException() {
        Point p1 = PointFactory.getInstance().createPoint(0, 0);
        Point p2 = PointFactory.getInstance().createPoint(1, 0);
        Point p3 = PointFactory.getInstance().createPoint(1, 1);

        assertThrows(UnsupportedOperationException.class, () ->
                new ClosedBrokenLine(List.of(p1, p2, p3)));
    }

    @Test
    void testSetPointsValidatesClosure() {
        Point p1 = PointFactory.getInstance().createPoint(0, 0);
        Point p2 = PointFactory.getInstance().createPoint(1, 0);
        Point p3 = PointFactory.getInstance().createPoint(0, 1);

        ClosedBrokenLine line = new ClosedBrokenLine(List.of(p1, p2, p3, p1));

        assertThrows(UnsupportedOperationException.class, () ->
                line.setPoints(List.of(p1, p2, p3)));

        assertThrows(IllegalArgumentException.class, () ->
                line.setPoints(List.of(p1, p1)));
    }

    @Test
    void testAddPointsMaintainsClosure() {
        Point p1 = PointFactory.getInstance().createPoint(0, 0);
        Point p2 = PointFactory.getInstance().createPoint(1, 0);
        Point p3 = PointFactory.getInstance().createPoint(0, 1);

        ClosedBrokenLine line = new ClosedBrokenLine(List.of(p1, p2, p3, p1));
        line.addPoint(PointFactory.getInstance().createPoint(1, 1));
        assertEquals(5, line.getPoints().size());
    }

    @Test
    void testToString() {
        Point p1 = PointFactory.getInstance().createPoint(0, 0);
        Point p2 = PointFactory.getInstance().createPoint(1, 0);
        Point p3 = PointFactory.getInstance().createPoint(0, 1);
        ClosedBrokenLine line = new ClosedBrokenLine(List.of(p1, p2, p3, p1));

        String str = line.toString();
        assertEquals("Замкнутая Линия [{0;0}, {1;0}, {0;1}, {0;0}]", line.toString());
    }
}
