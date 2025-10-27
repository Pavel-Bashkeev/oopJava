package ru.bashkeev.spring.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import ru.bashkeev.school.Student;
import ru.bashkeev.school.interfaces.GradeValidator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Import({MinMaxConfig.class, RangePredicateConfig.class, StudentConfig.class})
class StudentConfigIntegrationTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private GradeValidator rangeValidator;

    @Autowired
    private List<Student> students;

    @Test
    void allBeansShouldExist() {
        assertNotNull(context.getBean("min"));
        assertNotNull(context.getBean("max"));
        assertNotNull(context.getBean("rangePredicate"));
        assertNotNull(context.getBean("rangeValidator"));
        assertNotNull(context.getBean("students"));
    }

    @Test
    void rangeValidatorShouldWorkCorrectly() {
        assertTrue(rangeValidator.isValid(3));
        assertTrue(rangeValidator.isValid(5));
        assertFalse(rangeValidator.isValid(1));
        assertFalse(rangeValidator.isValid(6));
    }

    @Test
    void studentsShouldBeProperlyConfigured() {
        assertEquals(3, students.size());
        assertEquals("Иван", students.get(0).getName());
        assertEquals("Пётр", students.get(1).getName());
        assertEquals("Мария", students.get(2).getName());
    }
}