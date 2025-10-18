package ru.bashkeev.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinMaxConfig {
    @Bean(name = "min")
    public int min() {
        return 2;
    }

    @Bean(name = "max")
    public int max() {
        return 5;
    }
}
