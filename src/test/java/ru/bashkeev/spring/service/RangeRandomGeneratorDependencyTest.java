package ru.bashkeev.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.bashkeev.spring.config.MinMaxConfig;
import ru.bashkeev.spring.config.RandomGeneratorConfig;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Задача 9.2.1 Тесты для RangeRandomGenerator с зависимостями от MinMaxConfig")
class RangeRandomGeneratorDependencyTest {

    @Test
    @DisplayName("Проверка доступности бинов Min/Max в контексте")
    void testMinMaxBeans_AreAvailableInContext() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MinMaxConfig.class, RandomGeneratorConfig.class);

        Integer minBean = context.getBean("min", Integer.class);
        Integer maxBean = context.getBean("max", Integer.class);

        assertNotNull(minBean, "Бин 'min' должен быть доступен в контексте");
        assertNotNull(maxBean, "Бин 'max' должен быть доступен в контексте");
        assertEquals(2, minBean, "Бин 'min' должен возвращать 2");
        assertEquals(5, maxBean, "Бин 'max' должен возвращать 5");

        context.close();
    }

    @Test
    @DisplayName("Проверка генерации чисел в диапазоне Min/Max")
    void testRangeRandomGenerator_UsesMinMaxFromBeans() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MinMaxConfig.class, RandomGeneratorConfig.class);

        RandomGenerator randomGenerator = context.getBean(RandomGenerator.class);

        Set<Integer> generatedNumbers = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            int value = randomGenerator.next();
            generatedNumbers.add(value);

            assertTrue(value >= 2 && value <= 5,
                    "Сгенерированное число " + value + " должно быть в диапазоне [2,5] из MinMaxConfig");
        }

        Set<Integer> expectedNumbers = Set.of(2, 3, 4, 5);
        assertTrue(generatedNumbers.containsAll(expectedNumbers),
                "Должны быть сгенерированы все числа из диапазона MinMaxConfig: 2,3,4,5");

        context.close();
    }

    @Test
    @DisplayName("Проверка уникальности чисел в пределах цикла")
    void testRangeRandomGenerator_RespectsBeanRange() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MinMaxConfig.class, RandomGeneratorConfig.class);

        RandomGenerator randomGenerator = context.getBean(RandomGenerator.class);

        Set<Integer> firstCycle = new HashSet<>();

        for (int i = 0; i < 4; i++) {
            firstCycle.add(randomGenerator.next());
        }

        assertEquals(Set.of(2, 3, 4, 5), firstCycle,
                "Должны быть сгенерированы все числа из диапазона MinMaxConfig");

        context.close();
    }

    @Test
    @DisplayName("Проверка корректного внедрения зависимостей")
    void testRangeRandomGenerator_BeanInjection() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MinMaxConfig.class, RandomGeneratorConfig.class);

        RandomGenerator randomGenerator = context.getBean(RandomGenerator.class);

        assertNotNull(randomGenerator, "RandomGenerator должен быть внедрен через Spring");

        assertInstanceOf(RangeRandomGenerator.class, randomGenerator,
                "Должна быть внедрена реализация RangeRandomGenerator");

        context.close();
    }

    @Test
    @DisplayName("Проверка конфигурации MinMaxConfig")
    void testMinMaxConfig_ProducesCorrectBeans() {
        MinMaxConfig minMaxConfig = new MinMaxConfig();

        int min = minMaxConfig.min();
        int max = minMaxConfig.max();

        assertEquals(2, min, "Метод min() должен возвращать 2");
        assertEquals(5, max, "Метод max() должен возвращать 5");
    }
}