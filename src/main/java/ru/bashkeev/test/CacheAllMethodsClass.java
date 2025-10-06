package ru.bashkeev.test;

import ru.bashkeev.annotation.Cache;

@Cache  // value() default {} - кэшируем все методы
public class CacheAllMethodsClass {
    private int callCount = 0;

    public String getAllData() {
        callCount++;
        return "All data, call count: " + callCount;
    }

    public String getSpecificData(String filter) {
        callCount++;
        return "Specific data: " + filter + ", call count: " + callCount;
    }

    public int calculate(int a, int b) {
        callCount++;
        return a + b;
    }

    public int getCallCount() {
        return callCount;
    }
}
