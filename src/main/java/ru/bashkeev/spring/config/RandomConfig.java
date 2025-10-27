package ru.bashkeev.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.bashkeev.spring.service.RandomGenerator;

import java.util.Random;

@Configuration
public class RandomConfig {

    private final RandomGenerator randomGenerator;

    public RandomConfig(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    @Bean
    @Scope("prototype")
    public int random() {
        return randomGenerator.next();
    }
}
