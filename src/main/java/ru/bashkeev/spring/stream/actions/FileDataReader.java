package ru.bashkeev.spring.stream.actions;

import org.springframework.stereotype.Component;
import ru.bashkeev.spring.stream.DataReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class FileDataReader implements DataReader {
    @Override
    public List<String> read(String inputFile) {
        try {
            return Files.readAllLines(Path.of(inputFile));
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла: " + inputFile, e);
        }
    }
}