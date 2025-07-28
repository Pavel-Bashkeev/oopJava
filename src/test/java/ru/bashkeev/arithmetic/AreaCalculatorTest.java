package ru.bashkeev.arithmetic;

import ru.bashkeev.geometry.shapes.AbstractShare;
import ru.bashkeev.geometry.shapes.CalculateTotalArea;
import ru.bashkeev.geometry.shapes.Circle;
import ru.bashkeev.geometry.shapes.Square;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AreaCalculatorTest {
    @Test
    void testCalculateTotalArea() {
        AbstractShare[] shapes = {
                new Circle(1),       // Площадь ≈ 3.14159
                new Square(2),   // Площадь = 4
                new Circle(2)        // Площадь ≈ 12.56637
        };

        double expected = 3.14159 + 4 + 12.56637;
        double actual = CalculateTotalArea.calculateTotalArea(shapes);
        assertEquals(expected, actual, 0.0001); // Допустимая погрешность
    }

    @Test
    void testEmptyArray() {
        AbstractShare[] shapes = new AbstractShare[0];
        assertEquals(0, CalculateTotalArea.calculateTotalArea(shapes));
    }
}