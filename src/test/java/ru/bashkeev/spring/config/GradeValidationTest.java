package ru.bashkeev.spring.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import ru.bashkeev.school.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Import({MinMaxConfig.class, RangePredicateConfig.class, StudentConfig.class})
class GradeValidationTest {

    @Autowired
    private List<Student> students;

    @Test
    void studentsShouldRejectInvalidGrades() {
        Student student = students.getFirst();

        assertThrows(Exception.class, () -> student.addGrade(1));
        assertThrows(Exception.class, () -> student.addGrade(6));
        assertThrows(Exception.class, () -> student.addGrade(0));
    }

    @Test
    void studentsShouldAcceptValidGrades() {
        Student student = students.getFirst();

        assertDoesNotThrow(() -> student.addGrade(2));
        assertDoesNotThrow(() -> student.addGrade(3));
        assertDoesNotThrow(() -> student.addGrade(4));
        assertDoesNotThrow(() -> student.addGrade(5));
    }
}