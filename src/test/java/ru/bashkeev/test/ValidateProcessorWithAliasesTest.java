package ru.bashkeev.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import ru.bashkeev.processor.ValidateProcessor;

class ValidateProcessorWithAliasesTest {

    @Test
    @DisplayName("Тест 1: Должен работать с прямой аннотацией @Validate")
    void testDirectValidateAnnotation() throws Exception {
        DirectValidateClass obj = new DirectValidateClass();

        ValidateProcessor.ValidationResult result = ValidateProcessor.processor(obj);

        assertFalse(result.isValid(), "Должны быть ошибки валидации");
        assertEquals(1, result.getErrors().size(), "Должна быть 1 ошибка");
        assertTrue(result.getErrors().get(0).contains("active"),
                "Ошибка должна быть для поля 'active' (Boolean не в разрешенных типах)");
    }

    @Test
    @DisplayName("Тест 2: Должен работать с псевдонимом @StringIntegerValidate")
    void testStringIntegerAlias() throws Exception {
        AliasStringIntegerClass obj = new AliasStringIntegerClass();

        ValidateProcessor.ValidationResult result = ValidateProcessor.processor(obj);

        assertFalse(result.isValid(), "Должны быть ошибки валидации");
        assertEquals(1, result.getErrors().size(), "Должна быть 1 ошибка");
        assertTrue(result.getErrors().getFirst().contains("decimal"),
                "Ошибка должна быть для поля 'decimal' (Double не в разрешенных типах)");
    }

    @Test
    @DisplayName("Тест 3: Должен работать с псевдонимом @NumberValidate и наследованием типов")
    void testNumberAliasWithInheritance() throws Exception {
        AliasNumberClass obj = new AliasNumberClass();

        ValidateProcessor.ValidationResult result = ValidateProcessor.processor(obj);

        assertFalse(result.isValid(), "Должны быть ошибки валидации");
        assertEquals(1, result.getErrors().size(), "Должна быть 1 ошибка");
        assertTrue(result.getErrors().getFirst().contains("name"),
                "Ошибка должна быть для поля 'name' (String не в разрешенных типах)");
    }

    @Test
    @DisplayName("Тест 4: Должен работать с псевдонимом @AnyObjectValidate")
    void testAnyObjectAlias() throws Exception {
        AliasAnyObjectClass obj = new AliasAnyObjectClass();

        ValidateProcessor.ValidationResult result = ValidateProcessor.processor(obj);


        assertTrue(result.isValid(), "Все поля должны быть валидны (Object - родитель всех классов)");
        assertEquals(0, result.getErrors().size(), "Не должно быть ошибок");
    }

    @Test
    @DisplayName("Тест 5: Должен возвращать ошибку для класса без аннотаций")
    void testNoAnnotationClass() throws Exception {
        NoAnnotationClass obj = new NoAnnotationClass();

        ValidateProcessor.ValidationResult result = ValidateProcessor.processor(obj);

        assertFalse(result.isValid(), "Должна быть ошибка - нет аннотации");
        assertEquals(1, result.getErrors().size(), "Должна быть 1 ошибка");
        assertTrue(result.getErrors().getFirst().contains("@Validate annotation or its alias not found"),
                "Ошибка должна указывать на отсутствие аннотации");
    }
}