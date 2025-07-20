package oop.hw.classes.chapterTwo;

import oop.hw.classes.points.Point;
import org.junit.jupiter.api.Test;

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
                new ClosedBrokenLine(List.of(new Point(0, 0))));
    }

    @Test
    void testTwoPointsThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new ClosedBrokenLine(List.of(new Point(0, 0), new Point(0, 0))));
    }

    @Test
    void testThreePointsValid() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(1, 0);
        assertDoesNotThrow(() ->
                new ClosedBrokenLine(List.of(p1, p2, p1)));
    }

    @Test
    void testTriangleLength() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 0);
        Point p3 = new Point(0, 4);
        ClosedBrokenLine line = new ClosedBrokenLine(List.of(p1, p2, p3, p1));

        assertEquals(12.0, line.getLength()); // 3 + 4 + 5
    }

    @Test
    void testNotClosedLineThrowsException() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(1, 0);
        Point p3 = new Point(1, 1);

        assertThrows(IllegalArgumentException.class, () ->
                new ClosedBrokenLine(List.of(p1, p2, p3)));
    }

    @Test
    void testSetPointsValidatesClosure() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(1, 0);
        Point p3 = new Point(0, 1);

        ClosedBrokenLine line = new ClosedBrokenLine(List.of(p1, p2, p3, p1));

        assertThrows(IllegalArgumentException.class, () ->
                line.setPoints(List.of(p1, p2, p3)));

        assertThrows(IllegalArgumentException.class, () ->
                line.setPoints(List.of(p1, p1)));
    }

    @Test
    void testAddPointsMaintainsClosure() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(1, 0);
        Point p3 = new Point(0, 1);

        ClosedBrokenLine line = new ClosedBrokenLine(List.of(p1, p2, p3, p1));
        line.addPoint(new Point(1, 1));
        assertEquals(5, line.getPoints().size());
    }

    @Test
    void testToString() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(1, 0);
        Point p3 = new Point(0, 1);
        ClosedBrokenLine line = new ClosedBrokenLine(List.of(p1, p2, p3, p1));

        String str = line.toString();
        assertEquals("Замкнутая Линия [{0;0}, {1;0}, {0;1}, {0;0}]", line.toString());
    }
}
