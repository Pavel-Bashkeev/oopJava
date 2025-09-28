package ru.bashkeev.utils;

import java.lang.reflect.Field;
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
}