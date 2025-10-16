package ru.bashkeev.processor;

import ru.bashkeev.annotation.Cache;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheProcessor {

    @SuppressWarnings("unchecked")
    public static <T> T cache(T target) {
        if (target == null) {
            throw new IllegalArgumentException("Target object cannot be null");
        }

        Class<?> targetClass = target.getClass();

        if (!targetClass.isAnnotationPresent(Cache.class)) {
            return target;
        }

        return (T) Proxy.newProxyInstance(
                targetClass.getClassLoader(),
                targetClass.getInterfaces(),
                new CachingInvocationHandler(target)
        );
    }

    private static class CachingInvocationHandler implements InvocationHandler {
        private final Object target;
        private final Map<String, Object> cache = new ConcurrentHashMap<>();
        private final Cache cacheAnnotation;
        private final String[] cachedMethods;

        public CachingInvocationHandler(Object target) {
            this.target = target;
            this.cacheAnnotation = target.getClass().getAnnotation(Cache.class);
            this.cachedMethods = cacheAnnotation.value();
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // Методы Object не кэшируем, сразу делегируем
            if (method.getDeclaringClass() == Object.class) {
                return method.invoke(target, args);
            }

            // Проверяем, нужно ли кэшировать этот метод
            if (!shouldCacheMethod(method.getName())) {
                return method.invoke(target, args);
            }

            // Создаем ключ кэша
            String cacheKey = createCacheKey(method.getName(), args);

            // Если есть в кэше - возвращаем из кэша
            if (cache.containsKey(cacheKey)) {
                System.out.println("=== Cache HIT for " + method.getName() + " ===");
                return cache.get(cacheKey);
            }

            // Иначе вызываем и кэшируем результат
            System.out.println("=== Cache MISS for " + method.getName() + " ===");
            Object result = method.invoke(target, args);
            cache.put(cacheKey, result);
            return result;
        }

        private boolean shouldCacheMethod(String methodName) {
            if (cachedMethods.length == 0) {
                return true;
            }
            return Arrays.asList(cachedMethods).contains(methodName);
        }

        private String createCacheKey(String methodName, Object[] args) {
            StringBuilder key = new StringBuilder();
            key.append(methodName);

            if (args != null && args.length > 0) {
                key.append("(");
                for (int i = 0; i < args.length; i++) {
                    if (i > 0) key.append(",");
                    key.append(args[i] != null ? args[i].toString() : "null");
                }
                key.append(")");
            }

            return key.toString();
        }
    }
}
