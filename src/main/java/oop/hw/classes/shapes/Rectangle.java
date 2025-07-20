package oop.hw.classes.shapes;

public class Rectangle extends AbstractShare {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        super("Прямоугольник");
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Стороны должны быть положительными");
        }
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", ширина: " + width +
                ", высота: " + height;
    }
}