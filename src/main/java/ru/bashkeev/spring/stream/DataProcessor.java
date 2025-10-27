package ru.bashkeev.spring.stream;

import java.util.List;

public interface DataProcessor {
    List<String> process(List<String> input);
}
