package ru.bashkeev.processor;

import ru.bashkeev.annotation.Invoke;

import java.lang.reflect.Method;

public class InvokeProcessor {
    public static void processor(Object obj) {
        System.out.println("===== @Invoke Processor =====");

        Class<?> cls     = obj.getClass();
        Method[] methods = cls.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Invoke.class)) {
                try {
                    method.setAccessible(true);
                    System.out.println("===== @Invoke Method start " + method.getName() + " =====");
                    Object result = method.invoke(obj);
                    if (result != null) {
                        System.out.println("===== @Invoke Method end " + method.getName() + " =====");
                    }
                } catch (Exception e) {
                    System.out.println("===== @Invoke Method error " + method.getName() + ": " + e.getMessage() + " =====");
                }
            }
        }

        System.out.println("===== @Invoke Processor =====\n\n");
    }
}
