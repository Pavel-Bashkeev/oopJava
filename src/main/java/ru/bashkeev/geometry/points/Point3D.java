package ru.bashkeev.geometry.points;

public final class Point3D extends Point {
    private int z;

    public Point3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    public Point3D() {
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

    public double distanceTo(Point3D pointTo) {
        double dx = pointTo.getX() - this.getX();
        double dy = pointTo.getY() - this.getY();
        double dz = pointTo.z - this.z;
        double distance = Math.sqrt(dx*dx + dy*dy + dz*dz);

        return Math.ceil(distance * 10) / 10;
    }

    @Override
    public double distanceTo(Point pointTo) {
        if (pointTo instanceof Point3D) {
            return distanceTo((Point3D) pointTo);
        }
        return super.distanceTo(pointTo);
    }
}
