package ru.bashkeev.spring.stream.processors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FilterLongWordsProcessorTest {

    @Autowired
    private FilterLongWordsProcessor processor;

    @Test
    void testProcess_ShouldFilterWordsLongerThanMaxLength() {
        List<String> input    = Arrays.asList("cat", "elephant", "dog", "butterfly", "ant");
        List<String> expected = Arrays.asList("cat", "dog", "ant");
        List<String> result   = processor.process(input);

        assertEquals(expected, result);
    }

    @Test
    void testProcess_WithAllShortWords_ShouldReturnAll() {
        List<String> input    = Arrays.asList("a", "to", "the", "test");
        List<String> expected = Arrays.asList("a", "to", "the", "test");
        List<String> result   = processor.process(input);

        assertEquals(expected, result);
    }

    @Test
    void testProcess_WithAllLongWords_ShouldReturnEmptyList() {
        List<String> input  = Arrays.asList("elephant", "butterfly", "dinosaur");
        List<String> result = processor.process(input);

        assertTrue(result.isEmpty());
    }

    @Test
    void testProcess_WithEmptyList_ShouldReturnEmptyList() {
        List<String> input  = Arrays.asList();
        List<String> result = processor.process(input);

        assertTrue(result.isEmpty());
    }

    @Configuration
    static class TestConfig {
        @Bean(name = "maxWordLength")
        public int maxWordLength() {
            return 4;
        }

        @Bean
        public FilterLongWordsProcessor filterLongWordsProcessor() {
            return new FilterLongWordsProcessor(maxWordLength());
        }
    }
}