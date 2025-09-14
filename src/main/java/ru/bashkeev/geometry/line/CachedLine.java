package ru.bashkeev.geometry.line;

import ru.bashkeev.geometry.points.Point;
import ru.bashkeev.utils.MyCache;

public class CachedLine<T extends Point> {
    private final Line<T> line;
    private final MyCache<Double> cache;
    private int calculationCount = 0;

    public CachedLine(Line<T> line) {
        this.line = line;
        this.cache = MyCache.getInstance();
    }

    public CachedLine(T startPoint, T endPoint) {
        this(new Line<>(startPoint, endPoint));
    }

    public double getLength() {
        return cache.get(line, () -> {
            calculationCount++;
            System.out.println("Вычисление длины для линии: " + line);
            return line.getLength();
        });
    }
    public int getCalculationCount() {
        return calculationCount;
    }

    public void invalidateCache() {
        cache.invalidate(line);
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