package ru.bashkeev.test;

public interface UserService {
    String getUserInfo(String userId);
    String getSettings(String category);
    String getOtherData();
}
