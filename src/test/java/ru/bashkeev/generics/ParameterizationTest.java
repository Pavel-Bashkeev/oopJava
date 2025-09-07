package ru.bashkeev.generics;

import org.junit.jupiter.api.Test;
import ru.bashkeev.geometry.line.Line;
import ru.bashkeev.geometry.points.Point;
import ru.bashkeev.geometry.points.Point3D;
import ru.bashkeev.geometry.points.PointFactory;
import ru.bashkeev.utils.BoxUtils;
import ru.bashkeev.utils.BoxUtils3D;
import ru.bashkeev.utils.LineUtils;
import ru.bashkeev.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ParameterizationTest {

    @Test
    public void testShiftLineStartX() {
        Line<Point> line = new Line<>(PointFactory.getInstance().createPoint(5, 10), PointFactory.getInstance().createPoint(15, 20));
        LineUtils.shiftLineStartX(line);

        assertEquals(15, line.getStartPoint().getX());
        assertEquals(10, line.getStartPoint().getY());
    }

    @Test
    public void testFindMaxValue() {
        List<Box<? extends Number>> boxes = new ArrayList<>();

        Box<Integer> intBox = new Box<>();
        intBox.put(10);

        Box<Double> doubleBox = new Box<>();
        doubleBox.put(3.14);

        Box<Long> longBox = new Box<>();
        longBox.put(100L);

        boxes.add(intBox);
        boxes.add(doubleBox);
        boxes.add(longBox);

        double max = BoxUtils.findMaxValue(boxes);
        assertEquals(100.0, max, 0.001);
    }

    @Test
    public void testPutPoint3D() {
        Box<Point3D> box = new Box<>();
        BoxUtils3D.putPoint3D(box);

        Point3D point = box.get();

        assertNotNull(point);
        assertEquals(5, point.getX());
        assertEquals(10, point.getY());
        assertEquals(15, point.getZ());

        assertNull(box.get());
        assertTrue(box.isEmpty());
    }

    @Test
    public void testFillWithNumbers() {
        List<Integer> list = new ArrayList<>();
        ListUtils.fillWithNumbers(list);

        assertEquals(100, list.size());
        assertEquals(1, list.get(0));
        assertEquals(100, list.get(99));
    }
}