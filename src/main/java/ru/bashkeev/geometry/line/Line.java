package ru.bashkeev.geometry.line;

import ru.bashkeev.geometry.points.Point;
import ru.bashkeev.geometry.interfaces.Measurable;

public class Line<T extends Point> implements Measurable, Cloneable {
    private T startPoint;
    private T endPoint;

    public Line(T startPoint, T endPoint) {
        this.startPoint = clonePoint(startPoint);
        this.endPoint = clonePoint(endPoint);
    }

    public Line(Line<T> startLine, Line<T> endLine) {
        this.startPoint = startLine.getOriginalStartPoint();
        this.endPoint = endLine.getOriginalEndPoint();
    }

    @Override
    public String toString() {
        return String.format("Линия от %s до %s", this.startPoint, this.endPoint);
    }

    public T getOriginalStartPoint() {
        return startPoint;
    }

    public T getOriginalEndPoint() {
        return endPoint;
    }

    public T getStartPoint() {
        return clonePoint(startPoint);
    }

    public T getEndPoint() {
        return clonePoint(endPoint);
    }

    public void setStartPoint(T startPoint) {
        this.startPoint = clonePoint(startPoint);
    }

    public void setEndPoint(T endPoint) {
        this.endPoint = clonePoint(endPoint);
    }

    public void setStartPointDependent(T startPoint) {
        this.startPoint.setX(startPoint.getX());
        this.startPoint.setY(startPoint.getY());
    }

    public void setEndPointDependent(T endPoint) {
        this.endPoint.setX(endPoint.getX());
        this.endPoint.setY(endPoint.getY());
    }

    public double getLength() {
        return this.startPoint.distanceTo(this.endPoint);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        @SuppressWarnings("unchecked")
        Line<T> other = (Line<T>) obj;

        return (startPoint.equals(other.startPoint) && endPoint.equals(other.endPoint)) ||
                (startPoint.equals(other.endPoint) && endPoint.equals(other.startPoint));
    }

    @Override
    public int hashCode() {
        return startPoint.hashCode() ^ endPoint.hashCode();
    }

    @Override
    public Line<T> clone() {
        try {
            @SuppressWarnings("unchecked")
            Line<T> cloned = (Line<T>) super.clone();
            cloned.startPoint = this.getStartPoint();
            cloned.endPoint = this.getEndPoint();
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Line cloning failed", e);
        }
    }

    @SuppressWarnings("unchecked")
    private T clonePoint(T point) {
        if (point == null) {
            return null;
        }
        return (T) point.clone();
    }
}