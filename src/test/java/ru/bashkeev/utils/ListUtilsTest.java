package ru.bashkeev.utils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListUtilsTest {
    @Test
    public void testFillWithNumbersClearsList() {
        List<Integer> list = new ArrayList<>();
        list.add(999);
        list.add(1000);

        ListUtils.fillWithNumbers(list);

        assertEquals(100, list.size());
        assertEquals(1, list.getFirst());
        assertEquals(100, list.get(99));
    }
}
