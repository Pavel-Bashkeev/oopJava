package oop.hw.classes.shapes;

public class Circle extends AbstractShare {
    private final double radius;

    public Circle(double radius) {
        super("Круг");
        if (radius <= 0) {
            throw new IllegalArgumentException("Радиус должен быть положительным");
        }
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return super.toString() + ", радиус: " + radius;
    }
}