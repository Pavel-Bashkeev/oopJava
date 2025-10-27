package ru.bashkeev.spring.builder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import ru.bashkeev.school.Student;
import ru.bashkeev.spring.config.MinMaxConfig;
import ru.bashkeev.spring.config.RangePredicateConfig;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Import({MinMaxConfig.class, RangePredicateConfig.class})
class StudentBuilderTest {

    @Autowired
    private StudentBuilder studentBuilder;

    @Test
    void shouldCreateStudentWithNameOnly() {
        Student student = studentBuilder.create("Иван");

        assertNotNull(student);
        assertEquals("Иван", student.getName());
        assertTrue(student.getGrades().isEmpty());
    }

    @Test
    void shouldCreateStudentWithNameAndGrades() {
        List<Integer> grades = List.of(4, 5, 3);

        Student student = studentBuilder.create("Петр", grades);

        assertEquals("Петр", student.getName());
        assertEquals(grades, student.getGrades());
    }

    @Test
    void shouldCreateIndependentListOfGrades() {
        List<Integer> originalGrades = new ArrayList<>(List.of(4, 5));

        Student student = studentBuilder.create("Мария", originalGrades);

        originalGrades.add(3);
        assertEquals(List.of(4, 5), student.getGrades());
    }
}