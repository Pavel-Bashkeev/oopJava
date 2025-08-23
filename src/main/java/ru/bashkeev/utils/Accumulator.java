package ru.bashkeev.utils;

@FunctionalInterface
public interface Accumulator<R, T> {
    void accept(R result, T item);
}
