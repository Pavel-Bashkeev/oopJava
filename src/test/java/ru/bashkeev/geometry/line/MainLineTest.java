package ru.bashkeev.geometry.line;

import org.junit.jupiter.api.Test;
import ru.bashkeev.geometry.points.Point;
import ru.bashkeev.geometry.points.Point3D;
import static org.junit.jupiter.api.Assertions.*;

public class MainLineTest {

    @Test
    public void test3DLineCreation() {
        Point3D start = new Point3D(1, 2, 3);
        Point3D end = new Point3D(4, 5, 6);
        Line<Point3D> line = new Line<>(start, end);

        assertEquals(start, line.getStartPoint());
        assertEquals(end, line.getEndPoint());
    }

    @Test
    public void test3DLineLength() {
        Point3D start = new Point3D(0, 0, 0);
        Point3D end = new Point3D(3, 4, 0);
        Line<Point3D> line = new Line<>(start, end);

        assertEquals(5.0, line.getLength(), 0.001); // 3-4-5 triangle
    }

    @Test
    public void test3DLineClone() {
        Point3D start = new Point3D(1, 2, 3);
        Point3D end = new Point3D(4, 5, 6);
        Line<Point3D> original = new Line<>(start, end);
        Line<Point3D> cloned = original.clone();

        assertEquals(original, cloned);
        assertNotSame(original.getStartPoint(), cloned.getStartPoint());
    }

    @Test
    public void testTypeSafety() {
        Point3D start = new Point3D(1, 2, 3);
        Point3D end = new Point3D(4, 5, 6);
        Line<Point3D> line = new Line<>(start, end);

        Point3D retrievedStart = line.getStartPoint();
        Point3D retrievedEnd = line.getEndPoint();

        assertEquals(1, retrievedStart.getX());
        assertEquals(3, retrievedStart.getZ());
    }
}