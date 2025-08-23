package  ru.bashkeev.utils;

import org.junit.jupiter.api.Test;
import ru.bashkeev.utils.CollectionUtils;
import ru.bashkeev.utils.Function;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CollectionUtilsTest {

    @Test
    public void testStringLengths() {
        List<String> strings = List.of("qwerty", "asdfg", "zx");
        List<Integer> result = CollectionUtils.map(strings, new Function<String, Integer>() {
            @Override
            public Integer apply(String str) {
                return str.length();
            }
        });

        assertEquals(List.of(6, 5, 2), result);
    }

    @Test
    public void testAbsoluteValues() {
        List<Integer> numbers = List.of(1, -3, 7);
        List<Integer> result = CollectionUtils.map(numbers, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer number) {
                return Math.abs(number);
            }
        });

        assertEquals(List.of(1, 3, 7), result);
    }

    @Test
    public void testMaxArrayValues() {
        List<int[]> arrays = List.of(
                new int[]{1, 2, 3},
                new int[]{-1, 0, 5},
                new int[]{10, -5, 8, 2}
        );

        List<Integer> result = CollectionUtils.map(arrays, new Function<int[], Integer>() {
            @Override
            public Integer apply(int[] array) {
                int max = Integer.MIN_VALUE;
                for (int num : array) {
                    if (num > max) max = num;
                }
                return max;
            }
        });

        assertEquals(List.of(3, 5, 10), result);
    }

    @Test
    public void testNumberPartitioning() {
        List<Integer> numbers = List.of(1, -3, 7);

        Map<String, List<Integer>> result = CollectionUtils.collect(
                numbers,
                new Provider<Map<String, List<Integer>>>() {
                    public Map<String, List<Integer>> get() {
                        Map<String, List<Integer>> map = new HashMap<>();
                        map.put("positive", new ArrayList<>());
                        map.put("negative", new ArrayList<>());
                        return map;
                    }
                },
                new Accumulator<Map<String, List<Integer>>, Integer>() {
                    public void accept(Map<String, List<Integer>> map, Integer number) {
                        if (number >= 0) map.get("positive").add(number);
                        else map.get("negative").add(number);
                    }
                }
        );

        assertEquals(List.of(1, 7), result.get("positive"));
        assertEquals(List.of(-3), result.get("negative"));
    }

    @Test
    public void testStringGrouping() {
        List<String> strings = List.of("qwerty", "asdfg", "zx", "qw");

        Map<Integer, List<String>> result = CollectionUtils.collect(
                strings,
                new Provider<Map<Integer, List<String>>>() {
                    public Map<Integer, List<String>> get() {
                        return new HashMap<>();
                    }
                },
                new Accumulator<Map<Integer, List<String>>, String>() {
                    public void accept(Map<Integer, List<String>> map, String str) {
                        int length = str.length();
                        if (!map.containsKey(length)) {
                            map.put(length, new ArrayList<>());
                        }
                        map.get(length).add(str);
                    }
                }
        );

        assertEquals(List.of("qwerty"), result.get(6));
        assertEquals(List.of("asdfg"), result.get(5));
        assertEquals(List.of("zx", "qw"), result.get(2));
    }

    @Test
    public void testUniqueStrings() {
        List<String> strings = List.of("qwerty", "asdfg", "qwerty", "qw");

        Set<String> result = CollectionUtils.collect(
                strings,
                new Provider<Set<String>>() {
                    public Set<String> get() {
                        return new HashSet<>();
                    }
                },
                new Accumulator<Set<String>, String>() {
                    public void accept(Set<String> set, String str) {
                        set.add(str);
                    }
                }
        );

        assertEquals(3, result.size());
        assertTrue(result.contains("qwerty"));
        assertTrue(result.contains("asdfg"));
        assertTrue(result.contains("qw"));
    }
}