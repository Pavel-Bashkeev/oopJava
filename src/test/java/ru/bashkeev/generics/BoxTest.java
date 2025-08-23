package ru.bashkeev.generics;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoxTest {

    @Test
    public void testBoxInitiallyEmpty() {
        Box<String> box = new Box<>();
        assertTrue(box.isEmpty(), "Коробка должна быть пустой после создания");
    }

    @Test
    public void testPutAndGet() {
        Box<Integer> box = new Box<>();
        box.put(42);

        assertFalse(box.isEmpty(), "Коробка не должна быть пустой после добавления значения");

        Integer value = box.get();
        assertEquals(42, value, "Должно вернуться то же значение, которое было положено");

        assertTrue(box.isEmpty(), "Коробка должна быть пустой после извлечения значения");
    }

    @Test
    public void testGetSetsValueToNull() {
        Box<String> box = new Box<>();
        box.put("test");

        String value1 = box.get();
        String value2 = box.get(); // Должен вернуть null

        assertEquals("test", value1);
        assertNull(value2, "Повторный get() должен возвращать null");
    }

    @Test
    public void testPutWhenNotEmptyThrowsException() {
        Box<Double> box = new Box<>();
        box.put(3.14);

        AssertionError exception = assertThrows(AssertionError.class, () -> {
            box.put(2.71);
        });

        assertEquals("Коробка занята", exception.getMessage());
    }

    @Test
    public void testGetFromEmptyBox() {
        Box<String> box = new Box<>();

        assertTrue(box.isEmpty());
        assertNull(box.get(), "get() из пустой коробки должен возвращать null");
    }

    @Test
    public void testBoxWithDifferentTypes() {
        // Тестирование с Integer
        Box<Integer> intBox = new Box<>();
        intBox.put(100);
        assertEquals(100, intBox.get());

        // Тестирование с String
        Box<String> stringBox = new Box<>();
        stringBox.put("hello");
        assertEquals("hello", stringBox.get());

        // Тестирование с пользовательским типом
        Box<Person> personBox = new Box<>();
        Person person = new Person("John");
        personBox.put(person);
        assertEquals(person, personBox.get());
    }

    @Test
    public void testMultipleOperations() {
        Box<Integer> box = new Box<>();

        // put → get → put → get
        box.put(1);
        assertEquals(1, box.get());

        box.put(2);
        assertEquals(2, box.get());

        assertTrue(box.isEmpty());
    }

    // Вспомогательный класс для тестирования
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
    }
}