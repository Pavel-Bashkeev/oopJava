package ru.bashkeev.test;

import ru.bashkeev.annotation.Cache;

@Cache({"getUserInfo", "getSettings"})
public class CacheSpecificMethodsClass {
    private int userCallCount = 0;
    private int settingsCallCount = 0;
    private int otherCallCount = 0;

    public String getUserInfo(String userId) {
        userCallCount++;
        return "User info for " + userId + ", calls: " + userCallCount;
    }

    public String getSettings(String category) {
        settingsCallCount++;
        return "Settings for " + category + ", calls: " + settingsCallCount;
    }

    public String getOtherData() {
        otherCallCount++;
        return "Other data, calls: " + otherCallCount;
    }

    // Геттеры для тестирования
    public int getUserCallCount() { return userCallCount; }
    public int getSettingsCallCount() { return settingsCallCount; }
    public int getOtherCallCount() { return otherCallCount; }
}