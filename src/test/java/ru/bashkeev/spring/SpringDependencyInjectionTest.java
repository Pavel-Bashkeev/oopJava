package ru.bashkeev.spring;

import org.junit.jupiter.api.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.bashkeev.spring.component.RangePredicate;
import ru.bashkeev.spring.config.*;
import ru.bashkeev.utils.Predicate;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Глава 9. Внедрение зависимостей - Практические задачи")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpringDependencyInjectionTest {

    @Test
    @Order(1)
    @DisplayName("Задача 9.1.1")
    void testHelloBean() {
        ApplicationContext context = new AnnotationConfigApplicationContext(HelloConfig.class);

        String hello = context.getBean("hello", String.class);

        System.out.println("=== Задача 9.1.1 Привет ===");
        System.out.println("Получено из контейнера: " + hello);
        System.out.println();

        assertEquals("Hello world", hello);
    }

    @Test
    @Order(2)
    @DisplayName("Задача 9.1.2")
    void testRandomBean() {
        ApplicationContext context = new AnnotationConfigApplicationContext(RandomConfig.class);

        int random1 = context.getBean("random", Integer.class);
        int random2 = context.getBean("random", Integer.class);

        // ВЫВОДИМ НА ЭКРАН
        System.out.println("=== Задача 9.1.2 random ===");
        System.out.println("Первое случайное значение: " + random1);
        System.out.println("Второе случайное значение: " + random2);
        System.out.println();

        assertNotEquals(random1, random2);
    }

    @Test
    @Order(3)
    @DisplayName("Задача 9.1.3")
    void testDateBean() {
        ApplicationContext context = new AnnotationConfigApplicationContext(DateConfig.class);

        Date date = context.getBean("startDate", Date.class);

        System.out.println("=== Задача 9.1.3 Начало ===");
        System.out.println("Дата первого обращения: " + date);
        System.out.println();

        try {
            Thread.sleep(100);
        } catch (InterruptedException _) {
        }

        Date date2 = context.getBean("startDate", Date.class);
        System.out.println("Второе обращение: " + date2);

        System.out.println("Даты одинаковые: " + date.equals(date2));
        assertEquals(date, context.getBean("startDate", Date.class));
    }

    @Test
    @Order(4)
    @DisplayName("Задача 9.1.4")
    void testRangePredicateWithMinMaxBeans() {
        ApplicationContext context = new AnnotationConfigApplicationContext(
                MinMaxConfig.class,
                RangePredicateConfig.class
        );

        Predicate<Integer> predicate = context.getBean(RangePredicate.class);

        System.out.println("=== Задача 9.1.4 ===");
        System.out.println("Проверка диапазона 2-5:");
        System.out.println("1 в диапазоне: " + predicate.test(1));
        System.out.println("2 в диапазоне: " + predicate.test(2));
        System.out.println("3 в диапазоне: " + predicate.test(3));
        System.out.println("4 в диапазоне: " + predicate.test(4));
        System.out.println("5 в диапазоне: " + predicate.test(5));
        System.out.println("6 в диапазоне: " + predicate.test(6));
        System.out.println();

        assertTrue(predicate.test(2), "2 должно быть в диапазоне");
        assertTrue(predicate.test(3), "3 должно быть в диапазоне");
        assertTrue(predicate.test(5), "5 должно быть в диапазоне");
        assertFalse(predicate.test(1), "1 не должно быть в диапазоне");
        assertFalse(predicate.test(6), "6 не должно быть в диапазоне");
    }

    @Test
    @Order(5)
    @DisplayName("Задача 9.1.5")
    void MinMaxBeans() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MinMaxConfig.class);

        int min = context.getBean("min", Integer.class);
        int max = context.getBean("max", Integer.class);

        System.out.println("=== Задача 9.1.5 Минимакс ===");
        System.out.println("Бин 'min': " + min);
        System.out.println("Бин 'max': " + max);
        System.out.println();

        System.out.println("min должно быть меньше max: " + (min < max));
        assertTrue(min < max, "min должно быть меньше max");
    }
}
