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

        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                getAllInterfaces(target.getClass()),
                new CachingInvocationHandler(target)
        );
    }

    private static Class<?>[] getAllInterfaces(Class<?> clazz) {
        Set<Class<?>> interfaces = new HashSet<>();
        Class<?>      current    = clazz;

        while (current != null && current != Object.class) {
            Collections.addAll(interfaces, current.getInterfaces());
            current = current.getSuperclass();
        }

        return interfaces.toArray(new Class<?>[0]);
    }

    private static class CachingInvocationHandler implements InvocationHandler {
        private final Object                     target;
        private final Map<MethodKey, CacheEntry> cache                  = new ConcurrentHashMap<>();
        private final Set<String>                stateModifyingPrefixes = Set.of("set", "add", "remove", "put", "delete");
        private final Set<String>                stateModifyingMethods  = Set.of("clear");

        public CachingInvocationHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getDeclaringClass() == Object.class) {
                return handleObjectMethod(proxy, method, args);
            }

            if (!shouldCache(method, args)) {
                return method.invoke(target, args);
            }

            MethodKey key = new MethodKey(method, args);

            CacheEntry cached = cache.get(key);
            if (cached != null) {
                return cached.value;
            }

            Object result = method.invoke(target, args);
            cache.put(key, new CacheEntry(result));

            return result;
        }

        private Object handleObjectMethod(Object proxy, Method method, Object[] args) {
            String methodName = method.getName();
            return switch (methodName) {
                case "toString" -> "CachedProxy@" + Integer.toHexString(System.identityHashCode(proxy)) +
                        "[" + target.toString() + "]";
                case "hashCode" -> System.identityHashCode(proxy);
                case "equals" -> proxy == args[0];
                default -> throw new UnsupportedOperationException("Object method not supported: " + methodName);
            };
        }

        private boolean shouldCache(Method method, Object[] args) {
            if (method.getReturnType() == void.class) {
                return false;
            }

            if (isStateModifyingMethod(method)) {
                cache.clear();
                return false;
            }

            return true;
        }

        private boolean isStateModifyingMethod(Method method) {
            String methodName = method.getName();

            for (String prefix : stateModifyingPrefixes) {
                if (methodName.startsWith(prefix)) {
                    return true;
                }
            }

            return stateModifyingMethods.contains(methodName);
        }
    }

    private static class MethodKey {
        private final Method   method;
        private final Object[] args;
        private final int      hashCode;

        public MethodKey(Method method, Object[] args) {
            this.method   = method;
            this.args     = args != null ? args.clone() : null;
            this.hashCode = computeHashCode();
        }

        private int computeHashCode() {
            int result = method.hashCode();
            if (args != null) {
                for (Object arg : args) {
                    result = 31 * result + (arg != null ? arg.hashCode() : 0);
                }
            }
            return result;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MethodKey)) return false;

            MethodKey that = (MethodKey) o;
            if (!method.equals(that.method)) return false;

            if (args == null) return that.args == null;
            if (that.args == null) return false;
            if (args.length != that.args.length) return false;

            for (int i = 0; i < args.length; i++) {
                if (!Objects.equals(args[i], that.args[i])) {
                    return false;
                }
            }

            return true;
        }

        @Override
        public int hashCode() {
            return hashCode;
        }
    }

    private static class CacheEntry {
        final Object value;
        final long   timestamp;

        CacheEntry(Object value) {
            this.value     = value;
            this.timestamp = System.currentTimeMillis();
        }
    }
}
