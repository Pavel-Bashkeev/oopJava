package ru.bashkeev.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.bashkeev.spring.entity.Review;
import ru.bashkeev.spring.service.RandomGenerator;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ReviewConfig {

    @Bean
    public List<Review> reviews(RandomGenerator randomGenerator) {
        return Arrays.asList(
                new Review("Очень хорошо", 4),
                new Review("Сойдет", 3),
                new Review("Сложно сказать", randomGenerator.next())
        );
    }
}