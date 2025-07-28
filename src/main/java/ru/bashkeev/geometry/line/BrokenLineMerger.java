package ru.bashkeev.geometry.line;

import ru.bashkeev.geometry.points.Point;
import ru.bashkeev.geometry.interfaces.PolylineProvider;

import java.util.ArrayList;
import java.util.List;

public class BrokenLineMerger {
    public static BrokenLine mergeBrokenLines(PolylineProvider[] providers) {
        List<Point> mergedPoints = new ArrayList<>();

        for (PolylineProvider provider : providers) {
            BrokenLine brokenLine = provider.getBrokenLine();
            mergedPoints.addAll(brokenLine.getPoints());
        }

        return new BrokenLine(mergedPoints);
    }
}
