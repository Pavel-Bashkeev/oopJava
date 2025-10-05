package ru.bashkeev.processor;

import ru.bashkeev.annotation.Cache;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CacheProcessor {
    private static final Map<String, Object> cache = new HashMap<>();

    public static Object executeWithCache(Object obj, String methodName, Object... params) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("=== @Cache Processor ===");
        Class<?> cls = obj.getClass();

        if (!cls.isAnnotationPresent(Cache.class)) {
            Method method = findMethod(cls, methodName, params);
            return method.invoke(obj, params);
        }

        Cache    cacheAnnotation = cls.getAnnotation(Cache.class);
        String[] cacheGroups     = cacheAnnotation.value();

        String cacheKey = createCacheKey(obj, methodName, params, cacheGroups);
        System.out.println("=== @Cache Processor: Key cache '" + cacheKey + "' ====");
        System.out.println("=== @Cache Processor: Groups cache '" + Arrays.toString(cacheGroups) + "' ====");

        if (cache.containsKey(cacheKey)) {
            System.out.println("=== @Cache Processor: Cache have data ====");
            return cache.get(cacheKey);
        }

        System.out.println("=== @Cache Processor: We carry out the method '" + methodName + "' ====");
        Method method = findMethod(cls, methodName, params);
        Object result = method.invoke(obj, params);
        cache.put(cacheKey, result);
        System.out.println("=== @Cache Processor: result save in cache ====");

        return result;
    }

    private static Method findMethod(Class<?> cls, String methodName, Object... params) throws NoSuchMethodException {
        Class<?>[] paramTypes = new Class[params.length];
        for (int i = 0; i < params.length; i++) {
            paramTypes[i] = params[i].getClass();
        }
        return cls.getMethod(methodName, paramTypes);
    }

    private static String createCacheKey(Object obj, String methodName, Object[] params, String[] groups) throws NoSuchMethodException {
        StringBuilder key = new StringBuilder();
        key.append(obj.getClass().getSimpleName())
                .append(".")
                .append(methodName)
                .append("_");

        for (Object param : params) {
            key.append(param.toString());
        }

        if (groups != null && groups.length > 0) {
            key.append("group_").append(groups[0]);
        }

        return key.toString();
    }
}
