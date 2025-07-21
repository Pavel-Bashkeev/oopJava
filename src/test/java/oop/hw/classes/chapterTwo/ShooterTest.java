package oop.hw.classes.chapterTwo;

import oop.hw.classes.shooter.Shooter;
import oop.hw.classes.weapon.Gun;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShooterTest {

    @Test
    void testCreationWithoutWeapon() {
        Shooter shooter = new Shooter("Вася");
        assertEquals("Вася", shooter.getName());
        assertNull(shooter.getWeapon());
    }

    @Test
    void testCreationWithWeapon() {
        Shooter shooter = new Shooter("Петя", new Gun(6));
        assertEquals("Петя", shooter.getName());
        assertNotNull(shooter.getWeapon());
    }

    @Test
    void testShootWithoutWeapon() {
        Shooter shooter = new Shooter("Безоружный");
        assertEquals("Безоружный: не могу участвовать в перестрелке",
                shooter.shoot());
    }

    @Test
    void testShootWithWeapon() {
        Shooter shooter = new Shooter("Стрелок", new Gun(6, 1));
        String result = shooter.shoot();
        assertTrue(result.equals("Стрелок: Бах!") ||
                result.equals("Стрелок: Клац!"));
    }

    @Test
    void testSetWeapon() {
        Shooter shooter = new Shooter("Стрелок");
        shooter.setWeapon(new Gun(6));
        assertNotNull(shooter.getWeapon());
    }

    @Test
    void testSetName() {
        Shooter shooter = new Shooter("СтароеИмя");
        shooter.setName("НовоеИмя");
        assertEquals("НовоеИмя", shooter.getName());
    }

    @Test
    void testToStringWithoutWeapon() {
        Shooter shooter = new Shooter("Вася");
        assertEquals("Стрелок Вася, без оружия", shooter.toString());
    }

    @Test
    void testToStringWithWeapon() {
        Shooter shooter = new Shooter("Петя", new Gun(6));
        assertTrue(shooter.toString().startsWith("Стрелок Петя, вооружен: "));
    }
}
