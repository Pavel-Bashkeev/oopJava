package oop.hw.classes.line;

import oop.hw.classes.points.Point;
import oop.hw.classes.Square;

public class Example2_6 {
    public static void demo() {
        Square square = new Square(new Point(0, 10), 5.0);
        BrokenLine closedBrokenLine = square.getBrokenLine();

        System.out.println(closedBrokenLine);
        System.out.println("Длина периметра: " + closedBrokenLine.getLength());
    }
}
