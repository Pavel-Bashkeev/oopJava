package ru.bashkeev.processor;

import ru.bashkeev.annotation.ToString;

import java.lang.reflect.Field;

public class ToStringProcessor {
    public static String process(Object obj) throws IllegalAccessException {
        System.out.println("===== @ToString Processor =====");

        StringBuilder sb  = new StringBuilder();
        Class<?>      cls = obj.getClass();

        if (cls.isAnnotationPresent(ToString.class)) {
            ToString clsAnnotation = cls.getAnnotation(ToString.class);
            if (clsAnnotation.value() == ToString.Value.NO) {
                String result = cls.getSimpleName() + "{ToString disabled}";
                System.out.println("===== @ToString Processor result: " + result + " =====");
                System.out.println("===== @ToString Processor =====\n\n");
                return result;
            }
        }

        sb.append(cls.getSimpleName()).append("{");
        Field[] fields     = cls.getDeclaredFields();
        boolean firstField = true;

        for (Field field : fields) {
            field.setAccessible(true);

            ToString fieldAnnotation = field.getAnnotation(ToString.class);

            if (fieldAnnotation != null && fieldAnnotation.value() == ToString.Value.NO) {
                continue;
            }

            if (fieldAnnotation == null || fieldAnnotation.value() == ToString.Value.YES) {
                if (!firstField) {
                    sb.append(", ");
                }
                sb.append(field.getName()).append("=").append(field.get(obj));
                firstField = false;
            }
        }

        sb.append("}");

        String result = sb.toString();
        System.out.println("===== @ToString Processor result: " + result + " =====");
        System.out.println("===== @ToString Processor =====\n\n");
        return result;
    }
}
