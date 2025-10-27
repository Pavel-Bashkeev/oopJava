package ru.bashkeev.spring.stream.processors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.bashkeev.spring.stream.DataProcessor;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Order(3)
public class FilterLongWordsProcessor implements DataProcessor {
    private final int maxWordLength;

    public FilterLongWordsProcessor(@Qualifier("maxWordLength") int maxWordLength) {
        this.maxWordLength = maxWordLength;
    }

    @Override
    public List<String> process(List<String> input) {
        return input.stream()
                .filter(word -> word.length() <= maxWordLength)
                .collect(Collectors.toList());
    }
}
