package ru.bashkeev.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.bashkeev.spring.entity.Review;

import java.util.List;

@Configuration
public class BestReviewConfig {

    @Bean
    public Review bestReview(List<Review> reviews) {
        return reviews.stream()
                .max((r1, r2) -> Integer.compare(r1.getRating(), r2.getRating()))
                .orElseThrow(() -> new RuntimeException("Нет отзывов"));
    }
}