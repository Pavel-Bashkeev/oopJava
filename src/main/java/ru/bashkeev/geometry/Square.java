package ru.bashkeev.geometry;

import ru.bashkeev.geometry.line.BrokenLine;
import ru.bashkeev.geometry.points.Point;
import ru.bashkeev.geometry.interfaces.PolylineProvider;
import ru.bashkeev.geometry.points.PointFactory;

import java.util.List;

public class Square implements PolylineProvider {
    private Point startPointTopLeft;
    private double lengthOfSide;

    public Square(Point startPointTopLeft, double lengthOfSide) {
        validateLength(lengthOfSide);
        this.startPointTopLeft = PointFactory.getInstance().createPoint(startPointTopLeft.getX(), startPointTopLeft.getY());
        this.lengthOfSide = lengthOfSide;
    }

    public Square(Point startPointTopLeft, int lengthOfSide) {
        this(startPointTopLeft, (double) lengthOfSide);
    }

    public Square(int x, int y, double lengthOfSide) {
        this(PointFactory.getInstance().createPoint(x, y), lengthOfSide);
    }

    public Square(int x, int y, int lengthOfSide) {
        this(PointFactory.getInstance().createPoint(x, y), (double) lengthOfSide);
    }

    private void validateLength(double length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Длина стороны должна быть положительной");
        }
    }

    public void setSideLength(double sideLength) {
        validateLength(sideLength);
        this.lengthOfSide = sideLength;
    }

    public void setSideLength(int sideLength) {
        setSideLength((double) sideLength);
    }

    public void setTopLeft(Point point) {
        this.startPointTopLeft = PointFactory.getInstance().createPoint(point.getX(), point.getY());
    }

    public void setTopLeft(int x, int y) {
        this.startPointTopLeft = PointFactory.getInstance().createPoint(x, y);
    }

    public double getSideLength() {
        return lengthOfSide;
    }

    public Point getTopLeft() {
        return PointFactory.getInstance().createPoint(startPointTopLeft.getX(), startPointTopLeft.getY());
    }

    @Override
    public String toString() {
        return "Квадрат в точке " + startPointTopLeft + " со стороной " + lengthOfSide;
    }

    public BrokenLine getBrokenLine() {
        double x = startPointTopLeft.getX();
        double y = startPointTopLeft.getY();
        double side = lengthOfSide;

        double offsetX = x + side;
        double offsetY = y - side;

        Point topLeft = PointFactory.getInstance().createPoint((int)Math.round(x), (int)Math.round(y));
        Point topRight = PointFactory.getInstance().createPoint((int)Math.round(offsetX), (int)Math.round(y));
        Point bottomRight = PointFactory.getInstance().createPoint((int)Math.round(offsetX), (int)Math.round(offsetY));
        Point bottomLeft = PointFactory.getInstance().createPoint((int)Math.round(x), (int)Math.round(offsetY));

        return new BrokenLine(List.of(
                topLeft,
                topRight,
                bottomRight,
                bottomLeft,
                topLeft
        ));
    }
}
