package oop.hw.classes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {

    @Test
    void testFullName() {
        Name name = new Name("Иванов", "Иван", "Иванович");
        assertEquals("Иванов Иван Иванович", name.toString());
    }

    @Test
    void testWithoutMiddleName() {
        Name name = new Name("Пушкин", "Александр");
        assertEquals("Пушкин Александр", name.toString());
    }

    @Test
    void testOnlyFirstName() {
        Name name = new Name("Клеопатра");
        assertEquals("Клеопатра", name.toString());
    }

    @Test
    void testOnlyLastName() {
        Name name = new Name("Иванов", null, null);
        assertEquals("Иванов", name.toString());
    }

    @Test
    void testOnlyMiddleName() {
        Name name = new Name(null, null, "Сергеевич");
        assertEquals("Сергеевич", name.toString());
    }

    @Test
    void testEmptyNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Name(null, null, null));
    }

    @Test
    void testEmptyStringsAreTreatedAsMissing() {
        assertThrows(IllegalArgumentException.class, () -> new Name("", "", ""));
    }

    @Test
    void testMixedEmptyAndValid() {
        Name name = new Name("", "Анна", "");
        assertEquals("Анна", name.toString());
    }
}
