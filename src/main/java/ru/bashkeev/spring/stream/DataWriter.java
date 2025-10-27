package ru.bashkeev.spring.stream;

import java.util.List;

public interface DataWriter {
    void write(String outputFile, List<String> data);
}
