package ru.bashkeev.spring.stream.processors;

import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;
import ru.bashkeev.spring.stream.DataProcessor;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Order(1)
public class ToUpperCaseProcessor implements DataProcessor {
    @Override
    public List<String> process(List<String> input) {
        return input.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }
}
