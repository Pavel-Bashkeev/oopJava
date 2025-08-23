package ru.bashkeev.generics;

public class Box<T> {
    private T value;

    public void put(T value) {
        if (this.value != null) {
            throw new AssertionError("Коробка занята");
        }

        this.value = value;
    }

    public T get() {
        T result = value;
        value = null;
        return result;
    }

    public boolean isEmpty() {
        return value == null;
    }
}
