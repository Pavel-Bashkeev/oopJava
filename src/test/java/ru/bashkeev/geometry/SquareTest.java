package ru.bashkeev.geometry;

import ru.bashkeev.geometry.line.BrokenLine;
import ru.bashkeev.geometry.points.Point;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest {

    @Test
    void testSquareCreationAndBrokenLine() {
        Square square = new Square(new Point(5, 3), 23);

        assertEquals("{5;3}", square.getTopLeft().toString());
        assertEquals(23.0, square.getSideLength(), 0.001);

        BrokenLine brokenLine = square.getBrokenLine();
        assertNotNull(brokenLine);

        List<Point> points = brokenLine.getPoints();
        assertEquals(5, points.size());

        assertEquals("{5;3}", points.get(0).toString());
        assertEquals("{28;3}", points.get(1).toString());
        assertEquals("{28;-20}", points.get(2).toString());
        assertEquals("{5;-20}", points.get(3).toString());
        assertEquals("{5;3}", points.get(4).toString());

        double expectedLength = 23 * 4;
        assertEquals(expectedLength, brokenLine.getLength(), 0.001);
    }

    @Test
    void testSquareToString() {
        Square square = new Square(new Point(5, 3), 23);
        assertEquals("Квадрат в точке {5;3} со стороной 23.0", square.toString());
    }

    @Test
    void testSetSideLength() {
        Square square = new Square(0, 0, 10);
        square.setSideLength(15.5);
        assertEquals(15.5, square.getSideLength(), 0.001);
    }

    @Test
    void testSetTopLeft() {
        Square square = new Square(0, 0, 10);
        square.setTopLeft(7, 8);
        assertEquals("{7;8}", square.getTopLeft().toString());
    }

    @Test
    void testInvalidSideLengthInConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new Square(0, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> new Square(0, 0, -5));
    }

    @Test
    void testInvalidSideLengthInSetter() {
        Square square = new Square(0, 0, 10);
        assertThrows(IllegalArgumentException.class, () -> square.setSideLength(0));
        assertThrows(IllegalArgumentException.class, () -> square.setSideLength(-3));
    }
}
