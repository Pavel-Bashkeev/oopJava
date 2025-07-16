package oop.hw.classes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class HumanTest {

    @Test
    void testCleopatra() {
        Name  name  = new Name("Клеопатра");
        Human human = new Human(name, 152);

        assertEquals("Человек с именем Клеопатра и ростом 152", human.toString());
    }

    @Test
    void testPushkin() {
        Name  name  = new Name("Пушкин", "Александр", "Сергеевич");
        Human human = new Human(name, 167);

        assertEquals("Человек с именем Пушкин Александр Сергеевич и ростом 167", human.toString());
    }

    @Test
    void testMayakovsky() {
        Name  name  = new Name("Маяковский", "Владимир", null);
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
        Name  name  = new Name("Маяковский", "Владимир");
        Human human = new Human(name);

        assertEquals("Человек с именем Маяковский Владимир", human.toString());
    }

    @Test
    void testHumanWithParent() {
        Name nameIvan   = new Name("Чудов", "Иван");
        Name namePetrov = new Name("Чудов", "Петр");
        Name nameBoris  = new Name("Борис");

        Human personIvan  = new Human(nameIvan);
        Human personPetr  = new Human(namePetrov, personIvan);
        Human personBoris = new Human(nameBoris, personPetr);

        assertEquals("Человек с именем Чудов Иван", personIvan.toString());
        assertEquals("Человек с именем Чудов Петр Иванович", personPetr.toString());
        assertEquals("Человек с именем Чудов Борис Петрович", personBoris.toString());
    }

    @Test
    void testHumanFamilyTree() {
        Name nameIvan         = new Name("Иван");
        Name namePetr         = new Name("Петров", "Петр");
        Name nameBoris        = new Name("Борис");
        Name nameGrandfather1 = new Name("Иван");
        Name nameGrandfather2 = new Name("Борисов", "Иван");

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
        Human greatGrandfather = new Human(new Name("Смирнов", "Иван"));
        Human grandfather = new Human(new Name("Иван"), greatGrandfather);
        Human father = new Human(new Name("Петр"), grandfather);
        Human son = new Human(new Name("Алексей"), father);

        assertEquals("Смирнов", father.getLastName());
        assertEquals("Смирнов", son.getLastName());
        assertEquals("Петрович", son.getMiddleName());

        assertEquals("Человек с именем Смирнов Иван Иванович", grandfather.toString());
        assertEquals("Человек с именем Смирнов Петр Иванович", father.toString());
        assertEquals("Человек с именем Смирнов Алексей Петрович", son.toString());
    }
}