package ru.bashkeev.utils;

import org.junit.jupiter.api.Test;
import ru.bashkeev.geometry.line.Line;
import ru.bashkeev.geometry.points.Point;
import ru.bashkeev.geometry.points.PointFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LineUtilsTest {
    @Test
    public void testShiftLineStartXAuto() {
        Line<Point> positiveLine = new Line<>(PointFactory.getInstance().createPoint(5, 10), PointFactory.getInstance().createPoint(15, 20));
        LineUtils.shiftLineStartX(positiveLine);
        assertEquals(15, positiveLine.getStartPoint().getX()); // 5 + 10 = 15

        Line<Point> negativeLine = new Line<>(PointFactory.getInstance().createPoint(-7, 3), PointFactory.getInstance().createPoint(2, 8));
        LineUtils.shiftLineStartX(negativeLine);
        assertEquals(-17, negativeLine.getStartPoint().getX()); // -7 - 10 = -17
    }
}
