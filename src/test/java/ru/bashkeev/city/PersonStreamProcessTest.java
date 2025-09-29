package ru.bashkeev.city;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PersonStreamProcessTest {
    @TempDir
    public  Path tempDir;
    private Path testFile;

    @BeforeEach
    public void setUp(@TempDir Path tempDir) throws IOException {
        testFile = tempDir.resolve("person.txt");
        createTestFile(testFile);
    }

    private static void createTestFile(Path file) {
        List<String> lines = Arrays.asList(
                "Вася:5",
                "Петя:3",
                "Аня:5",
                "маша:",
                "КОЛЯ:2",
                "света:3",
                "ИГОРЬ:",
                "ОЛЯ:5",
                "сергей:2",
                "Петя:-1",
                "Маша:0"
        );

        try {
            Files.write(file, lines);
            System.out.println("Создан тестовый файл person.txt");
        } catch (IOException e) {
            System.err.println("Не удалось создать тестовый файл: " + e.getMessage());
        }
    }

    @Test
    void testProcessPersonFileGroupingAndFormatting() {
        Map<Integer, List<String>> result = PersonStreamProcess.processPersonFile(testFile.toString());
        System.out.println(result);
        assertNotNull(result);
        assertEquals(5, result.size(), "Должно быть 5 групп по номерам");

        assertTrue(result.containsKey(5));
        assertEquals(Arrays.asList("Вася", "Аня", "Оля"), result.get(5));

        assertTrue(result.containsKey(3));
        assertEquals(Arrays.asList("Петя", "Света"), result.get(3));

        assertTrue(result.containsKey(2));
        assertEquals(Arrays.asList("Коля", "Сергей"), result.get(2));

        assertTrue(result.containsKey(1));
        assertEquals(List.of("Петя"), result.get(1));

        assertTrue(result.containsKey(0));
        assertEquals(List.of("Маша"), result.get(0));
    }

    @Test
    void testProcessPersonFileNameFormatting() {
        Map<Integer, List<String>> result = PersonStreamProcess.processPersonFile(testFile.toString());

        assertNotNull(result);

        result.values().forEach(names ->
                names.forEach(name -> {
                    assertTrue(Character.isUpperCase(name.charAt(0)),
                            "Первая буква должна быть заглавной: " + name);
                })
        );
    }
}
