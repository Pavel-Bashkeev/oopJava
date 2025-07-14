package oop.hw.classes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GunTest {

    @Test
    void testConstructorWithCartridge() {
        Gun gun = new Gun(3);
        assertEquals(3, gun.getCountCartridge());
    }

    @Test
    void testDefaultConstructor() {
        Gun gun = new Gun();
        assertEquals(5, gun.getCountCartridge());
    }

    @Test
    void testShootWithCartridge() {
        Gun gun = new Gun(1);
        assertEquals("Бах!", gun.shoot());
        assertEquals(0, gun.getCountCartridge());
    }

    @Test
    void testShootWithoutCartridge() {
        Gun gun = new Gun(0);
        assertEquals("Клац!", gun.shoot());
        assertEquals(0, gun.getCountCartridge());
    }

    @Test
    void testMultipleShots() {
        Gun gun = new Gun(3);

        assertEquals("Бах!", gun.shoot());
        assertEquals("Бах!", gun.shoot());
        assertEquals("Бах!", gun.shoot());
        assertEquals("Клац!", gun.shoot());
        assertEquals("Клац!", gun.shoot());

        assertEquals(0, gun.getCountCartridge());
    }

    @Test
    void testNegativeCartridge() {
        Gun gun = new Gun(-5);
        assertEquals(0, gun.getCountCartridge());
    }

    @Test
    void testToString() {
        Gun gun = new Gun(3);
        assertEquals("Пистолет с 3 патронами", gun.toString());
    }

    @Test
    void testToStringOneBullet() {
        Gun gun = new Gun(1);
        assertEquals("Пистолет с 1 патроном", gun.toString());
    }
}