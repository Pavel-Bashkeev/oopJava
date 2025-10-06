package ru.bashkeev.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValueSource {
    int[] ints() default {};
    String[] strings() default {};
    double[] doubles() default {};
    boolean[] booleans() default {};
}
