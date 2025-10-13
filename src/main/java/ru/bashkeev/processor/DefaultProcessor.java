package ru.bashkeev.processor;

import ru.bashkeev.annotation.Default;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultProcessor {

    // Регистр стратегий для создания значений по умолчанию
    private static final Map<Class<?>, DefaultValueCreator> VALUE_CREATORS = new ConcurrentHashMap<>();

    static {
        // Регистрируем создателей значений для разных типов
        VALUE_CREATORS.put(String.class, context ->
                context.getDefaultValue().isEmpty() ? "dft" : context.getDefaultValue());

        VALUE_CREATORS.put(Integer.class, context -> 0);
        VALUE_CREATORS.put(int.class, context -> 0);

        VALUE_CREATORS.put(Double.class, context -> 0.0);
        VALUE_CREATORS.put(double.class, context -> 0.0);

        VALUE_CREATORS.put(Boolean.class, context -> false);
        VALUE_CREATORS.put(boolean.class, context -> false);

        VALUE_CREATORS.put(Long.class, context -> 0L);
        VALUE_CREATORS.put(long.class, context -> 0L);

        VALUE_CREATORS.put(Float.class, context -> 0.0f);
        VALUE_CREATORS.put(float.class, context -> 0.0f);

        // Универсальный создатель через рефлексию
        VALUE_CREATORS.put(Object.class, context -> {
            try {
                return context.getTargetType().getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                return null;
            }
        });
    }

    @FunctionalInterface
    private interface DefaultValueCreator {
        Object createValue(ValueContext context);
    }

    // Контекст для передачи данных в создатели значений
    private static class ValueContext {
        private final Class<?> targetType;
        private final String defaultValue;
        private final Class<?> defaultType;

        public ValueContext(Class<?> targetType, String defaultValue, Class<?> defaultType) {
            this.targetType = targetType;
            this.defaultValue = defaultValue;
            this.defaultType = defaultType;
        }

        public Class<?> getTargetType() { return targetType; }
        public String getDefaultValue() { return defaultValue; }
        public Class<?> getDefaultType() { return defaultType; }
    }

    public static void process(Object obj) throws Exception {
        System.out.println("===== @Default Processor =====");
        Class<?> cls = obj.getClass();

        // Получаем аннотацию класса если есть
        Default classAnnotation = cls.getAnnotation(Default.class);
        String classDefaultValue = classAnnotation != null ? classAnnotation.value() : "";
        Class<?> classDefaultType = classAnnotation != null ? classAnnotation.type() : Void.class;

        if (classAnnotation != null) {
            System.out.println("===== @Default Processor class has default: " +
                    classDefaultValue + ", type: " + classDefaultType.getSimpleName() + " =====");
        }

        // Обрабатываем все поля
        Arrays.stream(cls.getDeclaredFields())
                .forEach(field -> processField(obj, field, classDefaultValue, classDefaultType));

        System.out.println("===== @Default Processor =====\n\n");
    }

    private static void processField(Object obj, Field field, String classDefaultValue, Class<?> classDefaultType) {
        try {
            field.setAccessible(true);

            // Определяем значение по умолчанию для поля
            Object defaultValue = determineFieldDefault(field, classDefaultValue, classDefaultType);

            if (defaultValue != null) {
                field.set(obj, defaultValue);
                System.out.println("===== @Default Processor field " + field.getName() +
                        " set to: " + defaultValue + " =====");
            }

        } catch (Exception e) {
            System.out.println("===== @Default Processor failed to process field '" +
                    field.getName() + "': " + e.getMessage() + " =====");
        }
    }

    private static Object determineFieldDefault(Field field, String classDefaultValue, Class<?> classDefaultType) {
        Default fieldAnnotation = field.getAnnotation(Default.class);
        Class<?> fieldType = field.getType();

        // Приоритет 1: Аннотация на поле
        if (fieldAnnotation != null) {
            String fieldValue = fieldAnnotation.value();
            Class<?> fieldDefaultType = fieldAnnotation.type();

            ValueContext context = new ValueContext(fieldType, fieldValue,
                    fieldDefaultType != Void.class ? fieldDefaultType : fieldType);

            return createValue(context);
        }

        // Приоритет 2: Аннотация на классе (только для ненастроенных полей)
        if (!classDefaultValue.isEmpty() || classDefaultType != Void.class) {
            ValueContext context = new ValueContext(fieldType, classDefaultValue,
                    classDefaultType != Void.class ? classDefaultType : fieldType);

            return createValue(context);
        }

        return null;
    }

    private static Object createValue(ValueContext context) {
        Class<?> targetType = context.getTargetType();

        DefaultValueCreator creator = VALUE_CREATORS.get(targetType);
        if (creator == null) {
            creator = VALUE_CREATORS.get(Object.class);
        }

        return creator.createValue(context);
    }

    public static void registerValueCreator(Class<?> type, DefaultValueCreator creator) {
        VALUE_CREATORS.put(type, creator);
    }
}