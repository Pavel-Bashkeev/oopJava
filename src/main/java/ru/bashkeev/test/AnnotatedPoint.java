package ru.bashkeev.test;

import ru.bashkeev.annotation.*;
import ru.bashkeev.geometry.points.Point;
import ru.bashkeev.geometry.points.PointFactory;

@Two(first = "GeometryPoint", second = 2)
@Cache({"points", "geometry"})
@Validate({String.class, Integer.class, Double.class, Point.class})
public class AnnotatedPoint implements GeometryService {

    @Default(type = String.class)
    private String pointName;

    @ToString(value = ToString.Value.YES)
    private String description = "Annotated geometry point";

    @ToString(value = ToString.Value.NO)
    private  String internalId = "POINT_SECRET_123";

    private Point point;
    private int x;
    private int y;

    public AnnotatedPoint(int x, int y) {
        this.x = x;
        this.y = y;
        this.point = PointFactory.getInstance().createPoint(x, y);
    }

    public AnnotatedPoint() {
        this(0, 0);
    }

    @Invoke
    @Override
    public void initializePoint() {
        System.out.println("Point initialized at coordinates: (" + x + ", " + y + ")");
    }

    @Invoke
    @Override
    public String getPointInfo() {
        return "Point info: (" + x + ", " + y + "), name: " + pointName;
    }

    @Override
    public double distanceTo(AnnotatedPoint other) {
        return point.distanceTo(other.point);
    }

    public String getPointName() { return pointName; }
    public String getInternalId() { return internalId; }
    public String getDescription() { return description; }
    public int getX() { return x; }
    public int getY() { return y; }
    public Point getPoint() { return point; }

    @Override
    public String toString() {
        return "AnnotatedPoint{x=" + x + ", y=" + y + "}";
    }
}