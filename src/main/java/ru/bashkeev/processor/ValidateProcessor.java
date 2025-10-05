package ru.bashkeev.processor;

import ru.bashkeev.annotation.Validate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidateProcessor {
    public static ValidationResult processor(Object obj) throws IllegalAccessException {
        System.out.println("===== @Validate Processor =====");

        ValidationResult result = new ValidationResult();
        Class<?> cls = obj.getClass();

        if (cls.isAnnotationPresent(Validate.class)) {
            Validate validateAnnotation = cls.getAnnotation(Validate.class);
            Class<?>[] allowedTypes = validateAnnotation.value();

            System.out.println("===== @Validate allowedTypes " + Arrays.toString(allowedTypes) + " =====");

            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object value = field.get(obj);
                    boolean typeAllowed = false;

                    for (Class<?> allowedType : allowedTypes) {
                        if (value != null && allowedType.isAssignableFrom(value.getClass())) {
                            typeAllowed = true;
                            break;
                        }
                    }

                    if (!typeAllowed && value != null) {
                        String error = "Field '" + field.getName() + "' of class '" + cls.getName() + "' has invalid value '" + value + "'";
                        result.addError(error);
                        System.out.println("===== @Validate allowedTypes error: " + error + " =====");
                    } else {
                        String error = "Field '" + field.getName() + "' of class '" + cls.getName() + "' has valid value '" + value + "'";
                    }
                } catch (IllegalAccessException e) {
                    result.addError("Field access error: " + e.getMessage());
                }
            }
        } else {
            result.addError("===== Class: '" + cls.getName() + "' @Validate annotation not found =====");
        }
        
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
    }
}
