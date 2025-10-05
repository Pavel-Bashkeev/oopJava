package ru.bashkeev.processor;

import ru.bashkeev.annotation.Two;

public class TwoProcessor {
    public static void process(Object obj) {
        System.out.println("===== @Two processor =====");

        Class<?> cls = obj.getClass();
        if (cls.isAnnotationPresent(Two.class)) {
            Two twoAnnotation = cls.getAnnotation(Two.class);

            String first = twoAnnotation.first();
            int second = twoAnnotation.second();

            System.out.println("===== Class: '" + cls.getName() + "' has @Two annotation =====");
            System.out.println("===== first field: '" + first + "' =====");
            System.out.println("===== second field: '" + second + "' =====");
        } else {
            System.out.println("===== Class: '" + cls.getName() + "' @Two annotation not found =====");
        }
    }
}
