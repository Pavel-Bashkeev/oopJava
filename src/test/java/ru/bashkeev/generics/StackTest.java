package ru.bashkeev.generics;

import org.junit.jupiter.api.Test;
import java.util.EmptyStackException;
import static org.junit.jupiter.api.Assertions.*;

public class StackTest {

    @Test
    public void testEmptyStack() {
        Stack<String> stack = new Stack<>();
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    public void testPushAndPeek() {
        Stack<Integer> stack = new Stack<>();
        stack.push(42);

        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());
        assertEquals(42, stack.peek());
        assertEquals(1, stack.size());
        assertEquals(42, stack.peek());
    }

    @Test
    public void testPushAndPop() {
        Stack<String> stack = new Stack<>();
        stack.push("first");
        stack.push("second");

        assertEquals(2, stack.size());
        assertEquals("second", stack.pop());
        assertEquals(1, stack.size());
        assertEquals("first", stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testPopFromEmptyStack() {
        Stack<Double> stack = new Stack<>();
        assertThrows(EmptyStackException.class, stack::pop);
    }

    @Test
    public void testPeekFromEmptyStack() {
        Stack<Character> stack = new Stack<>();
        assertThrows(EmptyStackException.class, stack::peek);
    }

    @Test
    public void testMultipleOperations() {
        Stack<Integer> stack = new Stack<>();

        stack.push(10);
        assertEquals(10, stack.peek());

        stack.push(20);
        assertEquals(20, stack.pop());
        assertEquals(10, stack.pop());

        assertTrue(stack.isEmpty());
    }

    @Test
    public void testStackWithDifferentTypes() {
        Stack<Integer> intStack = new Stack<>();
        intStack.push(100);
        assertEquals(100, intStack.pop());

        Stack<String> stringStack = new Stack<>();
        stringStack.push("test");
        assertEquals("test", stringStack.pop());

        Stack<Person> personStack = new Stack<>();
        Person person = new Person("John");
        personStack.push(person);
        assertEquals(person, personStack.pop());
    }

    @Test
    public void testLargeStack() {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < 1000; i++) {
            stack.push(i);
        }

        assertEquals(1000, stack.size());
        assertEquals(999, stack.peek());

        // Извлекаем все элементы
        for (int i = 999; i >= 0; i--) {
            assertEquals(i, stack.pop());
        }

        assertTrue(stack.isEmpty());
    }

    @Test
    public void testStackOrder() {
        Stack<String> stack = new Stack<>();
        stack.push("a");
        stack.push("b");
        stack.push("c");

        assertEquals("c", stack.pop());
        assertEquals("b", stack.pop());
        assertEquals("a", stack.pop());
    }

    @Test
    public void testMixedOperations() {
        Stack<Integer> stack = new Stack<>();

        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.pop());

        stack.push(3);
        assertEquals(3, stack.peek());
        assertEquals(3, stack.pop());
        assertEquals(1, stack.pop());

        assertTrue(stack.isEmpty());
    }

    static class Person {
        private final String name;

        public Person(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Person person = (Person) obj;
            return name.equals(person.name);
        }

        @Override
        public String toString() {
            return name;
        }
    }
}