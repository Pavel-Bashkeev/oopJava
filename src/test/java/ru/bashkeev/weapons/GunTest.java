package ru.bashkeev.weapons;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GunTest {

    @Test
    void testInitialCapacityAndAmmo() {
        Gun gun = new Gun(7);
        assertEquals(7, gun.getMaxCartridge());
        assertEquals(0, gun.ammo());
        assertFalse(gun.isReady());
    }

    @Test
    void testReloadPositive() {
        Gun gun = new Gun(5);
        int leftover = gun.reload(3);
        assertEquals(0, leftover);
        assertEquals(3, gun.ammo());
        assertTrue(gun.isReady());
    }

    @Test
    void testReloadOverCapacity() {
        Gun gun = new Gun(4);
        int leftover = gun.reload(6);
        assertEquals(2, leftover);
        assertEquals(4, gun.ammo());
    }

    @Test
    void testReloadNegativeThrows() {
        Gun gun = new Gun(5);
        assertThrows(IllegalArgumentException.class, () -> gun.reload(-1));
    }

    @Test
    void testUnload() {
        Gun gun = new Gun(6);
        gun.reload(5);
        int unloaded = gun.unload();
        assertEquals(5, unloaded);
        assertEquals(0, gun.ammo());
        assertFalse(gun.isReady());
    }

    @Test
    void testShootLoaded() {
        Gun gun = new Gun(3);
        gun.reload(2);
        assertEquals("Бах!", gun.shoot());
        assertEquals("Бах!", gun.shoot());
        assertEquals("Клац!", gun.shoot());
    }

    @Test
    void testShootEmpty() {
        Gun gun = new Gun(2);
        assertEquals("Клац!", gun.shoot());
    }

    @Test
    void testConstructorWithInvalidCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new Gun(0));
        assertThrows(IllegalArgumentException.class, () -> new Gun(-3));
    }

    @Test
    void testTask() {
        Gun gun = new Gun(7);
        gun.reload(3);

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            output.append(gun.shoot()).append(" ");
        }

        gun.reload(8); // only 7 allowed

        for (int i = 0; i < 2; i++) {
            output.append(gun.shoot()).append(" ");
        }

        gun.unload();
        output.append(gun.shoot());

        assertEquals("Бах! Бах! Бах! Клац! Клац! Бах! Бах! Клац!", output.toString().trim());
    }
}
