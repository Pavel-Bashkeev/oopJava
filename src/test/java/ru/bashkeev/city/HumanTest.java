package ru.bashkeev.city;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class HumanTest {

    @Test
    void testCleopatra() {
        Name  name  = Name.builder().firstName("Клеопатра").build();
        Human human = new Human(name, 152);

        assertEquals("Человек с именем Клеопатра и ростом 152", human.toString());
    }

    @Test
    void testPushkin() {
        Name  name  = Name.builder().lastName("Пушкин").firstName("Александр").middleName("Сергеевич").build();
        Human human = new Human(name, 167);

        assertEquals("Человек с именем Пушкин Александр Сергеевич и ростом 167", human.toString());
    }

    @Test
    void testMayakovsky() {
        Name  name  = Name.builder().lastName("Маяковский").firstName("Владимир").build();
        Human human = new Human(name, 189);

        assertEquals("Человек с именем Маяковский Владимир и ростом 189", human.toString());
    }

    @Test
    void testStringNameConstructor() {
        Human human = new Human("Лев Толстой", 182);
        assertEquals("Человек с именем Лев Толстой и ростом 182", human.toString());
    }

    @Test
    void testHumanWithoutHeight() {
        Name  name  = Name.builder().lastName("Маяковский").firstName("Владимир").build();
        Human human = new Human(name);

        assertEquals("Человек с именем Маяковский Владимир", human.toString());
    }

    @Test
    void testHumanWithParent() {
        Name nameIvan   = Name.builder().lastName("Чудов").firstName("Иван").build();
        Name namePetrov = Name.builder().lastName("Чудов").firstName("Петр").build();
        Name nameBoris  = Name.builder().firstName("Борис").build();

        Human personIvan  = new Human(nameIvan);
        Human personPetr  = new Human(namePetrov, personIvan);
        Human personBoris = new Human(nameBoris, personPetr);

        assertEquals("Человек с именем Чудов Иван", personIvan.toString());
        assertEquals("Человек с именем Чудов Петр Иванович", personPetr.toString());
        assertEquals("Человек с именем Чудов Борис Петрович", personBoris.toString());
    }

    @Test
    void testHumanFamilyTree() {
        Name nameIvan         = Name.builder().firstName("Иван").build();
        Name namePetr         = Name.builder().lastName("Петров").firstName("Петр").build();
        Name nameBoris        = Name.builder().firstName("Борис").build();
        Name nameGrandfather1 = Name.builder().firstName("Иван").build();
        Name nameGrandfather2 = Name.builder().lastName("Борисов").firstName("Иван").build();

        Human grandfather2 = new Human(nameGrandfather2);
        Human ivan         = new Human(nameIvan, grandfather2);
        Human petr         = new Human(namePetr, ivan);
        Human boris        = new Human(nameBoris, petr);


        assertEquals("Человек с именем Борисов Иван Иванович", ivan.toString());
        assertEquals("Человек с именем Петров Петр Иванович", petr.toString());
        assertEquals("Человек с именем Петров Борис Петрович", boris.toString());

        assertNull(grandfather2.getParent());
        assertEquals(ivan, petr.getParent());
    }

    @Test
    void testMyFathersFather() {
        Name SmIvan = Name.builder().lastName("Смирнов").firstName("Иван").build();
        Name Ivan = Name.builder().firstName("Иван").build();
        Name Petr = Name.builder().firstName("Петр").build();
        Name Alex = Name.builder().firstName("Алексей").build();

        Human greatGrandfather = new Human(SmIvan);
        Human grandfather = new Human(Ivan, greatGrandfather);
        Human father = new Human(Petr, grandfather);
        Human son = new Human(Alex, father);

        assertEquals("Смирнов", father.getLastName());
        assertEquals("Смирнов", son.getLastName());
        assertEquals("Петрович", son.getMiddleName());

        assertEquals("Человек с именем Смирнов Иван Иванович", grandfather.toString());
        assertEquals("Человек с именем Смирнов Петр Иванович", father.toString());
        assertEquals("Человек с именем Смирнов Алексей Петрович", son.toString());
    }
}