package ru.bashkeev.generics;

public class Storage<T> {
    private final T value;
    private final T alternative;

    public Storage(T value, T alternative) {
        this.value = value;
        this.alternative = alternative;
    }

    public T getValue() {
        return value != null ? value : alternative;
    }
}
