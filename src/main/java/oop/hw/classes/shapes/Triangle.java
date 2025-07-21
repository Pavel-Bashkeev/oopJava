package oop.hw.classes.shapes;

public class Triangle extends AbstractShare {
    private final double a;
    private final double b;
    private final double c;

    public Triangle(double a, double b, double c) {
        super("Треугольник");
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException("Стороны должны быть положительными");
        }
        if (!isValidTriangle(a, b, c)) {
            throw new IllegalArgumentException("Такой треугольник не существует");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    private boolean isValidTriangle(double a, double b, double c) {
        return a + b > c && a + c > b && b + c > a;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    @Override
    public double getArea() {
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    @Override
    public String toString() {
        return super.toString() +
                ", стороны: " + a + ", " + b + ", " + c;
    }
}