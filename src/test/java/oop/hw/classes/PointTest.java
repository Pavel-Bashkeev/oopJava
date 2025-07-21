package oop.hw.classes;

import oop.hw.classes.points.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PointTest {

    @Test
    void testPoint() {
        Point p1 = new  Point(3, 5);
        Point p2 = new  Point(25, 6);
        Point p3 = new  Point(7, 8);

        assertEquals("{3;5}", p1.toString());
        assertEquals("{25;6}", p2.toString());
        assertEquals("{7;8}", p3.toString());
    }

    @Test
    void testGetters() {
        Point p1 = new  Point(3, 5);

        assertEquals(3, p1.getX());
        assertEquals(5, p1.getY());
    }

    @Test
    void testSetters() {
        Point p1 = new  Point(3, 5);
        p1.setX(5);
        p1.setY(10);
        assertEquals(5, p1.getX());
        assertEquals(10, p1.getY());
    }
}
