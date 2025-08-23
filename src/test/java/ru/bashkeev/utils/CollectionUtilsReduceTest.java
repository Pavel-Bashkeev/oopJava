package ru.bashkeev.utils;

import org.junit.jupiter.api.Test;
import ru.bashkeev.utils.CollectionUtils;
import ru.bashkeev.utils.Reduction;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class CollectionUtilsReduceTest {

    @Test
    public void testReduceStringConcatenation() {
        List<String> strings = List.of("qwerty", "asdfg", "zx");

        String result = CollectionUtils.reduce(strings,
                new Reduction<String>() {
                    @Override
                    public String apply(String left, String right) {
                        return left + right;
                    }
                },
                ""
        );

        assertEquals("qwertyasdfgzx", result);
    }

    @Test
    public void testReduceSumNumbers() {
        List<Integer> numbers = List.of(1, -3, 7);

        Integer result = CollectionUtils.reduce(numbers,
                new Reduction<Integer>() {
                    @Override
                    public Integer apply(Integer left, Integer right) {
                        return left + right;
                    }
                },
                0
        );

        assertEquals(5, result);
    }

    @Test
    public void testReduceNestedLists() {
        List<List<Integer>> listOfLists = List.of(
                List.of(1, 2, 3),
                List.of(4, 5),
                List.of(6, 7, 8, 9)
        );

        Integer result = CollectionUtils.reduce(
                CollectionUtils.map(listOfLists, List::size),
                new Reduction<Integer>() {
                    @Override
                    public Integer apply(Integer left, Integer right) {
                        return left + right;
                    }
                },
                0
        );

        assertEquals(9, result);
    }

    @Test
    public void testReduceEmptyList() {
        List<String> emptyList = List.of();

        // Вариант с Optional
        Optional<String> optionalResult = CollectionUtils.reduce(emptyList,
                new Reduction<String>() {
                    @Override
                    public String apply(String left, String right) {
                        return left + right;
                    }
                }
        );

        assertTrue(optionalResult.isEmpty());

        // Вариант с identity value
        String result = CollectionUtils.reduce(emptyList,
                new Reduction<String>() {
                    @Override
                    public String apply(String left, String right) {
                        return left + right;
                    }
                },
                "default"
        );

        assertEquals("default", result);
    }

    @Test
    public void testReduceSingleElement() {
        List<String> singleList = List.of("hello");

        Optional<String> result = CollectionUtils.reduce(singleList,
                new Reduction<String>() {
                    @Override
                    public String apply(String left, String right) {
                        return left + right;
                    }
                }
        );

        assertTrue(result.isPresent());
        assertEquals("hello", result.get());
    }
}