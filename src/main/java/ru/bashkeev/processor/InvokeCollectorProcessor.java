package ru.bashkeev.processor;

import ru.bashkeev.annotation.Invoke;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class InvokeCollectorProcessor {
    public static Map<String, Object> collect(Class<?>... classes) {
        System.out.println("===== @InvokeCollectorProcessor Collector =====");
        Map<String, Object> results = new HashMap<>();

        for (Class<?> cls : classes) {
            processStaticMethods(cls, results);

            try {
                Object instance = cls.getDeclaredConstructor().newInstance();
                processInstanceMethods(instance, results);
            } catch (Exception ex) {
                System.out.println("===== @InvokeCollectorProcessor Cannot create instance for " + cls.getSimpleName() + ": " + ex.getMessage() + " =====");
            }
        }
        System.out.println("===== @InvokeCollectorProcessor Collector =====\n\n");
        return results;
    }

    private static void processStaticMethods(Class<?> cls, Map<String, Object> results) {
        for (Method method : cls.getDeclaredMethods()) {
            if (isValidInvokeMethod(method) && isStatic(method)) {
                invokeMethod(null, method, results);
            }
        }
    }

    private static void processInstanceMethods(Object instance, Map<String, Object> results) {
        for (Method method : instance.getClass().getDeclaredMethods()) {
            if (isValidInvokeMethod(method) && !isStatic(method)) {
                invokeMethod(instance, method, results);
            }
        }
    }

    private static boolean isValidInvokeMethod(Method method) {
        return method.isAnnotationPresent(Invoke.class) && method.getParameterCount() == 0 && method.getReturnType() != void.class;
    }

    private static boolean isStatic(Method method) {
        return Modifier.isStatic(method.getModifiers());
    }

    private static void invokeMethod(Object instance, Method method, Map<String, Object> results) {
        try {
            method.setAccessible(true);
            String methodKey = method.getDeclaringClass().getName() + "." + method.getName();
            Object result    = method.invoke(instance);
            results.put(methodKey, result);
            System.out.println("===== @InvokeCollectorProcessor Invoked '" + methodKey + "' = '" + result + "'");
        } catch (Exception ex) {
            System.out.println("===== @InvokeCollectorProcessor invokeMethod -> Exception: " + ex.getMessage());
        }
    }
}
