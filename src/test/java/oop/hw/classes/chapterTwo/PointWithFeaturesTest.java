package oop.hw.classes.chapterTwo;

import ru.bashkeev.geometry.points.Point;
import ru.bashkeev.geometry.points.Point3D;
import ru.bashkeev.geometry.points.PointWithFeatures;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PointWithFeaturesTest {
    @Test
    void testEmptyFeatures() {
        Point point = new Point(3, 4);
        PointWithFeatures pwf = new PointWithFeatures(point);

        assertEquals(point, pwf.getPoint());
        assertNull(pwf.getColor());
        assertNull(pwf.getBorderColor());
        assertNull(pwf.getShape());
        assertEquals("{3;4}", pwf.toString());
    }

    @Test
    void testWithColor() {
        Point point = new Point(1, 2);
        PointWithFeatures pwf = new PointWithFeatures(point)
                .withColor("красный");

        assertEquals("красный", pwf.getColor());
        assertEquals("{1;2}, цвет: красный", pwf.toString());
    }

    @Test
    void testWithSize() {
        Point3D point = new Point3D(5, 5, 5);
        PointWithFeatures pwf = new PointWithFeatures(point)
                .withSize(10);

        assertEquals(10, pwf.getSize());
        assertEquals("{5;5;5}, размер: 10", pwf.toString());
    }

    @Test
    void testWithBorder() {
        Point point = new Point(0, 0);
        PointWithFeatures pwf = new PointWithFeatures(point)
                .withBorder("синий");

        assertEquals("синий", pwf.getBorderColor());
        assertEquals("{0;0}, ободок: синий", pwf.toString());
    }

    @Test
    void testWithShape() {
        Point3D point = new Point3D(7, 8, 9);
        PointWithFeatures pwf = new PointWithFeatures(point)
                .withShape("котенок");

        assertEquals("котенок", pwf.getShape());
        assertEquals("{7;8;9}, форма: котенок", pwf.toString());
    }

    @Test
    void testAllFeatures() {
        Point point = new Point(10, 20);
        PointWithFeatures pwf = new PointWithFeatures(point)
                .withColor("зеленый")
                .withSize(15)
                .withBorder("черный")
                .withShape("квадрат");

        assertEquals("зеленый", pwf.getColor());
        assertEquals(15, pwf.getSize());
        assertEquals("черный", pwf.getBorderColor());
        assertEquals("квадрат", pwf.getShape());
        assertEquals("{10;20}, цвет: зеленый, размер: 15, ободок: черный, форма: квадрат",
                pwf.toString());
    }

    @Test
    void testFluentInterface() {
        Point3D point = new Point3D(1, 1, 1);
        PointWithFeatures pwf = new PointWithFeatures(point)
                .withColor("желтый")
                .withSize(5)
                .withShape("круг");

        // Проверяем, что возвращается тот же объект
        assertSame(pwf, pwf.withColor("красный"));
        assertSame(pwf, pwf.withSize(10));
        assertSame(pwf, pwf.withShape("треугольник"));
    }

    @Test
    void testNullValues() {
        Point point = new Point(3, 3);
        PointWithFeatures pwf = new PointWithFeatures(point)
                .withColor(null)
                .withBorder(null)
                .withShape(null);

        assertNull(pwf.getColor());
        assertNull(pwf.getBorderColor());
        assertNull(pwf.getShape());
        assertEquals("{3;3}", pwf.toString());
    }
}