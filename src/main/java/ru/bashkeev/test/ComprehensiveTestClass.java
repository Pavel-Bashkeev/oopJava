package ru.bashkeev.test;

import ru.bashkeev.annotation.Invoke;

/**
 * Комплексный тестовый класс со всеми вариантами методов для тестирования InvokeCollector
 */
public class ComprehensiveTestClass {
    // Поля для отслеживания состояния
    private static int staticCallCount = 0;
    private       int    instanceCallCount = 0;
    private final String name;

    public ComprehensiveTestClass() {
        this.name = "Default";
    }

    public ComprehensiveTestClass(String name) {
        this.name = name;
    }

    // ========== ВАЛИДНЫЕ МЕТОДЫ (должны быть вызваны) ==========

    @Invoke
    public static String getStaticMessage() {
        staticCallCount++;
        return "Static message, calls: " + staticCallCount;
    }

    @Invoke
    public static int getStaticNumber() {
        return 42;
    }

    @Invoke
    public static boolean isStaticActive() {
        return true;
    }

    @Invoke
    public String getName() {
        return name;
    }

    @Invoke
    public int getInstanceCallCount() {
        instanceCallCount++;
        return instanceCallCount;
    }

    @Invoke
    public String getFullInfo() {
        return "Name: " + name + ", calls: " + instanceCallCount;
    }

    @Invoke
    public String getNullable() {
        return null;
    }

    // ========== НЕВАЛИДНЫЕ МЕТОДЫ (не должны быть вызваны) ==========

    @Invoke
    public String methodWithParams(String param) {
        return "With param: " + param;
    }

    @Invoke
    public static String staticWithParams(int number) {
        return "Static with param: " + number;
    }

    @Invoke
    public void voidMethod() {
        System.out.println("Void method called");
    }

    @Invoke
    public static void staticVoid() {
        System.out.println("Static void called");
    }

    public String unannotatedMethod() {
        return "This should not be called";
    }

    public static String unannotatedStatic() {
        return "This static should not be called";
    }

    // ========== ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ ==========

    public static void resetStaticCounter() {
        staticCallCount = 0;
    }

    public void resetInstanceCounter() {
        instanceCallCount = 0;
    }

    public static int getStaticCallCount() {
        return staticCallCount;
    }

    public int getRawInstanceCallCount() {
        return instanceCallCount;
    }
}