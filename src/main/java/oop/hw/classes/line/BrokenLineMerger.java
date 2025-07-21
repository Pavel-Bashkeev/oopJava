package oop.hw.classes.line;

import oop.hw.classes.points.Point;
import oop.hw.interfaces.PolylineProvider;

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
