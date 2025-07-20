package oop.hw.classes;


sealed public class Point permits Point3D {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
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
}
