package ru.bashkeev.utils;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReflectionUtilsTest {

    private static class BaseClass {
        private int privateField;
        public String publicField;
    }

    private static class SubClass extends BaseClass {
        protected boolean protectedField;
        public double publicSubField;
    }

    @Test
    void testBaseClassFields() {
        List<Field> fields = ReflectionUtils.fieldCollection(BaseClass.class);

        assertEquals(2, fields.size());
        assertTrue(fields.stream().anyMatch(field -> field.getName().equals("privateField")));
        assertTrue(fields.stream().anyMatch(field -> field.getName().equals("publicField")));
    }

    @Test
    void testInheritedFields() {
        List<Field> fields = ReflectionUtils.fieldCollection(SubClass.class);

        assertEquals(4, fields.size());

        assertTrue(fields.stream().anyMatch(field -> field.getName().equals("protectedField")));
        assertTrue(fields.stream().anyMatch(field -> field.getName().equals("publicSubField")));

        assertTrue(fields.stream().anyMatch(field -> field.getName().equals("privateField")));
        assertTrue(fields.stream().anyMatch(field -> field.getName().equals("publicField")));
    }

    @Test
    void testFieldModifiers() {
        List<Field> fields = ReflectionUtils.fieldCollection(SubClass.class);

        boolean hasPrivate = fields.stream().anyMatch(field -> field.getName().equals("privateField"));
        boolean hasProtected = fields.stream().anyMatch(field -> field.getName().equals("protectedField"));
        boolean hasPublic = fields.stream().anyMatch(field -> field.getName().equals("publicSubField"));

        assertTrue(hasPrivate);
        assertTrue(hasProtected);
        assertTrue(hasPublic);
    }

    @Test
    void testNoDuplicateFields() {
        List<Field> fields = ReflectionUtils.fieldCollection(SubClass.class);
        assertEquals(4, fields.size());
    }


    @Test
    void testObjectFields() {
        List<Field> fields = ReflectionUtils.fieldCollection(Object.class);
        assertTrue(fields.isEmpty());
    }
}
