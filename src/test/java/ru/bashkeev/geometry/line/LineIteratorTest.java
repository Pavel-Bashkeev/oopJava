package ru.bashkeev.geometry.line;

import org.junit.jupiter.api.Test;
import ru.bashkeev.geometry.interfaces.PointIterator;
import ru.bashkeev.geometry.points.Point;
import ru.bashkeev.geometry.points.PointFactory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class LineIteratorTest {

    @Test
    void testBrokenLineIterator() {
        Point p1 =  PointFactory.getInstance().createPoint(0, 0);
        Point p2 =  PointFactory.getInstance().createPoint(1, 1);
        Point p3 =  PointFactory.getInstance().createPoint(2, 2);
        List<Point> points = new ArrayList<>(List.of(p1, p2, p3));
        BrokenLine line = new BrokenLine(points);
        PointIterator iterator = line.iterator(1);

        assertEquals(PointFactory.getInstance().createPoint(1, 1), iterator.currentPoint());
        assertTrue(iterator.hasNext());

        Point next = iterator.nextPoint();
        assertEquals(PointFactory.getInstance().createPoint(2, 2), next);
        assertFalse(iterator.hasNext());
    }

    @Test
    void testClosedLineIterator() {
        Point p1 =  PointFactory.getInstance().createPoint(0, 0);
        Point p2 =  PointFactory.getInstance().createPoint(1, 1);
        Point p3 =  PointFactory.getInstance().createPoint(2, 2);
        List<Point> points = new ArrayList<>(List.of(p1, p2, p3));
        ClosedBrokenLine line     = new ClosedBrokenLine(points);
        PointIterator    iterator = line.iterator(1);
        
        Point next = iterator.nextPoint();
        assertEquals(PointFactory.getInstance().createPoint(2, 2), next);

        next = iterator.nextPoint();
        assertEquals(PointFactory.getInstance().createPoint(0, 0), next);

        next = iterator.nextPoint();
        assertEquals(PointFactory.getInstance().createPoint(0, 0), next);

        next = iterator.nextPoint();
        assertEquals(PointFactory.getInstance().createPoint(1, 1), next);
    }

    @Test
    void testEmptyLine() {
        BrokenLine emptyLine = new BrokenLine();
        PointIterator iterator = emptyLine.iterator();

        assertThrows(IllegalStateException.class, iterator::currentPoint);
        assertThrows(IllegalStateException.class, iterator::nextPoint);
    }

    @Test
    void testReset() {
        List<Point> points = List.of(PointFactory.getInstance().createPoint(0, 0), PointFactory.getInstance().createPoint(1, 1));
        BrokenLine line = new BrokenLine(points);
        PointIterator iterator = line.iterator();

        iterator.nextPoint(); // Переходим к следующей
        iterator.reset(); // Сбрасываем

        assertEquals(PointFactory.getInstance().createPoint(0, 0), iterator.currentPoint());
    }
}