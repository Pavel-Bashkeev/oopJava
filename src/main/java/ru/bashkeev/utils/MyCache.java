package ru.bashkeev.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class MyCache<T> {
    private static MyCache<?>                  instance;
    private final  Map<Integer, CacheEntry<?>> cache = new HashMap<>();

    private MyCache() {
    }

    public static <T> MyCache<T> getInstance() {
        if (instance == null) {
            instance = new MyCache<T>();
        }

        @SuppressWarnings("unchecked")
        MyCache<T> result = (MyCache<T>) instance;
        return result;
    }

    public T get(Object key, Supplier<T> valueSupplier) {
        int cacheKey    = System.identityHashCode(key);
        int currentHash = key.hashCode();

        @SuppressWarnings("unchecked")
        CacheEntry<T> entry = (CacheEntry<T>) cache.get(cacheKey);

        if (entry != null && entry.objectHash == currentHash) {
            System.out.println("Значение взято из кэша для: " + key);
            return entry.value;
        }

        T value = valueSupplier.get();
        cache.put(cacheKey, new CacheEntry<>(value, currentHash));
        System.out.println("Вычислено новое значение для: " + key);

        return value;
    }

    public void invalidate(Object key) {
        int cacheKey = System.identityHashCode(key);
        cache.remove(cacheKey);
    }

    public void clear() {
        cache.clear();
    }

    public int size() {
        return cache.size();
    }

    private record CacheEntry<T>(T value, int objectHash) {
    }
}