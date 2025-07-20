package oop.hw.classes.chapterTwo;

import oop.hw.classes.shapes.Circle;
import oop.hw.classes.shapes.Rectangle;
import oop.hw.classes.shapes.Square;
import oop.hw.classes.shapes.Triangle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShapesTest {
    @Test
    void testCircle() {
        Circle circle = new Circle(5);
        assertEquals("Круг", circle.getName());
        assertEquals(5, circle.getRadius());
        assertEquals(Math.PI * 25, circle.getArea(), 0.001);
        assertThrows(IllegalArgumentException.class, () -> new Circle(-1));
    }

    @Test
    void testSquare() {
        Square square = new Square(4);
        assertEquals("Квадрат", square.getName());
        assertEquals(4, square.getSide());
        assertEquals(16, square.getArea(), 0.001);
        assertThrows(IllegalArgumentException.class, () -> new Square(0));
    }

    @Test
    void testRectangle() {
        Rectangle rectangle = new Rectangle(3, 6);
        assertEquals("Прямоугольник", rectangle.getName());
        assertEquals(3, rectangle.getWidth());
        assertEquals(6, rectangle.getHeight());
        assertEquals(18, rectangle.getArea(), 0.001);
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(0, 5));
    }

    @Test
    void testTriangle() {
        Triangle triangle = new Triangle(3, 4, 5);
        assertEquals("Треугольник", triangle.getName());
        assertEquals(3, triangle.getA());
        assertEquals(4, triangle.getB());
        assertEquals(5, triangle.getC());
        assertEquals(6, triangle.getArea(), 0.001);
        assertThrows(IllegalArgumentException.class, () -> new Triangle(1, 1, 3));
    }
}