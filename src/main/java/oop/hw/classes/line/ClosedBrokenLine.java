package oop.hw.classes.line;

import oop.hw.classes.points.Point;

import java.util.List;

public class ClosedBrokenLine extends BrokenLine {

    public ClosedBrokenLine(List<Point> points) {
        super(points);
        validateClosed(points);
    }

    private void validateClosed(List<Point> points) {
        if (points.size() < 3) {
            throw new IllegalArgumentException("Замкнутая ломаная должна содержать минимум 3 точки");
        }

        if (!points.getFirst().equals(points.getLast())) {
            throw new IllegalArgumentException("Замкнутая ломаная должна начинаться и заканчиваться в одной точке");
        }
    }

    @Override
    public void setPoints(List<Point> points) {
        validateClosed(points);
        super.setPoints(points);
    }

    @Override
    public double getLength() {
        List<Point> points = getPoints();
        if (points.size() < 2) {
            return 0.0;
        }

        double length = super.getLength();

        if (points.size() > 2) {
            Point first = points.getFirst();
            Point last = points.getLast();
            length += last.distanceTo(first);
        }

        return length;
    }

    @Override
    public String toString() {
        return "Замкнутая " + super.toString();
    }
}
