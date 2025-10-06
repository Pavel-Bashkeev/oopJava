package ru.bashkeev.processor;

import ru.bashkeev.annotation.Default;

import java.lang.reflect.Field;

public class DefaultProcessor {
    public static void process(Object obj) throws Exception {
        System.out.println("===== @Default Processor =====");
        Class<?> cls = obj.getClass();

        if (cls.isAnnotationPresent(Default.class)) {
            Default clsAnnotation = cls.getAnnotation(Default.class);
            System.out.println("===== @Default Processor have default type " + clsAnnotation.value().getSimpleName() + " =====");
        }

        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Default.class)) {
                Default fieldAnnotation = field.getAnnotation(Default.class);
                field.setAccessible(true);

                Object dftValue = createDftValue(fieldAnnotation.value());
                field.set(obj, dftValue);

                System.out.println("===== @Default Processor field " + field.getName() + " has " + dftValue + " =====");
            }
        }
        System.out.println("===== @Default Processor =====\n\n\n");
    }

    private static Object createDftValue(Class<?> type) {
        if (type == String.class) return "dft";
        if (type == Integer.class) return 0;
        if (type == Double.class) return 0.0;
        if (type == Boolean.class) return false;
        if (type == Long.class) return 0L;
        if (type == Float.class) return 0.0F;
        try {
            return type.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }
    }
}
