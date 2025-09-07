package ru.bashkeev.geometry.line;

import ru.bashkeev.geometry.Square;
import ru.bashkeev.geometry.points.Point;
import ru.bashkeev.geometry.interfaces.PolylineProvider;
import ru.bashkeev.geometry.points.PointFactory;

public class Example2_7 {
    public static void demo() {
        Square square1 = new Square(PointFactory.getInstance().createPoint(0, 10), 5);
        Square square2 = new Square(PointFactory.getInstance().createPoint(5, 15), 3);

        PolylineProvider[] shapes = {square1, square2};

        BrokenLine mergedBrokenLine = BrokenLineMerger.mergeBrokenLines(shapes);
        System.out.println("Объединенная ломаная:");
        System.out.println(mergedBrokenLine);
        System.out.println("Общее количество точек: " + mergedBrokenLine.getPoints().size());
    }
}
