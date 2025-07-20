package oop.hw.classes.chapterTwo;

import oop.hw.classes.Point;
import oop.hw.classes.Point3D;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Point3DTest {

    @Test
    void testConstructorWithCoordinates() {
        Point3D point = new Point3D(1, 2, 3);
        assertEquals(1, point.getX());
        assertEquals(2, point.getY());
        assertEquals(3, point.getZ());
    }

    @Test
    void testDefaultConstructor() {
        Point3D point = new Point3D();
        assertEquals(0, point.getX());
        assertEquals(0, point.getY());
        assertEquals(0, point.getZ());
    }

    @Test
    void testSetters() {
        Point3D point = new Point3D();
        point.setX(5);
        point.setY(10);
        point.setZ(15);

        assertEquals(5, point.getX());
        assertEquals(10, point.getY());
        assertEquals(15, point.getZ());
    }

    @Test
    void testToString() {
        Point3D point = new Point3D(1, 2, 3);
        assertEquals("{1;2;3}", point.toString());
    }

    @Test
    void testDistanceTo3D() {
        Point3D p1 = new Point3D(1, 2, 3);
        Point3D p2 = new Point3D(4, 6, 9);

        assertEquals(7.9, p1.distanceTo(p2), 0.01);
    }

    @Test
    void testDistanceTo2D() {
        Point3D p1 = new Point3D(1, 2, 0);
        Point p2 = new Point(4, 6);

        assertEquals(5.0, p1.distanceTo(p2), 0.01);
    }
}