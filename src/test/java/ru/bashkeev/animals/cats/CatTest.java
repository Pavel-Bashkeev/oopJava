package ru.bashkeev.animals.cats;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CatTest {
    @Test
    void testConstructor() {
        Cat cat = new Cat("Барсик");
        assertEquals("Барсик", cat.getNickName());
    }

    @Test
    void testEmptyName() {
        Cat cat = new Cat("");
        assertEquals("Безымянный", cat.getNickName());
    }

    @Test
    void testNullName() {
        Cat cat = new Cat(null);
        assertEquals("Безымянный", cat.getNickName());
    }

    @Test
    void testToString() {
        Cat cat = new Cat("Барсик");
        assertEquals("Кот: Барсик", cat.toString());
    }

    @Test
    void testMultipleMeow() {
        Cat cat = new Cat("Барсик");
        assertEquals("Барсик: мяу-мяу-мяу!", cat.meow(3));
    }

    @Test
    void testZeroMeow() {
        Cat cat = new Cat("Барсик");
        assertEquals("Барсик: молчит или спит!", cat.meow(0));
    }
}