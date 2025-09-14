package ru.bashkeev.geometry.line;

import ru.bashkeev.geometry.points.Point;

public class CachedLine<T extends Point> {
    private final Line<T> line;
    private Double cachedLength;
    private int calculationCount = 0;

    public CachedLine(Line<T> line) {
        this.line = line;
    }

    public CachedLine(T startPoint, T endPoint) {
        this.line = new Line<>(startPoint, endPoint);
    }

    public double getLength() {
        if (cachedLength == null) {
            calculationCount++;
            cachedLength = line.getLength();
        }
        return cachedLength;
    }
    public int getCalculationCount() {
        return calculationCount;
    }

    public void invalidateCache() {
        cachedLength = null;
    }

    public T getStartPoint() {
        return line.getStartPoint();
    }

    public T getEndPoint() {
        return line.getEndPoint();
    }

    public void setStartPoint(T startPoint) {
        line.setStartPoint(startPoint);
        invalidateCache();
    }

    public void setEndPoint(T endPoint) {
        line.setEndPoint(endPoint);
        invalidateCache();
    }

    public Line<T> getLine() {
        return line;
    }
}