package ru.bashkeev.processor;

import ru.bashkeev.annotation.Validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidateProcessor {

    public static ValidationResult processor(Object obj) throws IllegalAccessException {
        System.out.println("===== @Validate Processor (with aliases) =====");

        ValidationResult result = new ValidationResult();
        Class<?> cls = obj.getClass();

        Validate validateAnnotation = findValidateAnnotation(cls);

        if (validateAnnotation == null) {
            String error = "Class: '" + cls.getName() + "' @Validate annotation or its alias not found";
            result.addError(error);
            System.out.println("===== " + error + " =====");
            return result;
        }

        Class<?>[] allowedTypes = validateAnnotation.value();
        System.out.println("===== @Validate allowedTypes " + Arrays.toString(allowedTypes) + " =====");

        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                boolean typeAllowed = false;

                if (value == null) {
                    System.out.println("===== Field '" + field.getName() + "' is null - skipping validation =====");
                    continue;
                }

                for (Class<?> allowedType : allowedTypes) {
                    if (allowedType.isAssignableFrom(value.getClass())) {
                        typeAllowed = true;
                        break;
                    }
                }

                if (!typeAllowed) {
                    String error = "Field '" + field.getName() + "' has invalid type '" +
                            value.getClass().getSimpleName() + "' (value: " + value + ")";
                    result.addError(error);
                    System.out.println("===== Validation error: " + error + " =====");
                } else {
                    System.out.println("===== Field '" + field.getName() + "' is valid: " + value + " =====");
                }

            } catch (IllegalAccessException e) {
                String error = "Field access error for '" + field.getName() + "': " + e.getMessage();
                result.addError(error);
                System.out.println("===== " + error + " =====");
            }
        }
        System.out.println("===== @Validate Processor (with aliases) end =====\n\n");
        return result;
    }

    public static class ValidationResult {
        private final List<String> errors = new ArrayList<>();

        public void addError(String error) {
            errors.add(error);
        }

        public boolean isValid() {
            return errors.isEmpty();
        }

        public List<String> getErrors() {
            return errors;
        }

        @Override
        public String toString() {
            return "ValidationResult{valid=" + isValid() + ", errors=" + errors + "}";
        }
    }

    private static Validate findValidateAnnotation(Class<?> cls) {
        if (cls.isAnnotationPresent(Validate.class)) {
            System.out.println("===== Found direct @Validate annotation =====");
            return cls.getAnnotation(Validate.class);
        }

        Annotation[] annotations = cls.getAnnotations();
        for (Annotation annotation : annotations) {
            Class<? extends Annotation> annotationType = annotation.annotationType();

            if (annotationType.isAnnotationPresent(Validate.class)) {
                System.out.println("===== Found alias annotation: " + annotationType.getSimpleName() + " =====");
                return annotationType.getAnnotation(Validate.class);
            }
        }

        return null;
    }
}
