package oop.hw.classes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HouseTest {
    @Test
    void testHouseToStringSingleFloor() {
        House house = new House(1);
        assertEquals("Дом с 1 этажом", house.toString());
    }

    @Test
    void testHouseToStringMultipleFloors() {
        assertEquals("Дом с 2 этажами", new House(2).toString());
        assertEquals("Дом с 5 этажами", new House(5).toString());
        assertEquals("Дом с 21 этажом", new House(21).toString());
    }

    @Test
    void testHouseToStringEdgeCases() {
        assertEquals("Дом с 0 этажами", new House(0).toString());
        assertEquals("Дом с 11 этажами", new House(11).toString());
        assertEquals("Дом с 101 этажом", new House(101).toString());
    }

    @Test
    void testHouseToStringLargeNumbers() {
        assertEquals("Дом с 15432 этажами", new House(15432).toString());
        assertEquals("Дом с 1012 этажами", new House(1012).toString());
    }

    @Test
    void testHouseToStringNegativeNumbers() {
        assertEquals("Дом с -1 этажом", new House(-1).toString());
        assertEquals("Дом с -12 этажами", new House(-12).toString());
    }
}