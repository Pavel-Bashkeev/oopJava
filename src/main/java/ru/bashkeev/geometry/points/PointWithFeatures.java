package ru.bashkeev.geometry.points;

public class PointWithFeatures {
    private final Point point;
    private String color;
    private int size;
    private String borderColor;
    private String shape;

    public PointWithFeatures(Point point) {
        this.point = point;
    }

    public PointWithFeatures withColor(String color) {
        this.color = color;
        return this;
    }

    public PointWithFeatures withSize(int size) {
        this.size = size;
        return this;
    }

    public PointWithFeatures withBorder(String borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public PointWithFeatures withShape(String shape) {
        this.shape = shape;
        return this;
    }

    public Point getPoint() {
        return point;
    }

    public String getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public String getShape() {
        return shape;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(point.toString());
        if (color != null) sb.append(", цвет: ").append(color);
        if (size != 0) sb.append(", размер: ").append(size);
        if (borderColor != null) sb.append(", ободок: ").append(borderColor);
        if (shape != null) sb.append(", форма: ").append(shape);
        return sb.toString();
    }
}