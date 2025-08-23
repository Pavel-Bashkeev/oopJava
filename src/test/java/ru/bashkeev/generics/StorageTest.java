package ru.bashkeev.generics;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    @Test
    public void testStorageWithNullNumber() {
        Storage<Integer> storage = new Storage<>(null, 0);
        assertEquals(0, storage.getValue());
    }

    @Test
    public void testStorageWithNumber() {
        Storage<Integer> storage = new Storage<>(99, -1);
        assertEquals(99, storage.getValue());
    }

    @Test
    public void testStorageWithNullString() {
        Storage<String> storage = new Storage<>(null, "default");
        assertEquals("default", storage.getValue());
    }

    @Test
    public void testStorageWithString() {
        Storage<String> storage = new Storage<>("hello", "hello world");
        assertEquals("hello", storage.getValue());
    }

    @Test
    public void testStorageTypeSafety() {
        Storage<Integer> intStorage = new Storage<>(42, 0);
        Integer value = intStorage.getValue(); // Должен работать без приведения типов
        assertEquals(42, value);

        Storage<String> stringStorage = new Storage<>("test", "default");
        String strValue = stringStorage.getValue(); // Должен работать без приведения типов
        assertEquals("test", strValue);
    }

    @Test
    public void testStorageImmutability() {
        Integer originalValue = 100;
        Integer alternative = 0;
        Storage<Integer> storage = new Storage<>(originalValue, alternative);

        // Попытка изменить оригинальные значения не должна влиять на storage
        originalValue = 200;
        alternative = -1;

        assertEquals(100, storage.getValue()); // Должно остаться оригинальное значение
    }
}