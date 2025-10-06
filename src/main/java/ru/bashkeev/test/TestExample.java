package ru.bashkeev.test;

import ru.bashkeev.annotation.*;
import ru.bashkeev.exception.TestFailedException;

public class TestExample {

    private static int counter = 0;

    @BeforeAll
    static void globalSetup() {
        System.out.println("Global setup");
    }

    @AfterAll
    static void globalCleanup() {
        System.out.println("Global cleanup");
    }

    @BeforeEach
    void setUp() {
        counter++;
        System.out.println("Setup " + counter);
    }

    @AfterEach
    void tearDown() {
        System.out.println("Teardown");
    }

    @Test(name = "Первый тест", order = 1, tags = {"fast"})
    void firstTest() {
        System.out.println("First test executed");
    }

    @Test(name = "Второй тест", order = 2, dependsOn = {"firstTest"}, tags = {"fast"})
    void secondTest() {
        System.out.println("Second test executed");
    }

    @Test(name = "Третий тест", order = 0)
    void thirdTest() {
        System.out.println("Third test executed");
    }

    @Test(name = "Падающий тест")
    void failingTest() {
        System.out.println("Failing test executed");
        throw new TestFailedException("Тест упал по плану");
    }

    @Test
    @Disabled
    void disabledTest() {
        System.out.println("Этот тест не должен выполняться");
    }

    @Test(name = "Тест чисел")
    @ValueSource(ints = {1, 2, 3})
    void testNumbers(int number) {
        System.out.println("Обрабатываем число: " + number);
    }

    @Test(name = "Тест строк")
    @ValueSource(strings = {"hello", "world"})
    void testStrings(String text) {
        System.out.println("Обрабатываем строку: " + text);
    }

    @Test(name = "Тест с ошибкой")
    @ValueSource(ints = {1, 0, 2})
    void testWithError(int number) {
        if (number == 0) {
            throw new TestFailedException("Деление на ноль");
        }
        System.out.println("100 / " + number + " = " + (100 / number));
    }
}