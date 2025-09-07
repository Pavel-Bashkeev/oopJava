package ru.bashkeev.geometry.line;

import ru.bashkeev.geometry.points.Point;
import ru.bashkeev.geometry.Square;
import ru.bashkeev.geometry.points.PointFactory;

public class Example2_6 {
    public static void demo() {
        Square square = new Square(PointFactory.getInstance().createPoint(0, 10), 5.0);
        BrokenLine closedBrokenLine = square.getBrokenLine();

        System.out.println(closedBrokenLine);
        System.out.println("Длина периметра: " + closedBrokenLine.getLength());
    }
}
