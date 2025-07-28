package ru.bashkeev.geometry.shapes;

public class Square extends Rectangle {
    public Square(double side) {
        super(side, side);
    }

    public double getSide() {
        return getWidth(); // или getHeight(), так как они равны
    }

    @Override
    public String getName() {
        return "Квадрат";
    }

    @Override
    public String toString() {
        return super.getName() + " (площадь: " + getArea() +
                "), сторона: " + getSide();
    }
}