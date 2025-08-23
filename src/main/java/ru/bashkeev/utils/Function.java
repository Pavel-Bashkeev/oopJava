package ru.bashkeev.utils;

public interface Function<T, R> {
    R apply(T t);
}
