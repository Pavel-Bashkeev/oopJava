package ru.bashkeev.processor;

import ru.bashkeev.annotation.ToString;

import java.lang.reflect.Field;

public class ToStringProcessor {
    public static String process(Object obj) throws IllegalAccessException {
        System.out.println("===== @ToString Processor =====");

        StringBuilder sb = new StringBuilder();
        Class<?> cls =  obj.getClass();

        if (cls.isAnnotationPresent(ToString.class)) {
            ToString clsAnnotations = cls.getAnnotation(ToString.class);
            if (clsAnnotations.value() == ToString.Value.NO) {
                return cls.getSimpleName() + "{ToString disabled}";
            }
        }

        sb.append(cls.getSimpleName()).append("{");
        Field[] fields = cls.getDeclaredFields();
        boolean firstField = true;

        for (Field field : fields) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(ToString.class)) {
                ToString fieldAnnotation = field.getAnnotation(ToString.class);
                if (fieldAnnotation.value() == ToString.Value.YES) {
                    if (!firstField) sb.append(", ");
                    sb.append(field.getName()).append("=").append(field.get(obj));
                    firstField = false;
                }
            } else {
                if (!firstField) sb.append(", ");
                sb.append(field.getName()).append("=").append(field.get(obj));
            }
        }

        sb.append("}");
        return sb.toString();
    }
}
