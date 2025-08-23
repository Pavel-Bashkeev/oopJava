package ru.bashkeev.utils;

@FunctionalInterface
public interface Provider<R> {
    R get();
}
