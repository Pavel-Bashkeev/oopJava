package ru.bashkeev.geometry.interfaces;


import ru.bashkeev.geometry.points.Point;

public interface PointIterator {
    boolean hasNext();
    Point nextPoint();
    Point currentPoint();
    void reset();
}
