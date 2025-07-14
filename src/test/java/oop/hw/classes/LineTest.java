package oop.hw.classes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LineTest {
    @Test
    void testLineCreation() {
        Point p1 = new Point(1, 3);
        Point p2 = new Point(23, 8);
        Line line = new Line(p1, p2);

        assertEquals("Линия от {1;3} до {23;8}", line.toString());
    }

    @Test
    void testHorizontalLine() {
        Point p1 = new Point(5, 10);
        Point p2 = new Point(25, 10);
        Line line = new Line(p1, p2);

        assertEquals("Линия от {5;10} до {25;10}", line.toString());
    }

    @Test
    void testDependentLine() {
        Line line1 = new Line(new Point(1, 3), new Point(23, 8));
        Line line2 = new Line(new Point(5, 10), new Point(25, 10));

        Line line3 = new Line(line1, line2);
        assertEquals("Линия от {1;3} до {25;10}", line3.toString());

        line1.setStartPointDependent(new Point(2, 4));
        assertEquals("Линия от {2;4} до {25;10}", line3.toString());

        line2.setEndPointDependent(new Point(30, 10));
        assertEquals("Линия от {2;4} до {30;10}", line3.toString());
    }

    @Test
    void testIndependentChange() {
        Line line1 = new Line(new Point(1, 3), new Point(23, 8));
        Line line2 = new Line(new Point(5, 10), new Point(25, 10));
        Line line3 = new Line(line1, line2);

        line1.setStartPoint(new Point(10, 20));
        line2.setEndPoint(new Point(10, 20));

        assertEquals("Линия от {1;3} до {25;10}", line3.toString());
        assertEquals("Линия от {10;20} до {23;8}", line1.toString());
        assertEquals("Линия от {5;10} до {10;20}", line2.toString());
    }

    @Test
    void testLengthCalculation() {
        Line line = new Line(new Point(1, 1), new Point(10, 15));
        assertEquals(16.7, line.getLength());
    }

}