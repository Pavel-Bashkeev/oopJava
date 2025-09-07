package ru.bashkeev.geometry.line;

import ru.bashkeev.geometry.points.Point;
import org.junit.jupiter.api.Test;
import ru.bashkeev.geometry.points.PointFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LineTest {
    @Test
    void testLineCreation() {
        Point p1 = PointFactory.getInstance().createPoint(1, 3);
        Point p2 = PointFactory.getInstance().createPoint(23, 8);
        Line<Point> line = new Line<>(p1, p2);

        assertEquals("Линия от {1;3} до {23;8}", line.toString());
    }

    @Test
    void testHorizontalLine() {
        Point p1 = PointFactory.getInstance().createPoint(5, 10);
        Point p2 = PointFactory.getInstance().createPoint(25, 10);
        Line<Point> line = new Line<>(p1, p2);

        assertEquals("Линия от {5;10} до {25;10}", line.toString());
    }

    @Test
    void testDependentLine() {
        Line<Point>line1 = new Line<>(PointFactory.getInstance().createPoint(1, 3), PointFactory.getInstance().createPoint(23, 8));
        Line<Point>line2 = new Line<>(PointFactory.getInstance().createPoint(5, 10), PointFactory.getInstance().createPoint(25, 10));

        Line<Point>line3 = new Line<>(line1, line2);
        assertEquals("Линия от {1;3} до {25;10}", line3.toString());

        line1.setStartPointDependent(PointFactory.getInstance().createPoint(2, 4));
        assertEquals("Линия от {2;4} до {25;10}", line3.toString());

        line2.setEndPointDependent(PointFactory.getInstance().createPoint(30, 10));
        assertEquals("Линия от {2;4} до {30;10}", line3.toString());
    }

    @Test
    void testIndependentChange() {
        Line<Point>line1 = new Line<>(PointFactory.getInstance().createPoint(1, 3), PointFactory.getInstance().createPoint(23, 8));
        Line<Point>line2 = new Line<>(PointFactory.getInstance().createPoint(5, 10), PointFactory.getInstance().createPoint(25, 10));
        Line<Point>line3 = new Line<>(line1, line2);

        line1.setStartPoint(PointFactory.getInstance().createPoint(10, 20));
        line2.setEndPoint(PointFactory.getInstance().createPoint(10, 20));

        assertEquals("Линия от {1;3} до {25;10}", line3.toString());
        assertEquals("Линия от {10;20} до {23;8}", line1.toString());
        assertEquals("Линия от {5;10} до {10;20}", line2.toString());
    }

    @Test
    void testLengthCalculation() {
        Line<Point>line = new Line<>(PointFactory.getInstance().createPoint(1, 1), PointFactory.getInstance().createPoint(10, 15));
        assertEquals(16.7, line.getLength());
    }

}