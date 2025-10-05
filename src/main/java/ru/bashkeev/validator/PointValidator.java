package ru.bashkeev.validator;

import ru.bashkeev.geometry.points.Point;
import ru.bashkeev.geometry.points.Point3D;
import ru.bashkeev.geometry.points.PointFactory;

public class PointValidator {
    public static void testPointCoordinates(Object entity) {
        if (entity instanceof Point point) {
            int x = point.getX();
            int y = point.getY();

            if (x < -10000 || x > 10000) {
                throw new RuntimeException("Координата X должна быть в диапазоне [-10000, 10000]: " + x);
            }
            if (y < -10000 || y > 10000) {
                throw new RuntimeException("Координата Y должна быть в диапазоне [-10000, 10000]: " + y);
            }

            if (x == 0 && y == 0) {
                throw new RuntimeException("Точка не должна находиться в начале координат");
            }
        }
    }

    public static void testPointFactory(Object entity) {
        if (entity instanceof PointFactory factory) {

            Point point2D = factory.createPoint(5, 10);
            if (point2D.getX() != 5 || point2D.getY() != 10) {
                throw new RuntimeException("Фабрика некорректно создает 2D точку");
            }

            Point3D point3D = factory.createPoint(1, 2, 3);
            if (point3D.getX() != 1 || point3D.getY() != 2) {
                throw new RuntimeException("Фабрика некорректно создает 3D точку");
            }
        }
    }

    public static void testPointDistance(Object entity) {
        if (entity instanceof Point point1) {

            Point point2 = PointFactory.getInstance().createPoint(point1.getX() + 3, point1.getY() + 4);

            double distance = point1.distanceTo(point2);

            if (Math.abs(distance - 5.0) > 0.1) {
                throw new RuntimeException("Некорректное вычисление расстояния: " + distance + " вместо 5.0");
            }

            double selfDistance = point1.distanceTo(point1);
            if (selfDistance != 0.0) {
                throw new RuntimeException("Расстояние точки до самой себя должно быть 0: " + selfDistance);
            }
        }
    }
}