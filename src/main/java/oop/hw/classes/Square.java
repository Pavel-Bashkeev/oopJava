package oop.hw.classes;

import java.util.List;

public class Square {
    private Point startPointTopLeft;
    private double lengthOfSide;

    public Square(Point startPointTopLeft, double lengthOfSide) {
        validateLength(lengthOfSide);
        this.startPointTopLeft = new Point(startPointTopLeft.getX(), startPointTopLeft.getY());
        this.lengthOfSide = lengthOfSide;
    }

    public Square(Point startPointTopLeft, int lengthOfSide) {
        this(startPointTopLeft, (double) lengthOfSide);
    }

    public Square(int x, int y, double lengthOfSide) {
        this(new Point(x, y), lengthOfSide);
    }

    public Square(int x, int y, int lengthOfSide) {
        this(new Point(x, y), (double) lengthOfSide);
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
        this.startPointTopLeft = new Point(point.getX(), point.getY());
    }

    public void setTopLeft(int x, int y) {
        this.startPointTopLeft = new Point(x, y);
    }

    public double getSideLength() {
        return lengthOfSide;
    }

    public Point getTopLeft() {
        return new Point(startPointTopLeft.getX(), startPointTopLeft.getY());
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

        Point topLeft = new Point((int)Math.round(x), (int)Math.round(y));
        Point topRight = new Point((int)Math.round(offsetX), (int)Math.round(y));
        Point bottomRight = new Point((int)Math.round(offsetX), (int)Math.round(offsetY));
        Point bottomLeft = new Point((int)Math.round(x), (int)Math.round(offsetY));

        return new BrokenLine(List.of(
                topLeft,
                topRight,
                bottomRight,
                bottomLeft,
                topLeft
        ));
    }
}
