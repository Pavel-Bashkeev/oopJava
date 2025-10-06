package ru.bashkeev.test;

public class NoCacheClass {
    private int callCount = 0;

    public String getData() {
        callCount++;
        return "Data, call count: " + callCount;
    }

    public int getCallCount() {
        return callCount;
    }
}