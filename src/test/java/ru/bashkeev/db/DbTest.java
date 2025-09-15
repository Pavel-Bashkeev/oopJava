package ru.bashkeev.db;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import ru.bashkeev.geometry.points.Point;
import ru.bashkeev.geometry.points.PointFactory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class DbTest {
    private Db db;

    @BeforeEach
    void setUp() {
        List<String> initList = List.of("42", "{3;4}", "true");
        db = new Db(new ArrayList<>(initList));

        db.addConverter(Point.class, value -> {
            String cleaned = value.replace("{", "").replace("}", "");
            String[] parts = cleaned.split(";");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            return PointFactory.getInstance().createPoint(x, y);
        });
    }

    @Test
    void testGetString() {
        Db db = new Db();
        db.addRecord("Hello");
        db.addRecord("World");

        db.addConverter(String.class, String::toString);
        assertEquals("Hello", db.get(0, String.class));
        assertEquals("World", db.get(1, String.class));
    }

    @Test
    void testGetInteger() {
        Db db = new Db();
        db.addRecord("42");
        db.addRecord("100");

        db.addConverter(Integer.class, Integer::parseInt);

        assertEquals(42, db.get(0, Integer.class));
        assertEquals(100, db.get(1, Integer.class));
    }

    @Test
    void testGetDouble() {
        Db db = new Db();
        db.addRecord("3.14");
        db.addRecord("2.71");

        db.addConverter(Double.class, Double::parseDouble);
        
        assertEquals(3.14, db.get(0, Double.class));
        assertEquals(2.71, db.get(1, Double.class));
    }

    @Test
    void testSameRecordDifferentTypes() {
        Db db = new Db();
        db.addRecord("42");

        db.addConverter(String.class, String::toString);
        db.addConverter(Integer.class, Integer::parseInt);

        String asString = db.get(0, String.class);
        Integer asInteger = db.get(0, Integer.class);

        assertEquals("42", asString);
        assertEquals(42, asInteger);
    }

    @Test
    void testSetRecord() {
        Db db = new Db();
        db.addConverter(String.class, String::toString);
        db.addRecord("OldValue");
        db.setRecord(0, "NewValue");

        assertEquals("NewValue", db.get(0, String.class));
    }

    @Test
    void testSetRecordOutOfBounds() {
        Db db = new Db();
        db.addRecord("Test");

        assertThrows(IndexOutOfBoundsException.class, () -> {
            db.setRecord(1, "Invalid");
        });
    }

    @Test
    void testGetOutOfBounds() {
        assertThrows(IllegalArgumentException.class, () -> {
            db.get(0, String.class);
        });
    }

    @Test
    void testGetWithUnregisteredType() {
        Db db = new Db();
        db.addRecord("test");

        assertThrows(IllegalArgumentException.class, () -> {
            db.get(0, Boolean.class);
        });
    }

    @Test
    void testConstructorWithInitialRecords() {
        List<String> initialRecords = List.of("first", "second", "third");
        Db dbWithData = new Db(new ArrayList<>(initialRecords));

        dbWithData.addConverter(String.class, value -> value);

        assertEquals("first", dbWithData.get(0, String.class));
        assertEquals("second", dbWithData.get(1, String.class));
        assertEquals("third", dbWithData.get(2, String.class));
    }

    @Test
    void testMultipleConverters() {
        Db db = new Db();
        db.addConverter(Boolean.class, Boolean::valueOf);
        db.addConverter(Float.class, Float::valueOf);

        db.addRecord("true");
        db.addRecord("3.14");

        assertTrue(db.get(0, Boolean.class));
        assertEquals(3.14f, db.get(1, Float.class));
    }


    @Test
    void testCustomConverterWithGetValue() {
        db.setRecord(0, "{10;20}");
        Point point = db.get(0, Point.class);

        assertEquals(10, point.getX());
        assertEquals(20, point.getY());
    }
}