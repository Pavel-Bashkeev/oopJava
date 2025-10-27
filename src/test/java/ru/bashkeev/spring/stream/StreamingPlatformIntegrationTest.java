package ru.bashkeev.spring.stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import ru.bashkeev.spring.config.StreamConfig;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = StreamConfig.class)
@ComponentScan("ru.bashkeev.spring.stream")
class StreamingPlatformIntegrationTest {

    @Autowired
    private StreamingPlatform streamingPlatform;

    @TempDir
    Path tempDir;

    @Test
    void testExecute_ShouldProcessDataThroughAllProcessors() throws Exception {
        Path inputFile = tempDir.resolve("input.txt");
        Path outputFile = tempDir.resolve("output.txt");

        List<String> inputData = Arrays.asList("apple", "banana", "cat", "dog", "elephant");
        Files.write(inputFile, inputData);

        List<String> expectedOutput = Arrays.asList("PPLE", "BNN", "CT", "DOG");

        streamingPlatform.execute(inputFile.toString(), outputFile.toString());

        List<String> actualOutput = Files.readAllLines(outputFile);
        assertEquals(expectedOutput, actualOutput);
    }

    @Configuration
    static class TestConfig {}
}