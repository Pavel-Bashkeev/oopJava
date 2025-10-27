package ru.bashkeev.spring.service;

import org.junit.jupiter.api.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.bashkeev.spring.config.*;
import ru.bashkeev.spring.entity.Review;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты для задач 9.2.2 и 9.2.3 - Отзывы и лучший отзыв")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReviewIntegrationTest {

    @Test
    @Order(1)
    @DisplayName("9.2.2 - Создание коллекции отзывов")
    void testReviewCollectionCreation() {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                MinMaxConfig.class,
                RandomGeneratorConfig.class,
                ReviewConfig.class
        )) {
            @SuppressWarnings("unchecked")
            List<Review> reviews = (List<Review>) context.getBean("reviews");

            System.out.println("=== Задача 9.2.2 - Коллекция отзывов ===");
            System.out.println("Количество отзывов: " + reviews.size());
            reviews.forEach(review -> System.out.println("  - " + review));
            System.out.println();

            assertEquals(3, reviews.size());
            assertTrue(reviews.stream().anyMatch(r -> r.getText().equals("Очень хорошо") && r.getRating() == 4));
            assertTrue(reviews.stream().anyMatch(r -> r.getText().equals("Сойдет") && r.getRating() == 3));
            assertTrue(reviews.stream().anyMatch(r -> r.getText().equals("Сложно сказать")));
        }
    }

    @Test
    @Order(2)
    @DisplayName("9.2.3 - Создание бина лучшего отзыва")
    void testBestReviewBeanCreation() {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                MinMaxConfig.class,
                RandomGeneratorConfig.class,
                ReviewConfig.class,
                BestReviewConfig.class
        )) {
            Review bestReview = context.getBean("bestReview", Review.class);

            System.out.println("=== Задача 9.2.3 - Бин лучшего отзыва ===");
            System.out.println("Лучший отзыв: " + bestReview);
            System.out.println();

            assertNotNull(bestReview);
            assertTrue(bestReview.getRating() >= 3 && bestReview.getRating() <= 5);
            assertTrue(List.of("Очень хорошо", "Сойдет", "Сложно сказать").contains(bestReview.getText()));
        }
    }

    @Test
    @Order(3)
    @DisplayName("9.2.3 - Динамическое определение лучшего отзыва")
    void testBestReviewDynamicSelection() {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                MinMaxConfig.class,
                RandomGeneratorConfig.class,
                ReviewConfig.class,
                BestReviewConfig.class
        )) {
            @SuppressWarnings("unchecked")
            List<Review> reviews = (List<Review>) context.getBean("reviews");
            Review bestReview = context.getBean("bestReview", Review.class);

            System.out.println("=== Динамическое определение лучшего отзыва ===");
            System.out.println("Все отзывы:");
            reviews.forEach(r -> System.out.println("  - " + r));
            System.out.println("Лучший отзыв: " + bestReview);
            System.out.println();

            int maxRating = reviews.stream()
                    .mapToInt(Review::getRating)
                    .max()
                    .orElse(0);

            assertEquals(maxRating, bestReview.getRating(),
                    "Лучший отзыв должен иметь максимальную оценку");
        }
    }

    @Test
    @Order(4)
    @DisplayName("Интеграция всех компонентов")
    void testFullIntegration() {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                MinMaxConfig.class,
                RandomGeneratorConfig.class,
                ReviewConfig.class,
                BestReviewConfig.class
        )) {
            System.out.println("=== Интеграция всех компонентов ===");

            assertDoesNotThrow(() -> context.getBean("min", Integer.class));
            assertDoesNotThrow(() -> context.getBean("max", Integer.class));
            assertDoesNotThrow(() -> context.getBean(RandomGenerator.class));
            assertDoesNotThrow(() -> context.getBean("reviews", List.class));
            assertDoesNotThrow(() -> context.getBean("bestReview", Review.class));

            @SuppressWarnings("unchecked")
            List<Review> reviews = (List<Review>) context.getBean("reviews");
            Review bestReview = context.getBean("bestReview", Review.class);

            System.out.println("Min: " + context.getBean("min"));
            System.out.println("Max: " + context.getBean("max"));
            System.out.println("Все отзывы: " + reviews);
            System.out.println("Лучший отзыв: " + bestReview);
            System.out.println("✅ Все компоненты работают корректно");
        }
    }
}