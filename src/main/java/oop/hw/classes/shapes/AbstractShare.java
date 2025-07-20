package oop.hw.classes.shapes;


public abstract class AbstractShare {
    private final String name;

    public AbstractShare(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double getArea();

    @Override
    public String toString() {
        return name + " (площадь: " + getArea() + ")";
    }
}
