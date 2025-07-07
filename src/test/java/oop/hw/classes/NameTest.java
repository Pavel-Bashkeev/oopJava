package oop.hw.classes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void testEmptyName() {
        Name name = new Name();
        assertEquals("", name.toString());
    }

    @Test
    void testOnlyLastNameMethod() {
        Name name = new Name().onlyLastName("Петров");
        assertEquals("Петров", name.toString());
    }

    @Test
    void testOnlyMiddleNameMethod() {
        Name name = new Name().onlyMiddleName("Николаевич");
        assertEquals("Николаевич", name.toString());
    }

    @Test
    void testFirstNameFIOMethod() {
        Name name = new Name().firstNameInFIO("Александр", "Сергеевич", "Пушкин");
        assertEquals("Александр Сергеевич Пушкин", name.toString());
    }

    @Test
    void testFirstNameAndLastNameMethod() {
        Name name = new Name().firstNameAndLastName("Христофор", "Бонифатьевич");
        assertEquals("Христофор Бонифатьевич", name.toString());
    }

    @Test
    void testFirstNameAndMiddleNameMethod() {
        Name name = new Name().firstNameAndMiddleName("Христофор", "Сергеевич");
        assertEquals("Христофор Сергеевич", name.toString());
    }
}