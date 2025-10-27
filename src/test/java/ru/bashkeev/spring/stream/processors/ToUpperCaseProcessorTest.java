package ru.bashkeev.spring.stream.processors;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToUpperCaseProcessorTest {

    @Test
    void testProcess_ShouldConvertAllStringsToUpperCase() {
        ToUpperCaseProcessor processor = new ToUpperCaseProcessor();
        List<String> input = Arrays.asList("hello", "world", "test");
        List<String> expected = Arrays.asList("HELLO", "WORLD", "TEST");

        List<String> result = processor.process(input);

        assertEquals(expected, result);
    }

    @Test
    void testProcess_WithEmptyList_ShouldReturnEmptyList() {
        ToUpperCaseProcessor processor = new ToUpperCaseProcessor();
        List<String> input = Arrays.asList();

        List<String> result = processor.process(input);

        assertTrue(result.isEmpty());
    }

    @Test
    void testProcess_WithMixedCase_ShouldConvertAllToUpperCase() {
        ToUpperCaseProcessor processor = new ToUpperCaseProcessor();
        List<String> input = Arrays.asList("Hello", "WoRlD", "tEsT");
        List<String> expected = Arrays.asList("HELLO", "WORLD", "TEST");

        List<String> result = processor.process(input);

        assertEquals(expected, result);
    }
}