package ru.bashkeev.school;

import ru.bashkeev.exceptions.InvalidGradeException;
import java.util.List;
import java.util.Random;

public class GradeAdder {
    private static final Random random = new Random();

    public static String addRandomGrades(List<Student> students) {
        if (students == null || students.isEmpty()) {
            return "Список студентов пуст";
        }

        for (Student student : students) {
            int randomGrade = 1 + random.nextInt(10);
            try {
                student.addGrade(randomGrade);
            } catch (InvalidGradeException e) {
                return "Не удалось добавить оценки. Причина: " + e.getMessage();
            }
        }

        return null;
    }
}
