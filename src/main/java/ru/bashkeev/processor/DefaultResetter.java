package ru.bashkeev.processor;

import ru.bashkeev.annotation.Default;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultResetter {
    public static void reset(Object... objs) {
        System.out.println("===== @DefaultResetter Processor started =====");
        for (Object obj : objs) {
            resetObjectDefaults(obj);
        }
        System.out.println("===== @DefaultResetter Processor end =====\n\n");
    }

    private static void resetObjectDefaults(Object obj) {
        Class<?> cls = obj.getClass();

        boolean  classHasDefault = cls.isAnnotationPresent(Default.class);
        Class<?> clsDefaultType  = classHasDefault ? cls.getAnnotation(Default.class).value() : null;

        List<Field> fields = getAllFields(cls);

        for (Field field : fields) {
            try {
                field.setAccessible(true);

                if (field.isAnnotationPresent(Default.class)) {
                    Default fieldAnnotation = field.getAnnotation(Default.class);
                    Object  defaultValue    = createDefaultValue(fieldAnnotation.value());
                    field.set(obj, defaultValue);
                    System.out.println("===== @DefaultResetter Processor field " + field.getName() + " set to: " + defaultValue + " =====");
                } else if (classHasDefault && !field.isAnnotationPresent(Default.class)) {
                    Object defaultValue = createDefaultValue(clsDefaultType);
                    field.set(obj, defaultValue);
                    System.out.println("===== @DefaultResetter Processor field " + field.getName() + " set to class default: " + defaultValue + " =====");
                } else if (!field.isAnnotationPresent(Default.class)) {
                    Object defaultNativeValue = getNativeDefaultValue(field.getType());
                    if (defaultNativeValue != null) {
                        field.set(obj, defaultNativeValue);
                        System.out.println("===== @DefaultResetter Processor field " + field.getName() + " set to class default: " + defaultNativeValue + " =====");
                    }
                }
            } catch (Exception e) {
                System.out.println("===== @DefaultResetter Processor Failed to reset field '" + field.getName() + "': " + e.getMessage() + " =====");
            }
        }
    }

    private static List<Field> getAllFields(Class<?> cls) {
        List<Field> fields = new ArrayList<>();
        while (cls != null) {
            fields.addAll(Arrays.asList(cls.getDeclaredFields()));
            cls = cls.getSuperclass();
        }
        return fields;
    }

    private static Object createDefaultValue(Class<?> type) {
        if (type == String.class) return "default";
        if (type == Integer.class || type == int.class) return 0;
        if (type == Double.class || type == double.class) return 0.0;
        if (type == Boolean.class || type == boolean.class) return false;
        if (type == Long.class || type == long.class) return 0L;
        if (type == Float.class || type == float.class) return 0.0f;
        try {
            return type.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }
    }

    private static Object getNativeDefaultValue(Class<?> type) {
        if (!type.isPrimitive() && type != String.class) return null;

        if (type == int.class) return 0;
        if (type == double.class) return 0.0;
        if (type == boolean.class) return false;
        if (type == long.class) return 0L;
        if (type == float.class) return 0.0f;
        if (type == char.class) return '\0';
        if (type == byte.class) return (byte) 0;
        if (type == short.class) return (short) 0;
        if (type == String.class) return null;

        return null;
    }
}
