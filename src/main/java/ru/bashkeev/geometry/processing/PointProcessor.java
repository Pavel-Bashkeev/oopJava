package ru.bashkeev.geometry.processing;

import ru.bashkeev.geometry.points.PointFactory;
import ru.bashkeev.geometry.points.Point;
import ru.bashkeev.geometry.line.BrokenLine;

import java.util.*;
import java.util.stream.Collectors;

public class PointProcessor {
    public static BrokenLine processPoints(List<Point> points) {
        return points.stream()
                .distinct()
                .sorted(Comparator.comparingInt(Point::getX))
                .map(point -> PointFactory.getInstance().createPoint(
                        point.getX(),
                        Math.abs(point.getY())
                ))
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        BrokenLine::new
                ));
    }
}
