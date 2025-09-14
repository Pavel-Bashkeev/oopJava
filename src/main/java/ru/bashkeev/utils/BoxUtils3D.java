package ru.bashkeev.utils;

import ru.bashkeev.generics.Box;
import ru.bashkeev.geometry.points.Point3D;
import ru.bashkeev.geometry.points.PointFactory;

public class BoxUtils3D {

    public static void putPoint3D(Box<Point3D> box) {
        Point3D point = PointFactory.getInstance().createPoint(5, 10, 15);
        box.put(point);
    }
}