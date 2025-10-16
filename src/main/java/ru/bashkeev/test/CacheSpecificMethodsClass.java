package ru.bashkeev.test;

import ru.bashkeev.annotation.Cache;

@Cache({"getUserInfo", "getSettings"})
public class CacheSpecificMethodsClass implements UserService {
    private int userCallCount = 0;
    private int settingsCallCount = 0;
    private int otherCallCount = 0;

    @Override
    public String getUserInfo(String userId) {
        userCallCount++;
        return "User info for " + userId + ", calls: " + userCallCount;
    }

    @Override
    public String getSettings(String category) {
        settingsCallCount++;
        return "Settings for " + category + ", calls: " + settingsCallCount;
    }

    @Override
    public String getOtherData() {
        otherCallCount++;
        return "Other data, calls: " + otherCallCount;
    }

    public int getUserCallCount() { return userCallCount; }
    public int getSettingsCallCount() { return settingsCallCount; }
    public int getOtherCallCount() { return otherCallCount; }
}
