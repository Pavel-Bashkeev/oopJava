package ru.bashkeev.school;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class StudentComparisonTest {

    @Test
    void testCompareTo_FirstStudentHasHigherAverage() {
        Student student1 = new Student("Иван", List.of(5, 5, 5)); // avg = 5.0
        Student student2 = new Student("Петр", List.of(4, 4, 4)); // avg = 4.0

        assertTrue(student1.compareTo(student2) > 0); // Должен вернуть 1
        assertTrue(student2.compareTo(student1) < 0); // Должен вернуть -1
    }

    @Test
    void testCompareTo_EqualAverages() {
        Student student1 = new Student("Иван", List.of(5, 4, 3)); // avg = 4.0
        Student student2 = new Student("Петр", List.of(4, 4, 4)); // avg = 4.0

        assertEquals(0, student1.compareTo(student2));
        assertEquals(0, student2.compareTo(student1));
    }

    @Test
    void testCompareTo_SecondStudentHasHigherAverage() {
        Student student1 = new Student("Иван", List.of(3, 3, 3)); // avg = 3.0
        Student student2 = new Student("Петр", List.of(4, 4, 4)); // avg = 4.0

        assertTrue(student1.compareTo(student2) < 0); // Должен вернуть -1
        assertTrue(student2.compareTo(student1) > 0); // Должен вернуть 1
    }

    @Test
    void testCompareTo_WithEmptyGrades() {
        Student student1 = new Student("Иван"); // avg = 0.0
        Student student2 = new Student("Петр", List.of(4, 4)); // avg = 4.0

        assertTrue(student1.compareTo(student2) < 0); // 0.0 < 4.0 → -1
        assertTrue(student2.compareTo(student1) > 0); // 4.0 > 0.0 → 1
    }

    @Test
    void testCompareTo_BothEmptyGrades() {
        Student student1 = new Student("Иван"); // avg = 0.0
        Student student2 = new Student("Петр"); // avg = 0.0

        assertEquals(0, student1.compareTo(student2));
        assertEquals(0, student2.compareTo(student1));
    }

    @Test
    void testCompareTo_FloatingPointPrecision() {
        Student student1 = new Student("Иван", List.of(5, 4, 5, 4)); // avg = 4.5
        Student student2 = new Student("Петр", List.of(5, 4, 4, 5)); // avg = 4.5

        assertEquals(0, student1.compareTo(student2)); // Должны быть равны с учетом погрешности
    }

    @Test
    void testCompareTo_SmallDifference() {
        Student student1 = new Student("Иван", List.of(5, 5, 5, 4)); // avg = 4.75
        Student student2 = new Student("Петр", List.of(5, 5, 4, 4)); // avg = 4.5

        assertTrue(student1.compareTo(student2) > 0); // 4.75 > 4.5 → 1
        assertTrue(student2.compareTo(student1) < 0); // 4.5 < 4.75 → -1
    }
}