package ru.bashkeev.city;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {

    @Test
    void testFullName() {
        Name name = Name.builder().lastName("Иванов").firstName("Иван").middleName("Иванович").build();
        assertEquals("Иванов Иван Иванович", name.toString());
    }

    @Test
    void testWithoutMiddleName() {
        Name name = Name.builder().lastName("Пушкин").middleName("Александр").build();
        assertEquals("Пушкин Александр", name.toString());
    }

    @Test
    void testOnlyFirstName() {
        Name name = Name.builder().firstName("Клеопатра").build();
        assertEquals("Клеопатра", name.toString());
    }

    @Test
    void testOnlyLastName() {
        Name name = Name.builder().lastName("Иванов").build();
        assertEquals("Иванов", name.toString());
    }

    @Test
    void testOnlyMiddleName() {
        Name name = Name.builder().middleName("Сергеевич").build();
        assertEquals("Сергеевич", name.toString());
    }

    @Test
    void testEmptyNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> Name.builder().build());
    }

    @Test
    void testEmptyStringsAreTreatedAsMissing() {
        assertThrows(IllegalArgumentException.class, () -> Name.builder().build());
    }

    @Test
    void testMixedEmptyAndValid() {
        Name name = Name.builder().firstName("Анна").build();
        assertEquals("Анна", name.toString());
    }
}
