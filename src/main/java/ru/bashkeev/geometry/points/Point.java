package ru.bashkeev.geometry.points;


import java.util.Objects;

sealed public class Point implements Cloneable permits Point3D {
    private int x;
    private int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Point() {
        this(0,0);
    }

    @Override
    public String toString() {
        return "{" + x + ";" + y + "}";
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public  double distanceTo(Point pointTo) {
        double dx = pointTo.x  - this.x;
        double dy = pointTo.y - this.y;
        double distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

        return Math.ceil(distance * 10) / 10;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Point other)) {
            return false;
        }
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public Point clone() {
        try {
            return (Point) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Point cloning failed", e);
        }
    }
}
