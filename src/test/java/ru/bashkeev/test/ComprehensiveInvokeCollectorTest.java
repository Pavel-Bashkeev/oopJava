package ru.bashkeev.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

import ru.bashkeev.processor.InvokeCollectorProcessor;

import java.util.Map;

class ComprehensiveInvokeCollectorTest {

    @BeforeEach
    void setUp() {
        ComprehensiveTestClass.resetStaticCounter();
    }

    private String getFullMethodKey(String methodName) {
        return "ru.bashkeev.test.ComprehensiveTestClass." + methodName;
    }

    @Test
    @DisplayName("Тест 1: Должен вызывать только валидные методы и игнорировать невалидные")
    void testOnlyValidMethodsAreInvoked() {
        Map<String, Object> results = InvokeCollectorProcessor.collect(ComprehensiveTestClass.class);

        assertNotNull(results, "Результат не должен быть null");

        // Проверяем количество вызванных методов
        assertEquals(7, results.size(), "Должно быть вызвано 7 валидных методов");

        // Проверяем ВАЛИДНЫЕ методы (должны быть в результатах)
        assertAll(
                // Статические методы
                () -> assertTrue(results.containsKey(getFullMethodKey("getStaticMessage")),
                        "Статический метод getStaticMessage должен быть вызван"),
                () -> assertTrue(results.containsKey(getFullMethodKey("getStaticNumber")),
                        "Статический метод getStaticNumber должен быть вызван"),
                () -> assertTrue(results.containsKey(getFullMethodKey("isStaticActive")),
                        "Статический метод isStaticActive должен быть вызван"),

                // Объектные методы
                () -> assertTrue(results.containsKey(getFullMethodKey("getName")),
                        "Объектный метод getName должен быть вызван"),
                () -> assertTrue(results.containsKey(getFullMethodKey("getInstanceCallCount")),
                        "Объектный метод getInstanceCallCount должен быть вызван"),
                () -> assertTrue(results.containsKey(getFullMethodKey("getFullInfo")),
                        "Объектный метод getFullInfo должен быть вызван"),
                () -> assertTrue(results.containsKey(getFullMethodKey("getNullable")),
                        "Метод возвращающий null должен быть вызван")
        );
    }

    @Test
    @DisplayName("Тест 2: Должен корректно обрабатывать возвращаемые значения и состояние")
    void testReturnValuesAndState() {
        Map<String, Object> results = InvokeCollectorProcessor.collect(ComprehensiveTestClass.class);

        assertAll(
                // Статические методы
                () -> assertEquals("Static message, calls: 1",
                        results.get(getFullMethodKey("getStaticMessage")),
                        "Статический метод должен вернуть корректное сообщение с счетчиком"),
                () -> assertEquals(42,
                        results.get(getFullMethodKey("getStaticNumber")),
                        "Статический метод должен вернуть число 42"),
                () -> assertEquals(true,
                        results.get(getFullMethodKey("isStaticActive")),
                        "Статический метод должен вернуть true"),

                // Объектные методы
                () -> assertEquals("Default",
                        results.get(getFullMethodKey("getName")),
                        "Объектный метод должен вернуть имя по умолчанию"),
                () -> assertEquals(1,
                        results.get(getFullMethodKey("getInstanceCallCount")),
                        "Объектный метод должен вернуть инкрементированный счетчик"),

                // getFullInfo теперь проверяем только структуру, а не конкретное значение счетчика
                () -> assertTrue(((String) results.get(getFullMethodKey("getFullInfo"))).startsWith("Name: Default, calls:"),
                        "Объектный метод должен вернуть полную информацию"),
                () -> assertNull(results.get(getFullMethodKey("getNullable")),
                        "Метод может возвращать null")
        );
    }

    @Test
    @DisplayName("Тест 3: Должен корректно обрабатывать множественные вызовы и изолировать экземпляры")
    void testMultipleCallsAndInstanceIsolation() {

        Map<String, Object> firstCall  = InvokeCollectorProcessor.collect(ComprehensiveTestClass.class);
        Map<String, Object> secondCall = InvokeCollectorProcessor.collect(ComprehensiveTestClass.class);
        Map<String, Object> thirdCall  = InvokeCollectorProcessor.collect(ComprehensiveTestClass.class);

        assertAll(
                // Статические методы сохраняют состояние между вызовами
                () -> assertEquals("Static message, calls: 1",
                        firstCall.get(getFullMethodKey("getStaticMessage")),
                        "Первый вызов статического метода"),
                () -> assertEquals("Static message, calls: 2",
                        secondCall.get(getFullMethodKey("getStaticMessage")),
                        "Второй вызов статического метода (состояние сохраняется)"),
                () -> assertEquals("Static message, calls: 3",
                        thirdCall.get(getFullMethodKey("getStaticMessage")),
                        "Третий вызов статического метода (состояние сохраняется)"),

                // Объектные методы создают новые экземпляры (состояние сбрасывается)
                () -> assertEquals(1,
                        firstCall.get(getFullMethodKey("getInstanceCallCount")),
                        "Первый вызов объектного метода"),
                () -> assertEquals(1,
                        secondCall.get(getFullMethodKey("getInstanceCallCount")),
                        "Второй вызов объектного метода (новый экземпляр)"),
                () -> assertEquals(1,
                        thirdCall.get(getFullMethodKey("getInstanceCallCount")),
                        "Третий вызов объектного метода (новый экземпляр)"),

                // Проверяем что для каждого экземпляра создается новый объект
                // (не проверяем конкретные значения calls в getFullInfo, так как порядок не гарантирован)
                () -> assertTrue(((String) firstCall.get(getFullMethodKey("getFullInfo"))).startsWith("Name: Default, calls:"),
                        "Полная информация первого экземпляра"),
                () -> assertTrue(((String) secondCall.get(getFullMethodKey("getFullInfo"))).startsWith("Name: Default, calls:"),
                        "Полная информация второго экземпляра"),
                () -> assertTrue(((String) thirdCall.get(getFullMethodKey("getFullInfo"))).startsWith("Name: Default, calls:"),
                        "Полная информация третьего экземпляра")
        );

        assertEquals(3, ComprehensiveTestClass.getStaticCallCount(),
                "После трех вызовов статический счетчик должен быть равен 3");
    }
}
