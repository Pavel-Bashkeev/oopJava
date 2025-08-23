package ru.bashkeev.utils;

import org.junit.jupiter.api.Test;
import ru.bashkeev.utils.CollectionUtils;
import ru.bashkeev.utils.Predicate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CollectionUtilsFilterTest {

    @Test
    public void testFilterStringsByLength() {
        List<String> strings = List.of("qwerty", "asdfg", "zx");
        List<String> result = CollectionUtils.filter(strings, str -> str.length() >= 3);

        assertEquals(List.of("qwerty", "asdfg"), result);
    }

    @Test
    public void testFilterNegativeNumbers() {
        List<Integer> numbers = List.of(1, -3, 7);
        List<Integer> result = CollectionUtils.filter(numbers, number -> number < 0);

        assertEquals(List.of(-3), result);
    }

    @Test
    public void testFilterArraysWithoutPositive() {
        List<int[]> arrays = List.of(
                new int[]{-1, -2, -3},
                new int[]{-1, 0, 1},
                new int[]{-5, -10},
                new int[]{0, 0, 0}
        );

        List<int[]> result = CollectionUtils.filter(arrays, new Predicate<int[]>() {
            @Override
            public boolean test(int[] array) {
                for (int num : array) {
                    if (num > 0) return false;
                }
                return true;
            }
        });

        assertEquals(3, result.size());
        assertArrayEquals(new int[]{-1, -2, -3}, result.get(0));
        assertArrayEquals(new int[]{-5, -10}, result.get(1));
        assertArrayEquals(new int[]{0, 0, 0}, result.get(2));
    }

    @Test
    public void testFilterEmptyList() {
        List<String> emptyList = List.of();
        List<String> result = CollectionUtils.filter(emptyList, new Predicate<String>() {
            @Override
            public boolean test(String str) {
                return str.length() > 0;
            }
        });

        assertTrue(result.isEmpty());
    }
}
