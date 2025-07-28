package oop.hw.classes;

import ru.bashkeev.city.House;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertEquals("Дом с 11 этажами", new House(11).toString());
        assertEquals("Дом с 101 этажом", new House(101).toString());
    }

    @Test
    void testHouseToStringLargeNumbers() {
        assertEquals("Дом с 15432 этажами", new House(15432).toString());
        assertEquals("Дом с 1012 этажами", new House(1012).toString());
    }

    @Test
    void testZeroFloors() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new House(0)
        );
        assertEquals("Количество этажей должно быть положительным числом", exception.getMessage());
    }
}