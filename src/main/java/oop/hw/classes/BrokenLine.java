package oop.hw.classes;

import oop.hw.helpers.ArrayToString;

import java.util.ArrayList;
import java.util.List;

public class BrokenLine {
    List<Point> points;

    public BrokenLine(List<Point> points) {
        this.points = points;
    }

    public BrokenLine() {
        this.points = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Линия " + ArrayToString.pointListToString(points);
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public void addPoint(Point point) {
        this.points.add(point);
    }

    public void addPoints(List<Point> points) {
        this.points.addAll(points);
    }
}
