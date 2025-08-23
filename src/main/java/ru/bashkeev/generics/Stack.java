package ru.bashkeev.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.EmptyStackException;

public class Stack<T> {
    private final List<T> elements;

    public Stack() {
        this.elements = new ArrayList<>();
    }

    public void push(T item) {
        elements.add(item);
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return elements.removeLast();
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return elements.getLast();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public int size() {
        return elements.size();
    }
}