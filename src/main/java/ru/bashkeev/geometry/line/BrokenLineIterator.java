package ru.bashkeev.geometry.line;

import ru.bashkeev.geometry.interfaces.PointIterator;
import ru.bashkeev.geometry.points.Point;

import java.util.List;


public class BrokenLineIterator implements PointIterator {
    private final List<Point> points;
    private       int         currentIndex;

    public BrokenLineIterator(BrokenLine brokenLine) {
        this(brokenLine, 0);
    }

    public BrokenLineIterator(BrokenLine brokenLine, int startIndex) {
        this.points = brokenLine.getPoints();
        this.currentIndex = Math.max(0, Math.min(startIndex, points.size() - 1));
    }

    @Override
    public boolean hasNext() {
        return currentIndex < points.size() - 1;
    }

    @Override
    public Point nextPoint() {
        if (!hasNext()) {
            throw new IllegalStateException("Нет следующей точки");
        }
        currentIndex++;
        return points.get(currentIndex);
    }

    @Override
    public Point currentPoint() {
        if (points.isEmpty()) {
            throw new IllegalStateException("Линия не содержит точек");
        }
        return points.get(currentIndex);
    }

    @Override
    public void reset() {
        currentIndex = 0;
    }
}
