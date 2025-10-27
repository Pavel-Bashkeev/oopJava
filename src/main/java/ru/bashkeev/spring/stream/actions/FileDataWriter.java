package ru.bashkeev.spring.stream.actions;

import org.springframework.stereotype.Component;
import ru.bashkeev.spring.stream.DataWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class FileDataWriter implements DataWriter {
    @Override
    public void write(String outputFile, List<String> data) {
        try {
            Files.write(Path.of(outputFile), data);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи файла: " + outputFile, e);
        }
    }
}
