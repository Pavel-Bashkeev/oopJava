package ru.bashkeev.geometry.points;

public class PointFactory {
    private static PointFactory instance;

    private PointFactory() {}

    public static PointFactory getInstance() {
        if (instance == null) {
            instance = new PointFactory();
        }
        return instance;
    }

    public Point createPoint(int x, int y) {
        return new Point(x, y);
    }

    public Point3D createPoint(int x, int y, int z) {
        return new Point3D(x, y, z);
    }
}
