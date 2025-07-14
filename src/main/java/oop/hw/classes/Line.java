
package oop.hw.classes;

public class Line {
    private Point startPoint;
    private Point endPoint;


    public Line (Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Line (Line startLine, Line endLine) {
        this(startLine.getStartPoint(),  endLine.getEndPoint());
    }

    @Override
    public String toString() {
        return String.format("Линия от %s до %s", this.startPoint.toString(), this.endPoint.toString());
    }

    public Point getStartPoint() {
        return new Point(this.startPoint.getX(), this.startPoint.getY());
    }

    public Point getEndPoint() {
        return new  Point(this.endPoint.getX(), this.endPoint.getY());
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = new Point(startPoint.getX(), startPoint.getY());
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = new Point(endPoint.getX(), endPoint.getY());
    }

    public void setStartPointDependent(Point startPoint) {
        this.startPoint.setX(startPoint.getX());
        this.startPoint.setY(startPoint.getY());
    }

    public void setEndPointDependent(Point endPoint) {
        this.endPoint.setX(endPoint.getX());
        this.endPoint.setY(endPoint.getY());
    }

    public double getLength () {
        return this.startPoint.distanceTo(this.endPoint);
    }
}
