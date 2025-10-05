package ru.bashkeev.main;

import ru.bashkeev.geometry.points.Point;
import ru.bashkeev.geometry.points.PointFactory;
import ru.bashkeev.utils.ValidationUtils;
import ru.bashkeev.validator.PointValidator;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Демонстрация валидации точек ===");

        PointFactory factory    = PointFactory.getInstance();
        Point        validPoint = factory.createPoint(5, 10);

        System.out.println("\n1. Валидация корректной точки:");
        try {
            ValidationUtils.validate(validPoint, PointValidator.class);
            System.out.println("+ Точка прошла валидацию");
        } catch (ValidationUtils.ValidateException e) {
            System.out.println("❌ " + e.getMessage());
        }

        System.out.println("\n2. Валидация фабрики:");
        try {
            ValidationUtils.validate(factory, PointValidator.class);
            System.out.println("+ Фабрика прошла валидацию");
        } catch (ValidationUtils.ValidateException e) {
            System.out.println("❌ " + e.getMessage());
        }

        Point invalidPoint1 = factory.createPoint(0, 0);
        Point invalidPoint2 = factory.createPoint(20000, 10);

        System.out.println("\n3. Валидация точки в начале координат:");
        try {
            ValidationUtils.validate(invalidPoint1, PointValidator.class);
            System.out.println("+ Точка прошла валидацию");
        } catch (ValidationUtils.ValidateException e) {
            System.out.println("❌ " + e.getMessage());
        }

        System.out.println("\n4. Валидация точки с большой координатой:");
        try {
            ValidationUtils.validate(invalidPoint2, PointValidator.class);
            System.out.println("+ Точка прошла валидацию");
        } catch (ValidationUtils.ValidateException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }
}