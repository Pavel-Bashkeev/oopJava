package ru.bashkeev.spring.stream.processors;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.bashkeev.spring.stream.DataProcessor;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Order(2)
public class RemoveAProcessor implements DataProcessor {
    @Override
    public List<String> process(List<String> input) {
        return input.stream()
                .map(s -> s.replace("A", ""))
                .collect(Collectors.toList());
    }
}
