package ru.bashkeev.geometry.line;

import org.junit.jupiter.api.Test;
import ru.bashkeev.geometry.points.Point;
import ru.bashkeev.geometry.points.PointFactory;

import static org.junit.jupiter.api.Assertions.*;

public class CachedLineTest {

    @Test
    void testCachedLineReturnsSameValueAsBaseLine() {
        Point start = PointFactory.getInstance().createPoint(0, 0);
        Point end = PointFactory.getInstance().createPoint(3, 4);

        Line<Point> baseLine = new Line<>(start, end);
        CachedLine<Point> cachedLine = new CachedLine<>(start, end);
        
        assertEquals(baseLine.getLength(), cachedLine.getLength(), 0.001);
    }

    @Test
    void testCacheWorksOnSecondCall() {
        Point start = PointFactory.getInstance().createPoint(0, 0);
        Point end = PointFactory.getInstance().createPoint(3, 4);
        CachedLine<Point> line = new CachedLine<>(start, end);
        
        double firstCall = line.getLength();

        double secondCall = line.getLength();

        assertEquals(firstCall, secondCall, 0.001);
    }

    @Test
    void testCacheInvalidatesWhenStartPointChanged() {
        Point start1 = PointFactory.getInstance().createPoint(0, 0);
        Point start2 = PointFactory.getInstance().createPoint(1, 1);
        Point end = PointFactory.getInstance().createPoint(3, 4);

        CachedLine<Point> line = new CachedLine<>(start1, end);

        double length1 = line.getLength();

        line.setStartPoint(start2);

        double length2 = line.getLength();

        assertNotEquals(length1, length2, 0.001);
    }

    @Test
    void testCacheInvalidatesWhenEndPointChanged() {
        Point start = PointFactory.getInstance().createPoint(0, 0);
        Point end1 = PointFactory.getInstance().createPoint(3, 4);
        Point end2 = PointFactory.getInstance().createPoint(6, 8);

        CachedLine<Point> line = new CachedLine<>(start, end1);
        
        double length1 = line.getLength();
        line.setEndPoint(end2);
        
        double length2 = line.getLength();

        assertNotEquals(length1, length2, 0.001);
    }

    @Test
    void testCacheWorksWithDependentPoints() {
        Point start = PointFactory.getInstance().createPoint(0, 0);
        Point end = PointFactory.getInstance().createPoint(3, 4);
        CachedLine<Point> line = new CachedLine<>(start, end);
        
        double originalLength = line.getLength();
        System.out.println(originalLength);

        Point newStart = PointFactory.getInstance().createPoint(1, 1);
        line.setStartPoint(newStart);
        double newLength = line.getLength();


        System.out.println(newLength);
        assertNotEquals(originalLength, newLength, 0.001);
    }

    @Test
    void testMultipleCachedLinesWorkIndependently() {
        Point start1 = PointFactory.getInstance().createPoint(0, 0);
        Point end1 = PointFactory.getInstance().createPoint(3, 4);
        Point start2 = PointFactory.getInstance().createPoint(1, 1);
        Point end2 = PointFactory.getInstance().createPoint(4, 6);

        CachedLine<Point> line1 = new CachedLine<>(start1, end1);
        CachedLine<Point> line2 = new CachedLine<>(start2, end2);

        double length1 = line1.getLength();
        double length2 = line2.getLength();

        assertNotEquals(length1, length2, 0.001);
        assertEquals(length1, line1.getLength(), 0.001);
        assertEquals(length2, line2.getLength(), 0.001);
        assertEquals(1, line1.getCalculationCount(), 0.001, "Проверяем что происходит один раз инициализация кэша");
    }
}