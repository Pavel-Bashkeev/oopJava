package ru.bashkeev.processor;

import ru.bashkeev.annotation.Cache;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class CacheProcessor {
    private static final Map<String, Object> cache = new HashMap<>();

    public static void cache(Object... objects) {
        System.out.println("=== @Cache Setup Processor ===");

        for (Object obj : objects) {
            Class<?> cls = obj.getClass();

            if (!cls.isAnnotationPresent(Cache.class)) {
                System.out.println("=== Class '" + cls.getSimpleName() + "' has no @Cache annotation - skipping ===");
                continue;
            }

            Cache    cacheAnnotation = cls.getAnnotation(Cache.class);
            String[] cachedMethods   = cacheAnnotation.value();

            System.out.println("=== Setting up cache for class: " + cls.getSimpleName() + " ===");
            System.out.println("=== Cached methods: " +
                    (cachedMethods.length == 0 ? "ALL" : Arrays.toString(cachedMethods)) + " ===");
        }
        System.out.println();
    }

    public static Object executeWithCache(Object obj, String methodName, Object... params)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("=== @Cache Execution Processor ===");
        Class<?> cls = obj.getClass();

        if (!cls.isAnnotationPresent(Cache.class)) {
            Method method = findMethod(cls, methodName, params);
            System.out.println("=== No @Cache annotation - executing without cache ===");
            return method.invoke(obj, params);
        }

        Cache    cacheAnnotation = cls.getAnnotation(Cache.class);
        String[] cachedMethods   = cacheAnnotation.value();

        if (!shouldCacheMethod(methodName, cachedMethods)) {
            Method method = findMethod(cls, methodName, params);
            System.out.println("=== Method '" + methodName + "' is not in cached list - executing without cache ===");
            return method.invoke(obj, params);
        }

        String cacheKey = createCacheKey(obj, methodName, params);
        System.out.println("=== Cache key: '" + cacheKey + "' ===");
        System.out.println("=== Cached methods config: " +
                (cachedMethods.length == 0 ? "ALL" : Arrays.toString(cachedMethods)) + " ===");

        if (cache.containsKey(cacheKey)) {
            System.out.println("=== Cache HIT for method '" + methodName + "' ===");
            return cache.get(cacheKey);
        }

        System.out.println("=== Cache MISS - executing method '" + methodName + "' ===");
        Method method = findMethod(cls, methodName, params);
        Object result = method.invoke(obj, params);
        cache.put(cacheKey, result);
        System.out.println("=== Result saved in cache ===");

        return result;
    }

    private static boolean shouldCacheMethod(String methodName, String[] cachedMethods) {
        if (cachedMethods.length == 0) {
            return true;
        }

        return Arrays.asList(cachedMethods).contains(methodName);
    }

    private static Method findMethod(Class<?> cls, String methodName, Object... params)
            throws NoSuchMethodException {
        Class<?>[] paramTypes = new Class[params.length];
        for (int i = 0; i < params.length; i++) {
            paramTypes[i] = params[i].getClass();
        }
        return cls.getMethod(methodName, paramTypes);
    }

    private static String createCacheKey(Object obj, String methodName, Object[] params) {
        StringBuilder key = new StringBuilder();
        key.append(obj.getClass().getSimpleName())
                .append(".")
                .append(methodName)
                .append("(");

        for (int i = 0; i < params.length; i++) {
            if (i > 0) key.append(",");
            key.append(params[i]);
        }
        key.append(")");

        return key.toString();
    }


    public static void clearCache() {
        cache.clear();
        System.out.println("=== Cache cleared ===");
    }

    public static void printCacheStats() {
        System.out.println("=== Cache Statistics ===");
        System.out.println("=== Total entries: " + cache.size() + " ===");
        cache.forEach((key, value) ->
                System.out.println("=== " + key + " -> " + value + " ==="));
    }

    public static int getCacheSize() {
        return cache.size();
    }
}
