package ru.bashkeev.utils;

@FunctionalInterface
public interface Reduction<T> {
    T apply(T l, T r);
}
