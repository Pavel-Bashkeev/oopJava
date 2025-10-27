package ru.bashkeev.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StreamLimitsConfig {

    @Bean(name = "maxWordLength")
    public int maxWordLength() {
        return 4;
    }
}
