package oop.hw.classes;

import oop.hw.helpers.ArrayToString;

import java.util.ArrayList;
import java.util.List;

public class BrokenLine {
    private List<Point> points;

    public BrokenLine(List<Point> points) {
        this.points = new ArrayList<>(points);
    }

    public BrokenLine() {
        this(new ArrayList<>());
    }

    @Override
    public String toString() {
        return "Линия " + ArrayToString.pointListToString(points);
    }

    public List<Point> getPoints() {
        return new ArrayList<>(points);
    }

    public void setPoints(List<Point> points) {
        this.points = new ArrayList<>(points);
    }

    public void addPoint(Point point) {
        this.points.add(point);
    }

    public void addPoints(List<Point> points) {
        this.points.addAll(points);
    }

    public double getLength() {
        if (points.size() < 2) {
            return 0.0;
        }

        double length = 0.0;

        for (int i = 0; i < points.size() - 1; i++) {
            Point current = points.get(i);
            Point next    = points.get(i + 1);
            length += current.distanceTo(next);
        }

        return length;
    }
}
