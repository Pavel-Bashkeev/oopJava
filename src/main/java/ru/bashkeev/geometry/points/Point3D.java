package ru.bashkeev.geometry.points;

public final class Point3D extends Point {
    private int z;

    Point3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    Point3D() {
        this(0, 0, 0);
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "{" + getX() + ";" + getY() + ";" + z + "}";
    }

    @Override
    public int hashCode() {
        return super.hashCode() * 31 + z;
    }

    @Override
    public Point3D clone() {
        return (Point3D) super.clone();
    }

    @Override
    public double distanceTo(Point other) {
        double distance;

        if (other instanceof Point3D other3D) {
            double dx = other3D.getX() - this.getX();
            double dy = other3D.getY() - this.getY();
            double dz = other3D.z - this.z;
            distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
        } else {
            double dx = other.getX() - this.getX();
            double dy = other.getY() - this.getY();
            distance = Math.sqrt(dx * dx + dy * dy);
        }

        return Math.ceil(distance * 10) / 10;
    }
}
