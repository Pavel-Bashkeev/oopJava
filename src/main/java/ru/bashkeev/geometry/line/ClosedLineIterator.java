package ru.bashkeev.geometry.line;

import ru.bashkeev.geometry.interfaces.PointIterator;
import ru.bashkeev.geometry.points.Point;

import java.util.List;

public class ClosedLineIterator implements PointIterator {
    private final List<Point> points;
    private       int         currentIndex;

    public ClosedLineIterator(ClosedBrokenLine closedLine) {
        this(closedLine, 0);
    }

    public ClosedLineIterator(ClosedBrokenLine closedLine, int startIndex) {
        this.points       = closedLine.getPoints();
        this.currentIndex = startIndex % points.size();
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Point nextPoint() {
        if (points.isEmpty()) {
            throw new IllegalStateException("Линия не содержит точек");
        }

        currentIndex = (currentIndex + 1) % points.size();

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
