package oop.hw.classes;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BrokenLineTest {
    @Test
    void testBrokenLine() {
        // 1. Создать первую Ломаную, проходящую через точки {1;5}, {2;8}, {5;3}
        Point p1 = new Point(1, 5);
        Point p2 = new Point(2, 8);
        Point p3 = new Point(5, 3);
        BrokenLine line = new BrokenLine(Arrays.asList(p1, p2, p3));

        assertEquals("Линия [{1;5}, {2;8}, {5;3}]",  line.toString());
    }

    @Test
    void testBrokenLine2() {
        // 2. Создайте вторую Ломаную, чья первая и последняя Точка совпадает с таковыми у первой Ломаной, но в качестве середины имеет точки: {2,-5}, {4,-8}
        Point p1 = new Point(1, 5);
        Point p2 = new Point(2, 8);
        Point p3 = new Point(5, 3);
        BrokenLine line = new BrokenLine(Arrays.asList(p1, p2, p3));

        List<Point> pointsLine = line.getPoints();
        Point startLine = pointsLine.getFirst();
        Point endLine = pointsLine.getLast();

        Point p4 = new Point(2, -4);
        Point p5 = new Point(4, -8);

        BrokenLine line2 = new BrokenLine(Arrays.asList(startLine, p4, p5, endLine));

        assertEquals("Линия [{1;5}, {2;8}, {5;3}]",  line.toString());
        assertEquals("Линия [{1;5}, {2;-4}, {4;-8}, {5;3}]",  line2.toString());
    }

    @Test
    void testDependentChange() {
        Point p1 = new Point(1, 5);
        Point p2 = new Point(2, 8);
        Point p3 = new Point(5, 3);
        BrokenLine line = new BrokenLine(Arrays.asList(p1, p2, p3));

        List<Point> pointsLine = line.getPoints();
        Point startLine = pointsLine.getFirst();
        Point endLine = pointsLine.getLast();

        Point p4 = new Point(2, -4);
        Point p5 = new Point(4, -8);

        BrokenLine line2 = new BrokenLine(Arrays.asList(startLine, p4, p5, endLine));

        assertEquals("Линия [{1;5}, {2;8}, {5;3}]",  line.toString());
        assertEquals("Линия [{1;5}, {2;-4}, {4;-8}, {5;3}]",  line2.toString());

        startLine.setX(2);

        assertEquals("Линия [{2;5}, {2;8}, {5;3}]",  line.toString());
        assertEquals("Линия [{2;5}, {2;-4}, {4;-8}, {5;3}]",  line2.toString());
    }

    @Test
    void testIndependentLines() {
        Point p1 = new Point(1, 5);
        Point p2 = new Point(2, 8);
        Point p3 = new Point(5, 3);
        BrokenLine line1 = new BrokenLine(Arrays.asList(p1, p2, p3));

        Point p1Copy = new Point(p1.getX(), p1.getY());
        Point p3Copy = new Point(p3.getX(), p3.getY());
        Point p4 = new Point(2, -4);
        Point p5 = new Point(4, -8);
        BrokenLine line2 = new BrokenLine(Arrays.asList(p1Copy, p4, p5, p3Copy));

        assertEquals("Линия [{1;5}, {2;8}, {5;3}]", line1.toString());
        assertEquals("Линия [{1;5}, {2;-4}, {4;-8}, {5;3}]", line2.toString());

        p1.setX(10);

        assertEquals("Линия [{10;5}, {2;8}, {5;3}]", line1.toString());
        assertEquals("Линия [{1;5}, {2;-4}, {4;-8}, {5;3}]", line2.toString());
    }

    @Test
    void testEmptyPoints() {
        BrokenLine line = new BrokenLine();

        assertEquals("Линия []",  line.toString());
    }

    @Test
    void testAddMultiplePoints() {
        BrokenLine line = new BrokenLine();
        line.addPoints(Arrays.asList(
                new Point(1, 1),
                new Point(2, 2)
        ));

        assertEquals(2, line.getPoints().size());
        assertEquals("Линия [{1;1}, {2;2}]", line.toString());

        line.addPoint(new Point(4, 1));

        assertEquals(3, line.getPoints().size());
        assertEquals("Линия [{1;1}, {2;2}, {4;1}]", line.toString());
    }

    @Test
    void testSetPoints() {
        BrokenLine line = new BrokenLine();
        line.setPoints(Arrays.asList(
                new Point(1, 1),
                new Point(2, 2),
                new Point(3, 3)
        ));

        assertEquals(3, line.getPoints().size());
        assertEquals("Линия [{1;1}, {2;2}, {3;3}]", line.toString());
    }

    @Test
    void testGetLengthWithTwoPoints() {
        BrokenLine brokenLine = new BrokenLine(List.of(
                new Point(0, 0),
                new Point(3, 4)
        ));

        assertEquals(5.0, brokenLine.getLength());
    }

    @Test
    void testGetLengthWithMultiplePoints() {
        BrokenLine brokenLine = new BrokenLine(List.of(
                new Point(1, 1),
                new Point(4, 5),
                new Point(7, 9)
        ));

        assertEquals(10.0, brokenLine.getLength());
    }

    @Test
    void testGetLengthAfterAddingPoints() {
        BrokenLine brokenLine = new BrokenLine(List.of(
                new Point(1, 1),
                new Point(4, 5)
        ));

        double initialLength = brokenLine.getLength();
        assertEquals(5.0, initialLength);

        brokenLine.addPoint(new Point(7, 9));
        double updatedLength = brokenLine.getLength();

        assertEquals(10.0, updatedLength);
    }
}
