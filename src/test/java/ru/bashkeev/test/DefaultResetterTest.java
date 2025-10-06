package ru.bashkeev.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import ru.bashkeev.processor.DefaultResetter;

class DefaultResetterTest {

    private DefaultReseterTestClass testObject;

    @BeforeEach
    void setUp() {
        testObject = new DefaultReseterTestClass();
    }

    @Test
    @DisplayName("Тест 1: Должен сбрасывать поля с аннотациями @Default к указанным значениям")
    void testAnnotatedFieldsReset() {
        assertEquals("original annotated", testObject.getAnnotatedString());
        assertEquals(999, testObject.getAnnotatedInteger());
        assertNull(testObject.getAnnotatedBoolean());
        assertNull(testObject.getAnnotatedDouble());

        DefaultResetter.reset(testObject);

        assertAll(
                () -> assertEquals("default", testObject.getAnnotatedString(),
                        "Поле с @Default(String.class) должно стать 'default'"),
                () -> assertEquals(0, testObject.getAnnotatedInteger(),
                        "Поле с @Default(Integer.class) должно стать 0"),
                () -> assertEquals(false, testObject.getAnnotatedBoolean(),
                        "Поле с @Default(Boolean.class) должно стать false"),
                () -> assertEquals(0.0, testObject.getAnnotatedDouble(),
                        "Поле с @Default(Double.class) должно стать 0.0")
        );
    }

    @Test
    @DisplayName("Тест 2: Должен сбрасывать примитивы к Java defaults и применять классную аннотацию к String полям")
    void testPrimitivesAndClassAnnotation() {
        assertEquals("original string", testObject.getStringField());
        assertEquals(500, testObject.getIntegerField());
        assertEquals(true, testObject.getBooleanField());
        assertEquals("initial", testObject.getInitializedString());
        assertEquals(100, testObject.getInitializedInt());

        DefaultResetter.reset(testObject);

        assertAll(
                () -> assertEquals(0, testObject.getPrimitiveInt(),
                        "Примитив int должен стать 0"),
                () -> assertEquals(0.0, testObject.getPrimitiveDouble(),
                        "Примитив double должен стать 0.0"),
                () -> assertFalse(testObject.isPrimitiveBoolean(), "Примитив boolean должен стать false"),
                () -> assertEquals(0L, testObject.getPrimitiveLong(),
                        "Примитив long должен стать 0"),
                () -> assertEquals(0.0f, testObject.getPrimitiveFloat(),
                        "Примитив float должен стать 0.0"),
                () -> assertEquals('\0', testObject.getPrimitiveChar(),
                        "Примитив char должен стать '\\0'"),
                () -> assertEquals((byte)0, testObject.getPrimitiveByte(),
                        "Примитив byte должен стать 0"),
                () -> assertEquals((short)0, testObject.getPrimitiveShort(),
                        "Примитив short должен стать 0"),

                () -> assertEquals("default", testObject.getStringField(),
                        "String поле без аннотации должно получить 'default' из классной аннотации"),
                () -> assertEquals("default", testObject.getInitializedString(),
                        "Инициализированное String поле должно получить 'default' из классной аннотации"),

                () -> assertEquals(500, testObject.getIntegerField(),
                        "Integer поле без аннотации должно остаться неизменным"),
                () -> assertEquals(true, testObject.getBooleanField(),
                        "Boolean поле без аннотации должно остаться неизменным"),
                () -> assertEquals(200, testObject.getInitializedInteger(),
                        "Инициализированный Integer должен остаться неизменным"),

                () -> assertNotNull(testObject.getObjectField(),
                        "Object поле должно остаться неизменным"),
                () -> assertEquals(2, testObject.getListField().size(),
                        "List поле должно остаться неизменным")
        );
    }

    @Test
    @DisplayName("Тест 3: Должен корректно обрабатывать множественные объекты DefaultReseterTestClass")
    void testMultipleObjects() {
        DefaultReseterTestClass obj1 = new DefaultReseterTestClass();
        DefaultReseterTestClass obj2 = new DefaultReseterTestClass();
        DefaultReseterTestClass obj3 = new DefaultReseterTestClass();

        DefaultResetter.reset(obj1, obj2, obj3);

        assertAll(
                () -> assertEquals("default", obj1.getAnnotatedString(),
                        "Obj1: аннотированное String поле должно сброситься"),
                () -> assertEquals(0, obj1.getAnnotatedInteger(),
                        "Obj1: аннотированное Integer поле должно сброситься"),
                () -> assertEquals(0, obj1.getPrimitiveInt(),
                        "Obj1: примитив int должен стать 0"),
                () -> assertEquals("default", obj1.getStringField(),
                        "Obj1: String поле должно получить классное значение"),

                () -> assertEquals("default", obj2.getAnnotatedString(),
                        "Obj2: аннотированное String поле должно сброситься"),
                () -> assertEquals(0, obj2.getAnnotatedInteger(),
                        "Obj2: аннотированное Integer поле должно сброситься"),
                () -> assertEquals(0, obj2.getPrimitiveInt(),
                        "Obj2: примитив int должен стать 0"),
                () -> assertEquals("default", obj2.getStringField(),
                        "Obj2: String поле должно получить классное значение"),

                () -> assertEquals("default", obj3.getAnnotatedString(),
                        "Obj3: аннотированное String поле должно сброситься"),
                () -> assertEquals(0, obj3.getAnnotatedInteger(),
                        "Obj3: аннотированное Integer поле должно сброситься"),
                () -> assertEquals(0, obj3.getPrimitiveInt(),
                        "Obj3: примитив int должен стать 0"),
                () -> assertEquals("default", obj3.getStringField(),
                        "Obj3: String поле должно получить классное значение"),

                () -> assertEquals(500, obj1.getIntegerField(),
                        "Obj1: Integer поле без аннотации должно остаться неизменным"),
                () -> assertEquals(500, obj2.getIntegerField(),
                        "Obj2: Integer поле без аннотации должно остаться неизменным"),
                () -> assertEquals(500, obj3.getIntegerField(),
                        "Obj3: Integer поле без аннотации должно остаться неизменным"),

                () -> assertEquals(2, obj1.getListField().size(),
                        "Obj1: List должен сохранить свои элементы"),
                () -> assertEquals(2, obj2.getListField().size(),
                        "Obj2: List должен сохранить свои элементы"),
                () -> assertEquals(2, obj3.getListField().size(),
                        "Obj3: List должен сохранить свои элементы")
        );
    }
}