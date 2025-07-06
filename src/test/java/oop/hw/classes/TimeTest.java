package oop.hw.classes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TimeTest {

    @Test
    void testTime71800() {
        Time time = new Time(71800);
        assertEquals("19:56:40",  time.toString());
    }

    @Test
    void testTime100000() {
        Time time = new Time(100000);
        assertEquals("03:46:40",  time.toString());
    }

    @Test
    void testTime10000() {
        Time time = new Time(10000);  // 1 час 1 минута 5 секунд
        assertEquals("02:46:40", time.toString());
    }

    @Test
    void testTime10() {
        Time time = new Time(10);
        assertEquals("00:00:10", time.toString());
    }

    @Test
    void testZeroTime() {
        Time time = new Time(0);
        assertEquals("00:00:00", time.toString());
    }

    @Test
    void testNegativeTime() {
        assertThrows(IllegalArgumentException.class, () -> new Time(-100));
    }
}