package ru.bashkeev.test;

import ru.bashkeev.annotation.Cache;

@Cache
public class CacheAllMethodsClass implements CacheableService {
    private int callCount = 0;

    @Override
    public String getAllData() {
        callCount++;
        return "All data, call count: " + callCount;
    }

    @Override
    public String getSpecificData(String filter) {
        callCount++;
        return "Specific data: " + filter + ", call count: " + callCount;
    }

    @Override
    public int calculate(int a, int b) {
        callCount++;
        return a + b;
    }

    public int getCallCount() {
        return callCount;
    }
}

