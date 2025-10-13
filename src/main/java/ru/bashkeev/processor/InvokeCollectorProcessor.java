package ru.bashkeev.processor;

import ru.bashkeev.annotation.Invoke;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InvokeCollectorProcessor {

    public static class InvokeCollectorException extends RuntimeException {
        public InvokeCollectorException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class InstanceCreationException extends InvokeCollectorException {
        public InstanceCreationException(Class<?> clazz, Throwable cause) {
            super("Cannot create instance of " + clazz.getName() + ": " + cause.getMessage(), cause);
        }
    }

    public static class MethodInvocationException extends InvokeCollectorException {
        public MethodInvocationException(Method method, Throwable cause) {
            super("Failed to invoke method " + method.getDeclaringClass().getName() +
                    "." + method.getName() + ": " + getInvocationCauseMessage(cause), cause);
        }

        private static String getInvocationCauseMessage(Throwable cause) {
            return cause instanceof InvocationTargetException ?
                    cause.getCause().getMessage() : cause.getMessage();
        }
    }

    public static Map<String, Object> collect(Class<?>... classes) {
        System.out.println("===== @InvokeCollectorProcessor Collector =====");

        // Используем HashMap напрямую вместо Collectors.toMap() для поддержки null значений
        Map<String, Object> results = new HashMap<>();

        Arrays.stream(classes)
                .flatMap(InvokeCollectorProcessor::processClassMethods)
                .forEach(entry -> results.put(entry.getKey(), entry.getValue()));

        System.out.println("===== @InvokeCollectorProcessor Collector =====\n");
        return results;
    }

    private static Stream<Map.Entry<String, Object>> processClassMethods(Class<?> cls) {
        Stream<Map.Entry<String, Object>> staticMethods = Arrays.stream(cls.getDeclaredMethods())
                .filter(InvokeCollectorProcessor::isValidInvokeMethod)
                .filter(InvokeCollectorProcessor::isStatic)
                .map(method -> invokeMethod(null, method));

        Stream<Map.Entry<String, Object>> instanceMethods = createInstance(cls)
                .map(instance -> Arrays.stream(instance.getClass().getDeclaredMethods())
                        .filter(InvokeCollectorProcessor::isValidInvokeMethod)
                        .filter(method -> !isStatic(method))
                        .map(method -> invokeMethod(instance, method)))
                .orElse(Stream.empty());

        return Stream.concat(staticMethods, instanceMethods);
    }

    private static Optional<Object> createInstance(Class<?> cls) {
        try {
            return Optional.of(cls.getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            throw new InstanceCreationException(cls, e);
        }
    }

    private static Map.Entry<String, Object> invokeMethod(Object instance, Method method) {
        try {
            method.setAccessible(true);
            String methodKey = method.getDeclaringClass().getName() + "." + method.getName();
            Object result = method.invoke(instance);

            System.out.println("===== @InvokeCollectorProcessor Invoked '" + methodKey + "' = '" + result + "'");

            return new AbstractMap.SimpleEntry<>(methodKey, result);

        } catch (Exception e) {
            throw new MethodInvocationException(method, e);
        }
    }

    private static boolean isValidInvokeMethod(Method method) {
        return method.isAnnotationPresent(Invoke.class) &&
                method.getParameterCount() == 0 &&
                method.getReturnType() != void.class;
    }

    private static boolean isStatic(Method method) {
        return Modifier.isStatic(method.getModifiers());
    }
}