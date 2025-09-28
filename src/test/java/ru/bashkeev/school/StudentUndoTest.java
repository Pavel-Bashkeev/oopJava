package ru.bashkeev.school;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class StudentUndoTest {
    @Test
    void testUndoNameChange() {
        Student student = new Student("Иван");
        student.setName("Петр");
        student.undo();
        assertEquals("Иван", student.getName());
    }

    @Test
    void testUndoGradeAddition() {
        Student student = new Student("Иван");
        student.addGrade(5);
        student.undo();
        assertTrue(student.getGrades().isEmpty());
    }

    @Test
    void testMultipleUndo() {
        Student student = new Student("Иван");
        student.setName("Петр");
        student.addGrade(5);
        student.addGrade(4);

        student.undo();
        assertEquals(List.of(5), student.getGrades());

        student.undo();
        assertTrue(student.getGrades().isEmpty());

        student.undo();
        assertEquals("Иван", student.getName());
    }

    @Test
    void testUndoBeyondInitial() {
        Student student = new Student("Иван");
        assertThrows(IllegalStateException.class, student::undo);
        assertEquals("Иван", student.getName());
    }
}
