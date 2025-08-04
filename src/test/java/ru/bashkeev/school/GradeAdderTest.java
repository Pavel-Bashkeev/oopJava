package ru.bashkeev.school;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import ru.bashkeev.exceptions.InvalidGradeException;
import ru.bashkeev.school.Student;
import java.util.ArrayList;
import java.util.List;

class GradeAdderTest {
    private List<Student> students;

    @BeforeEach
    void setUp() {
        students = new ArrayList<>();
    }

    @Test
    void testAddRandomGradesSuccess() {
        students.add(new Student("Иванов"));
        students.add(new Student("Петров"));

        String result = GradeAdder.addRandomGrades(students);

        assertNull(result);
        assertEquals(1, students.getFirst().getGrades().size());
        assertEquals(1, students.get(1).getGrades().size());
    }

    @Test
    void testEmptyStudentList() {
        String result = GradeAdder.addRandomGrades(new ArrayList<>());
        assertEquals("Список студентов пуст", result);
    }

    @Test
    void testNullStudentList() {
        String result = GradeAdder.addRandomGrades(null);
        assertEquals("Список студентов пуст", result);
    }

    @Test
    void testAllStudentsGetDifferentGrades() {
        students.add(new Student("Иванов"));
        students.add(new Student("Петров"));
        students.add(new Student("Сидоров"));

        GradeAdder.addRandomGrades(students);

        assertEquals(1, students.getFirst().getGrades().size());
        assertEquals(1, students.get(1).getGrades().size());
        assertEquals(1, students.get(2).getGrades().size());

        // Проверяем что оценки в диапазоне 1-10
        assertTrue(students.getFirst().getGrades().getFirst() >= 1);
        assertTrue(students.getFirst().getGrades().getFirst() <= 10);
    }
}