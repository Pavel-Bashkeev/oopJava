package ru.bashkeev.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class ValidationUtils {

    public static void validate(Object object, Class<?> testClass) throws ValidateException {
        List<Method> testMethods = ReflectionUtils.validationMethods(testClass);

        for (Method method : testMethods) {
            try {
                method.setAccessible(true);
                method.invoke(null, object);

            } catch (IllegalAccessException e) {
                throw new ValidateException("Нет доступа к методу валидации: " + method.getName(), e);
            } catch (InvocationTargetException e) {
                Throwable cause = e.getCause();
                if (cause instanceof ValidateException) {
                    throw (ValidateException) cause;
                } else {
                    String message = cause != null ? cause.getMessage() : "Неизвестная ошибка";
                    throw new ValidateException("Ошибка в методе " + method.getName() + ": " + message, cause);
                }
            } catch (IllegalArgumentException e) {
                throw new ValidateException("Неверные аргументы для метода " + method.getName(), e);
            }
        }
    }

    public static class ValidateException extends Exception {
        public ValidateException(String message) {
            super(message);
        }

        public ValidateException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
