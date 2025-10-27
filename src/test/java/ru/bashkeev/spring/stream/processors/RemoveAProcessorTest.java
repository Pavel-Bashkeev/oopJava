package ru.bashkeev.spring.stream.processors;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RemoveAProcessorTest {

    @Test
    void testProcess_ShouldRemoveAllLetterA() {
        RemoveAProcessor processor = new RemoveAProcessor();
        List<String>     input     = Arrays.asList("APPLE", "BANANA", "CAR", "DOG");
        List<String>     expected  = Arrays.asList("PPLE", "BNN", "CR", "DOG");
        List<String>     result    = processor.process(input);

        assertEquals(expected, result);
    }

    @Test
    void testProcess_WithNoLetterA_ShouldReturnSameStrings() {
        // Given
        RemoveAProcessor processor = new RemoveAProcessor();
        List<String>     input     = Arrays.asList("TEST", "DOG", "CAT", "BIRD");
        List<String>     expected  = Arrays.asList("TEST", "DOG", "CT", "BIRD");
        List<String>     result    = processor.process(input);

        assertEquals(expected, result);
    }

    @Test
    void testProcess_WithOnlyLetterA_ShouldReturnEmptyStrings() {
        // Given
        RemoveAProcessor processor = new RemoveAProcessor();
        List<String>     input     = Arrays.asList("AAA", "A", "AA");
        List<String>     expected  = Arrays.asList("", "", "");
        List<String>     result    = processor.process(input);

        assertEquals(expected, result);
    }

    @Test
    void testProcess_WithEmptyList_ShouldReturnEmptyList() {
        RemoveAProcessor processor = new RemoveAProcessor();
        List<String>     input     = Arrays.asList();
        List<String>     result    = processor.process(input);

        assertTrue(result.isEmpty());
    }
}