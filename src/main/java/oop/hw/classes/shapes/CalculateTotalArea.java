package oop.hw.classes.shapes;

public class CalculateTotalArea {
    public static double calculateTotalArea(AbstractShare[] shapes) {
        double totalArea = 0;
        for (AbstractShare shape : shapes) {
            totalArea += shape.getArea();
        }
        return totalArea;
    }
}
