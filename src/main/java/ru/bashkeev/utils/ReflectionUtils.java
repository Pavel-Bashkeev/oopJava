package ru.bashkeev.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReflectionUtils {

    public static List<Field> fieldCollection(Class<?> clazz) {
        List<Field> allFields    = new ArrayList<>();
        Class<?>    currentClass = clazz;

        while (currentClass != null && currentClass != Object.class) {
            Field[] declaredFields = currentClass.getDeclaredFields();
            Collections.addAll(allFields, declaredFields);
            currentClass = currentClass.getSuperclass();
        }

        return Collections.unmodifiableList(allFields);
    }

    public static List<Method> validationMethods(Class<?> clazz) {
        List<Method> validationMethods = new ArrayList<>();
        Class<?> currentClass = clazz;

        while (currentClass != null && currentClass != Object.class) {
            Method[] declaredMethods = currentClass.getDeclaredMethods();
            for (Method method : declaredMethods) {
                if (isValidationMethod(method)) {
                    validationMethods.add(method);
                }
            }
            currentClass = currentClass.getSuperclass();
        }

        return Collections.unmodifiableList(validationMethods);
    }

    private static boolean isValidationMethod(Method method) {
        return method.getName().startsWith("test") &&
                Modifier.isStatic(method.getModifiers()) &&
                method.getReturnType() == void.class &&
                method.getParameterCount() == 1 &&
                method.getParameterTypes()[0] == Object.class;
    }
}