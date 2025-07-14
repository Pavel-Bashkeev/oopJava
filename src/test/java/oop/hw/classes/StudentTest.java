package oop.hw.classes;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void testVasyaCreation() {
        // 1. Создание студента Васи с оценками 3,4,5
        Student vasya = new Student("Вася", Arrays.asList(3, 4, 5));

        assertEquals("Вася", vasya.getName());
        assertEquals(Arrays.asList(3, 4, 5), vasya.getGrades());
        assertEquals("Вася: [3, 4, 5]", vasya.toString());
    }

    @Test
    void testPetyaCopyGrades() {
        // 2-3. Копирование оценок Васи и изменение первой оценки Пети
        Student vasya = new Student("Вася", Arrays.asList(3, 4, 5));
        Student petya = new Student("Петя");

        // Копируем ссылку на список оценок
        petya.setGrades(vasya.getGrades());

        // Меняем первую оценку Пети
        petya.getGrades().set(0, 5);

        // Проверяем, что изменились оценки и у Васи (так как список один)
        assertEquals("Вася: [5, 4, 5]", vasya.toString());
        assertEquals("Петя: [5, 4, 5]", petya.toString());
    }

    @Test
    void testAndreyDeepCopy() {
        // 4. Независимое копирование оценок
        Student vasya = new Student("Вася", Arrays.asList(3, 4, 5));
        Student andrey = new Student("Андрей");

        // Создаем новый список с теми же значениями
        andrey.setGrades(new ArrayList<>(vasya.getGrades()));

        // Меняем первую оценку Андрея
        andrey.getGrades().set(0, 2);

        // Проверяем, что оценки Васи не изменились, так как создали новый список оценок
        assertEquals("Вася: [3, 4, 5]", vasya.toString());
        assertEquals("Андрей: [2, 4, 5]", andrey.toString());
    }

    @Test
    void testAddGrade() {
        Student student = new Student("Маша", new ArrayList<>());
        student.addGrade(5);
        student.addGrade(4);

        assertEquals(Arrays.asList(5, 4), student.getGrades());
        assertEquals("Маша: [5, 4]", student.toString());
    }

    @Test
    void testEmptyGrades() {
        Student student = new Student("Саша");
        assertEquals("Саша: []", student.toString());
    }

    @Test
    void testAvgAndIsExcellent() {
        Student s1 = new Student("Вася", List.of(3,4,5,4));
        Student s2 = new Student("Петя", List.of(5,5,5,5));

        assertFalse(s1.isExcellentStudent());
        assertTrue(s2.isExcellentStudent());

        assertEquals(4.0, s1.getAvgGrade(), 0.01);
        assertEquals(5.0, s2.getAvgGrade(), 0.01);
    }
}