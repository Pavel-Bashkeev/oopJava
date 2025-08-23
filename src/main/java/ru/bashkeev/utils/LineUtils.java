package ru.bashkeev.utils;

import ru.bashkeev.geometry.line.Line;
import ru.bashkeev.geometry.points.Point;

public class LineUtils {

    public static void shiftLineStartX(Line<? super Point> line) {
        Point startPoint = line.getStartPoint();
        int currentX = startPoint.getX();
        int newX = currentX + (currentX >= 0 ? 10 : -10);

        Point newPoint = startPoint.clone();
        newPoint.setX(newX);
        line.setStartPoint(newPoint);
    }
}