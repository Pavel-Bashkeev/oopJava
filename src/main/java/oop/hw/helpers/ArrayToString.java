package oop.hw.helpers;

import oop.hw.classes.points.Point;

import java.util.List;

public class ArrayToString {
    public static String arrayToString(int[] arr) {
        if (arr == null) return "[]";
        if (arr.length == 0) return "[]";

        String str = "[";
        for (int i = 0; i < arr.length -1; i++) {
            str += arr[i] + ", ";
        }
        str += arr[arr.length - 1] + "]";
        return str;
    }

    public static String intListToString(List<Integer> arr) {
        if (arr == null) return "[]";
        if (arr.isEmpty()) return "[]";

        String str = "[";
        for (int i = 0; i < arr.size() -1; i++) {
            str += arr.get(i) + ", ";
        }
        str += arr.getLast() + "]";
        return str;
    }

    public static String pointListToString(List<Point> points) {
        if (points == null) return "[]";
        if (points.isEmpty()) return "[]";
        String str = "[";
        for (int i = 0; i < points.size() -1; i++) {
            str += points.get(i).toString() + ", ";
        }
        str += points.getLast().toString() + "]";
        return str;
    }
}
