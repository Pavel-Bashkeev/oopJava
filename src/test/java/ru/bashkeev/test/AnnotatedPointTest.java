package ru.bashkeev.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import ru.bashkeev.annotation.Cache;
import ru.bashkeev.annotation.Two;
import ru.bashkeev.annotation.Validate;
import ru.bashkeev.processor.*;

class AnnotatedPointTest {

    private AnnotatedPoint annotatedPoint;

    @BeforeEach
    void setUp() {
        annotatedPoint = new AnnotatedPoint(3, 4);
    }

    @Test
    @DisplayName("Should process @Two annotation correctly")
    void testTwoAnnotation() {
        // Act & Assert - просто проверяем что не бросает исключение
        assertDoesNotThrow(() -> TwoProcessor.process(annotatedPoint));
    }

    @Test
    @DisplayName("Should initialize default values with @Default annotation")
    void testDefaultAnnotation() throws Exception {
        // Act
        DefaultProcessor.process(annotatedPoint);

        // Assert
        assertEquals("dft", annotatedPoint.getPointName(),
                "Поле pointName должно быть инициализировано значением по умолчанию 'dft'");
    }

    @Test
    @DisplayName("Should include/exclude fields in toString with @ToString annotation")
    void testToStringAnnotation() throws Exception {
        // Arrange
        DefaultProcessor.process(annotatedPoint); // Инициализируем значения по умолчанию

        // Act
        String result = ToStringProcessor.process(annotatedPoint);

        // Assert
        assertAll(
                () -> assertTrue(result.contains("pointName=dft"),
                        "Должно содержать поле с @ToString(value = YES)"),
                () -> assertTrue(result.contains("description=Annotated geometry point"),
                        "Должно содержать поле с @ToString(value = YES)"),
                () -> assertFalse(result.contains("POINT_SECRET_123"),
                        "Не должно содержать поле с @ToString(value = NO)"),
                () -> assertTrue(result.contains("x=3"),
                        "Должно содержать поля без аннотации"),
                () -> assertTrue(result.contains("y=4"),
                        "Должно содержать поля без аннотации")
        );
    }

    @Test
    @DisplayName("Should validate object with @Validate annotation")
    void testValidateAnnotation() throws Exception {
        DefaultProcessor.process(annotatedPoint);

        ValidateProcessor.ValidationResult result = ValidateProcessor.processor(annotatedPoint);

        assertTrue(result.isValid(), "AnnotatedPoint должен пройти валидацию");
        assertEquals(0, result.getErrors().size(), "Не должно быть ошибок валидации");
    }

    @Test
    @DisplayName("Should cache method results with @Cache annotation")
    void testCacheAnnotation() throws Exception {
        AnnotatedPoint otherPoint = new AnnotatedPoint(0, 0);

        double firstCall = (Double) CacheProcessor.executeWithCache(
                annotatedPoint, "distanceTo", otherPoint);
        double secondCall = (Double) CacheProcessor.executeWithCache(
                annotatedPoint, "distanceTo", otherPoint);

        assertEquals(firstCall, secondCall, 0.001,
                "Повторный вызов должен возвращать закэшированное значение");
    }

    @Test
    @DisplayName("Should cache different parameters separately")
    void testCacheWithDifferentParameters() throws Exception {
        AnnotatedPoint point1 = new AnnotatedPoint(0, 0);
        AnnotatedPoint point2 = new AnnotatedPoint(10, 10);

        // Act
        double distance1 = (Double) CacheProcessor.executeWithCache(
                annotatedPoint, "distanceTo", point1);
        double distance2 = (Double) CacheProcessor.executeWithCache(
                annotatedPoint, "distanceTo", point2);

        assertNotEquals(distance1, distance2, 0.001,
                "Разные параметры должны давать разные результаты");
    }

    @Test
    @DisplayName("Should process @Invoke annotation and execute methods")
    void testInvokeAnnotation() {
        assertDoesNotThrow(() -> InvokeProcessor.processor(annotatedPoint));
    }

    @Test
    @DisplayName("Should maintain original Point functionality")
    void testOriginalPointFunctionality() {
        AnnotatedPoint point1 = new AnnotatedPoint(0, 0);
        AnnotatedPoint point2 = new AnnotatedPoint(3, 4);

        double distance = point1.distanceTo(point2);

        assertEquals(5.0, distance, 0.1,
                "Расстояние между (0,0) и (3,4) должно быть 5.0");
    }

    @Test
    @DisplayName("Should have correct coordinates after construction")
    void testCoordinates() {
        assertAll(
                () -> assertEquals(3, annotatedPoint.getX(), "X координата должна быть 3"),
                () -> assertEquals(4, annotatedPoint.getY(), "Y координата должна быть 4")
        );
    }

    @Test
    @DisplayName("Should have all annotations present")
    void testAnnotationsPresence() {
        Class<AnnotatedPoint> clazz = AnnotatedPoint.class;

        assertAll(
                () -> assertTrue(clazz.isAnnotationPresent(Two.class),
                        "Должна присутствовать аннотация @Two"),
                () -> assertTrue(clazz.isAnnotationPresent(Cache.class),
                        "Должна присутствовать аннотация @Cache"),
                () -> assertTrue(clazz.isAnnotationPresent(Validate.class),
                        "Должна присутствовать аннотация @Validate")
        );
    }

    @Test
    @DisplayName("Should have correct @Two annotation values")
    void testTwoAnnotationValues() {
        Two twoAnnotation = AnnotatedPoint.class.getAnnotation(Two.class);

        assertAll(
                () -> assertEquals("GeometryPoint", twoAnnotation.first(),
                        "first должно быть 'GeometryPoint'"),
                () -> assertEquals(2, twoAnnotation.second(),
                        "second должно быть 2")
        );
    }

    @Test
    @DisplayName("Should have correct @Cache annotation values")
    void testCacheAnnotationValues() {
        Cache cacheAnnotation = AnnotatedPoint.class.getAnnotation(Cache.class);

        assertAll(
                () -> assertEquals(2, cacheAnnotation.value().length,
                        "Должно быть 2 группы кэша"),
                () -> assertArrayEquals(new String[]{"points", "geometry"}, cacheAnnotation.value(),
                        "Группы кэша должны быть 'points' и 'geometry'")
        );
    }
}