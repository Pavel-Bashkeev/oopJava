package oop.hw.classes.chapterTwo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AutomaticGunTest {

    @Test
    void testDefaultConstructor() {
        AutomaticGun gun = new AutomaticGun();
        assertEquals(30, gun.getMaxCartridge());
        assertEquals(30, gun.getFireSpeed());
        assertEquals(0, gun.ammo());
    }

    @Test
    void testConstructorWithMaxCartridge() {
        AutomaticGun gun = new AutomaticGun(40);
        assertEquals(40, gun.getMaxCartridge());
        assertEquals(20, gun.getFireSpeed()); // половина от 40
    }

    @Test
    void testConstructorWithMaxCartridgeAndFireRate() {
        AutomaticGun gun = new AutomaticGun(50, 10);
        assertEquals(50, gun.getMaxCartridge());
        assertEquals(10, gun.getFireSpeed());
    }

    @Test
    void testShootSingleTime() {
        AutomaticGun gun = new AutomaticGun(30, 3);
        gun.reload(10);

        String result = gun.shoot();
        int lineCount = result.split("\n").length;
        assertEquals(3, lineCount);
        assertEquals(7, gun.ammo()); // 10 - 3 = 7
    }

    @Test
    void testShootForSeconds() {
        AutomaticGun gun = new AutomaticGun(100, 5);
        gun.reload(20);

        String result = gun.shootForSeconds(3);
        int lineCount = result.split("\n").length;
        assertEquals(15, lineCount); // 5 * 3 = 15
        assertEquals(5, gun.ammo()); // 20 - 15 = 5
    }

    @Test
    void testShootWhenNotReady() {
        AutomaticGun gun = new AutomaticGun(30, 10);
        assertEquals("Клац!", gun.shoot());
    }

    @Test
    void testShootForSecondsWhenNotEnoughAmmo() {
        AutomaticGun gun = new AutomaticGun(30, 10);
        gun.reload(5);

        String result = gun.shootForSeconds(1);
        int lineCount = result.split("\n").length;
        assertEquals(5, lineCount); // только 5 выстрелов вместо 10
        assertEquals(0, gun.ammo());
    }

    @Test
    void testToString() {
        AutomaticGun gun = new AutomaticGun(30, 10);
        gun.reload(15);
        assertTrue(gun.toString().contains("Автомат с 15 патронами, скорострельность 10/сек"));
    }
}