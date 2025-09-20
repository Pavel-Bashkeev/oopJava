package ru.bashkeev.utils;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Iterator;

public class AdvancedLinkedList<T> implements Iterable<T> {

    private Node<T> head;
    private Node<T> tail;
    private int     size;
    private int     modCount;

    public AdvancedLinkedList() {
    }

    public static class Node<E> {
        public  E                     data;
        public  Node<E>               next;
        Node<E>               prev;
        AdvancedLinkedList<E> owner;

        private Node(E data, AdvancedLinkedList<E> owner) {
            this.data  = data;
            this.owner = owner;
        }

        public E getNodeData() {
            return data;
        }

        public void setNodeData(E data) {
            this.data = data;
        }
    }

    public Node<T> addFirst(T data) {
        Node<T> node = new Node<>(data, this);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head      = node;
        }

        size++;
        modCount++;
        return node;
    }

    public Node<T> addLast(T data) {
        Node<T> node = new Node<>(data, this);
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail      = node;
        }

        size++;
        modCount++;
        return node;
    }

    public Node<T> addAfter(Node<T> node, T data) {
        Objects.requireNonNull(node, "node must not be null");
        checkMember(node);

        if (node == tail) {
            return addLast(data);
        }

        Node<T> next    = node.next;
        Node<T> newNode = new Node<>(data, this);

        newNode.next = next;
        newNode.prev = node;
        node.next    = newNode;
        next.prev    = newNode;
        size++;
        modCount++;

        return newNode;
    }

    public Node<T> addBefore(Node<T> node, T data) {
        Objects.requireNonNull(node, "node must not be null");
        checkMember(node);

        if (node == head) {
            return addFirst(data);
        }

        Node<T> prev    = node.prev;
        Node<T> newNode = new Node<>(data, this);

        newNode.next = node;
        newNode.prev = prev;
        prev.next    = newNode;
        node.prev    = newNode;
        size++;
        modCount++;

        return newNode;
    }

    public T remove(Node<T> node) {
        Objects.requireNonNull(node, "node must not be null");
        checkMember(node);

        if (node == head && node == tail) {
            head = null;
            tail = null;
        } else if (node == head) {
            head      = node.next;
            if (head != null) {
                head.prev = null;
            }
        } else if (node == tail) {
            tail      = node.prev;
            if (tail != null) {
                tail.next = null;
            }
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        T value = node.data;

        node.data  = null;
        node.prev  = null;
        node.next  = null;
        node.owner = null;

        size--;
        modCount++;
        return value;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public Node<T> getNodeByIndex(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        if (index < size / 2) {
            Node<T> node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            Node<T> node = tail;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Node<T> node = head;
        while (node != null) {
            sb.append(node.data);
            node = node.next;
            if (node != null) sb.append(", ");
        }
        sb.append(']');
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private       Node<T> current     = head;
            private final int     expectedMod = modCount;

            private void checkMod() {
                if (modCount != expectedMod) {
                    throw new ConcurrentModificationException();
                }
            }

            @Override
            public boolean hasNext() {
                checkMod();
                return current != null;
            }

            @Override
            public T next() {
                checkMod();
                if (current == null) {
                    throw new NoSuchElementException();
                }

                T value = current.data;
                current = current.next;
                return value;
            }
        };
    }

    private void checkMember(Node<T> node) {
        if (node.owner != this) throw new IllegalArgumentException("node must own this member");
    }
}
