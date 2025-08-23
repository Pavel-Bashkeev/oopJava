package ru.bashkeev.utils;

import java.util.List;

public class ListUtils {

    public static void fillWithNumbers(List<? super Integer> list) {
        if (list == null) {
            throw new IllegalArgumentException("Список не может быть null");
        }

        list.clear();

        for (int i = 1; i <= 100; i++) {
            list.add(i);
        }
    }
}
