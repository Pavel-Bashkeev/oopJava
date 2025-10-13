package ru.bashkeev.test;

public interface GeometryService {
    double distanceTo(AnnotatedPoint other);
    String getPointInfo();
    void initializePoint();
}