package ru.bashkeev.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.bashkeev.spring.stream.DataProcessor;
import ru.bashkeev.spring.stream.processors.FilterLongWordsProcessor;
import ru.bashkeev.spring.stream.processors.RemoveAProcessor;
import ru.bashkeev.spring.stream.processors.ToUpperCaseProcessor;

import java.util.List;

@Configuration
public class StreamingConfig {

    @Bean
    public List<DataProcessor> processors(
            ToUpperCaseProcessor toUpperCaseProcessor,
            RemoveAProcessor removeAProcessor,
            FilterLongWordsProcessor filterLongWordsProcessor
    ) {
        return List.of(
                toUpperCaseProcessor,
                removeAProcessor,
                filterLongWordsProcessor
        );
    }
}
