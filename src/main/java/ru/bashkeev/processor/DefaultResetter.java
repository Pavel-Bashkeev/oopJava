package ru.bashkeev.processor;

import ru.bashkeev.annotation.Default;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultResetter {
    private static final Map<Class<?>, DefaultValueStrategy> STRATEGIES = new ConcurrentHashMap<>();

    static {
        STRATEGIES.put(int.class, ctx -> 0);
        STRATEGIES.put(double.class, ctx -> 0.0);
        STRATEGIES.put(boolean.class, ctx -> false);
        STRATEGIES.put(long.class, ctx -> 0L);
        STRATEGIES.put(float.class, ctx -> 0.0f);
        STRATEGIES.put(char.class, ctx -> '\0');
        STRATEGIES.put(byte.class, ctx -> (byte) 0);
        STRATEGIES.put(short.class, ctx -> (short) 0);

        STRATEGIES.put(Integer.class, ctx -> 0);
        STRATEGIES.put(Boolean.class, ctx -> false);
        STRATEGIES.put(Double.class, ctx -> 0.0);
        STRATEGIES.put(Long.class, ctx -> 0L);
        STRATEGIES.put(Float.class, ctx -> 0.0f);
        STRATEGIES.put(Character.class, ctx -> '\0');
        STRATEGIES.put(Byte.class, ctx -> (byte) 0);
        STRATEGIES.put(Short.class, ctx -> (short) 0);

        STRATEGIES.put(String.class, ctx -> "default");
    }

    @FunctionalInterface
    private interface DefaultValueStrategy {
        Object createDefault(Context context);
    }

    private static class Context {
        private final Class<?> targetType;
        private final Default annotation;

        public Context(Class<?> targetType, Default annotation) {
            this.targetType = targetType;
            this.annotation = annotation;
        }

        public Class<?> getTargetType() {
            return targetType;
        }

        public Default getAnnotation() {
            return annotation;
        }
    }

    public static void reset(Object... objs) {
        System.out.println("===== @DefaultResetter Processor started =====");
        Arrays.stream(objs).forEach(DefaultResetter::resetObjectDefaults);
        System.out.println("===== @DefaultResetter Processor end =====\n\n");
    }

    private static void resetObjectDefaults(Object obj) {
        getAllFields(obj.getClass())
                .forEach(field -> resetField(obj, field));
    }

    private static void resetField(Object obj, Field field) {
        try {
            field.setAccessible(true);

            Object newValue = determineNewValue(field, obj.getClass());

            if (newValue != null) {
                field.set(obj, newValue);
                System.out.println("===== @DefaultResetter Processor field " + field.getName() +
                        " set to: " + newValue + " =====");
            }

        } catch (Exception e) {
            System.out.println("===== @DefaultResetter Processor Failed to reset field '" +
                    field.getName() + "': " + e.getMessage() + " =====");
        }
    }

    private static Object determineNewValue(Field field, Class<?> objClass) {
        if (field.isAnnotationPresent(Default.class)) {
            return getValueForType(field.getType());
        }

        if (shouldResetField(field)) {
            return getValueForType(field.getType());
        }

        return null;
    }

    private static boolean shouldResetField(Field field) {
        return field.getType().isPrimitive() || field.getType() == String.class;
    }

    private static Object getValueForType(Class<?> fieldType) {
        DefaultValueStrategy strategy = STRATEGIES.get(fieldType);
        return strategy != null ? strategy.createDefault(new Context(fieldType, null)) : null;
    }

    private static List<Field> getAllFields(Class<?> cls) {
        List<Field> fields = new ArrayList<>();
        Class<?> currentClass = cls;

        while (currentClass != null && currentClass != Object.class) {
            fields.addAll(Arrays.asList(currentClass.getDeclaredFields()));
            currentClass = currentClass.getSuperclass();
        }

        return fields;
    }
}
