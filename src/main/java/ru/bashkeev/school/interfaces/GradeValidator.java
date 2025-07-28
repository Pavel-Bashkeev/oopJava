package ru.bashkeev.school.interfaces;

@FunctionalInterface
public interface GradeValidator {
    boolean isValid(int grade);
}
