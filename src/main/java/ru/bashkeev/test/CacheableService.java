package ru.bashkeev.test;

public interface CacheableService {
    String getAllData();
    String getSpecificData(String filter);
    int calculate(int a, int b);
}
