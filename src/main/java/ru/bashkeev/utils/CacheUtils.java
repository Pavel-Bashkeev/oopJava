package ru.bashkeev.utils;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CacheUtils {

    @SuppressWarnings("unchecked")
    public static <T> T cache(T target) {
        if (target == null) {
            throw new IllegalArgumentException("Target object cannot be null");
        }

        Class<?> targetClass = target.getClass();

        if (targetClass.isInterface()) {
            return (T) Proxy.newProxyInstance(
                    targetClass.getClassLoader(),
                    new Class<?>[]{targetClass},
                    new CachingInvocationHandler(target)
            );
        } else {
            return (T) Proxy.newProxyInstance(
                    targetClass.getClassLoader(),
                    getAllPublicMethods(targetClass),
                    new CachingInvocationHandler(target)
            );
        }
    }

    private static Class<?>[] getAllPublicMethods(Class<?> clazz) {
        Set<Class<?>> interfaces = new HashSet<>();
        getAllInterfaces(clazz, interfaces);
        return interfaces.toArray(new Class<?>[0]);
    }

    private static void getAllInterfaces(Class<?> clazz, Set<Class<?>> interfaces) {
        if (clazz == null || clazz == Object.class) {
            return;
        }

        Collections.addAll(interfaces, clazz.getInterfaces());

        getAllInterfaces(clazz.getSuperclass(), interfaces);
    }

    private static class CachingInvocationHandler implements InvocationHandler {
        private final Object target;
        private final Map<Method, CacheEntry> cache = new ConcurrentHashMap<>();
        private volatile long lastStateCheck;
        private volatile int stateHash;

        public CachingInvocationHandler(Object target) {
            this.target = target;
            this.stateHash = computeStateHash();
            this.lastStateCheck = System.currentTimeMillis();
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (!shouldCache(method, args)) {
                return method.invoke(target, args);
            }

            if (hasStateChanged()) {
                cache.clear();
                stateHash = computeStateHash();
                lastStateCheck = System.currentTimeMillis();
            }

            CacheEntry cached = cache.get(method);
            if (cached != null) {
                return cached.value;
            }

            Object result = method.invoke(target, args);
            cache.put(method, new CacheEntry(result));

            return result;
        }

        private boolean shouldCache(Method method, Object[] args) {
            if (args != null && args.length > 0) {
                return false;
            }

            if (method.getDeclaringClass() == Object.class &&
                    !method.getName().equals("toString")) {
                return false;
            }

            if (isStateModifyingMethod(method)) {
                return false;
            }

            return true;
        }

        private boolean isStateModifyingMethod(Method method) {
            String methodName = method.getName();
            return methodName.startsWith("set") ||
                    methodName.startsWith("add") ||
                    methodName.startsWith("remove") ||
                    methodName.startsWith("put") ||
                    methodName.startsWith("delete") ||
                    methodName.equals("clear");
        }

        private boolean hasStateChanged() {
            return computeStateHash() != stateHash;
        }

        private int computeStateHash() {
            try {
                int hash = 17;
                Class<?> clazz = target.getClass();

                while (clazz != null && clazz != Object.class) {
                    for (Field field : clazz.getDeclaredFields()) {
                        if (Modifier.isStatic(field.getModifiers()) ||
                                Modifier.isFinal(field.getModifiers())) {
                            continue;
                        }

                        field.setAccessible(true);
                        Object value = field.get(target);
                        hash = 31 * hash + (value != null ? value.hashCode() : 0);
                    }
                    clazz = clazz.getSuperclass();
                }

                return hash;
            } catch (IllegalAccessException e) {
                return System.identityHashCode(target);
            }
        }
    }

    private static class CacheEntry {
        final Object value;
        final long timestamp;

        CacheEntry(Object value) {
            this.value = value;
            this.timestamp = System.currentTimeMillis();
        }
    }
}
