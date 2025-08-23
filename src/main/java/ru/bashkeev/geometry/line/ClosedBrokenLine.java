package ru.bashkeev.geometry.line;

import ru.bashkeev.geometry.points.Point;

import java.util.List;

public class ClosedBrokenLine extends BrokenLine {

    public ClosedBrokenLine(List<Point> points) {
        super(validateAndClosed(points));
    }

    private static List<Point> validateAndClosed(List<Point> points) {
        if (points.size() < 3) {
            throw new IllegalArgumentException("Замкнутая ломаная должна содержать минимум 3 точки");
        }

        Point first = points.getFirst();
        Point last = points.getLast();

        if (!first.equals(last)) {
            points.add(first.clone());
        }

        return points;
    }

    @Override
    public void setPoints(List<Point> points) {
        super.setPoints(validateAndClosed(points));
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
