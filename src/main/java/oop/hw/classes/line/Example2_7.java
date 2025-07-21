package oop.hw.classes.line;

import oop.hw.classes.Square;
import oop.hw.classes.points.Point;
import oop.hw.interfaces.PolylineProvider;

public class Example2_7 {
    public static void demo() {
        Square square1 = new Square(new Point(0, 10), 5);
        Square square2 = new Square(new Point(5, 15), 3);

        PolylineProvider[] shapes = {square1, square2};

        BrokenLine mergedBrokenLine = BrokenLineMerger.mergeBrokenLines(shapes);
        System.out.println("Объединенная ломаная:");
        System.out.println(mergedBrokenLine);
        System.out.println("Общее количество точек: " + mergedBrokenLine.getPoints().size());
    }
}
