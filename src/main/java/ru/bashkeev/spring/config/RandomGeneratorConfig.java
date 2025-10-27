package ru.bashkeev.spring.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.bashkeev.spring.service.RandomGenerator;
import ru.bashkeev.spring.service.RangeRandomGenerator;

@Configuration
public class RandomGeneratorConfig {

    @Bean
    public RandomGenerator randomGenerator(@Qualifier("min") int min, @Qualifier("max") int max) {
        return new RangeRandomGenerator(min, max);
    }
}
