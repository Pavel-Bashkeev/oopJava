package ru.bashkeev.spring.stream;

import java.util.List;

public interface DataReader {
    List<String> read(String inputFile);
}
