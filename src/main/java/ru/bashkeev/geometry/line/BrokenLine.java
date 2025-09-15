package ru.bashkeev.geometry.line;

import ru.bashkeev.geometry.interfaces.PointIterator;
import ru.bashkeev.geometry.points.Point;
import ru.bashkeev.helpers.ArrayToString;
import ru.bashkeev.geometry.interfaces.Measurable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BrokenLine implements Measurable {
    private List<Point> points;

    public BrokenLine(List<Point> points) {
        this.points = new ArrayList<>(points);
    }

    public BrokenLine() {
        this(new ArrayList<>());
    }

    public PointIterator iterator() {
        return new BrokenLineIterator(this);
    }

    public PointIterator iterator(int startIndex) {
        return new BrokenLineIterator(this, startIndex);
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        BrokenLine other = (BrokenLine) obj;

        if (this.points.size() != other.points.size()) {
            int diff = Math.abs(this.points.size() - other.points.size());
            if (diff > 1) return false;
        }

        List<Point> thisNormalized = normalizeForComparison(this.points);
        List<Point> otherNormalized = normalizeForComparison(other.points);

        return thisNormalized.equals(otherNormalized);
    }

    @Override
    public int hashCode() {
        List<Point> normalized = normalizeForComparison(points);
        return Objects.hash(normalized);
    }

    protected List<Point> normalizeForComparison(List<Point> points) {
        if (points.isEmpty()) return new ArrayList<>();

        if (points.size() > 1 && points.getFirst().equals(points.getLast())) {
            return new ArrayList<>(points.subList(0, points.size() - 1));
        }

        return new ArrayList<>(points);
    }
}
