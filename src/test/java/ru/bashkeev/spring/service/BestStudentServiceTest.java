package ru.bashkeev.spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import ru.bashkeev.school.Student;
import ru.bashkeev.spring.SchoolApplication;
import ru.bashkeev.spring.config.MinMaxConfig;
import ru.bashkeev.spring.config.RangePredicateConfig;
import ru.bashkeev.spring.config.StudentConfig;

import static org.junit.jupiter.api.Assertions.*;

// Исключаем конфликтующие конфигурации
@SpringBootTest(classes = SchoolApplication.class)
@Import({
        MinMaxConfig.class,
        RangePredicateConfig.class,
        StudentConfig.class
})
class BestStudentServiceTest {

    @Autowired
    private BestStudentService bestStudentService;

    @Test
    void contextLoads() {
        assertNotNull(bestStudentService);
    }

    @Test
    void getBestStudentShouldReturnStudentWithHighestAverage() {
        Student bestStudent = bestStudentService.getBestStudent();

        assertNotNull(bestStudent);
        assertEquals("Мария", bestStudent.getName());
        assertEquals(5.0, bestStudent.getAvgGrade(), 0.001);
    }
}