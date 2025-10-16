package ru.bashkeev.school;

import ru.bashkeev.exception.InvalidGradeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StudentRecovery {
    private static final Random random = new Random();

    public static void processStudents() {
        List<String> studentParams = generateStudentParams();
        List<String> gradeParams = generateGradeParams();

        try {
            List<Student> students = convert(studentParams, gradeParams);
            printStudents("Успешно созданные студенты:", students);
        } catch (InvalidGradeException e) {
            try {
                List<Student> students = convert(studentParams, List.of());
                printStudents("Студенты созданы без оценок:", students);
            } catch (Exception ex) {
                System.out.println("Неожиданная ошибка при создании студентов без оценок: " + ex.getMessage());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("студента " + extractStudentName(e.getMessage()) + " создать невозможно");
        }
    }

    protected static List<Student> convert(List<String> constructorArgs, List<String> addArgs) {
        List<Student> students = new ArrayList<>();

        if (constructorArgs == null || constructorArgs.isEmpty()) {
            throw new IllegalArgumentException("Список параметров студентов пуст");
        }

        for (String name : constructorArgs) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Ошибка создания студента: " + name);
            }
            students.add(new Student(name, grade -> grade >= 2 && grade <= 5));
        }

        if (addArgs != null && !addArgs.isEmpty()) {
            for (String gradeStr : addArgs) {
                try {
                    int grade = Integer.parseInt(gradeStr);
                    if (grade < 2 || grade > 5) {
                        throw new InvalidGradeException("Оценка " + grade + " недопустима");
                    }
                    for (Student student : students) {
                        student.addGrade(grade);
                    }
                } catch (NumberFormatException e) {
                    throw new InvalidGradeException("Неверный формат оценки: " + gradeStr);
                }
            }
        }

        return students;
    }

    private static String extractStudentName(String errorMessage) {
        if (errorMessage != null && errorMessage.contains("Ошибка создания студента")) {
            return errorMessage.replace("Ошибка создания студента", "").replace(":", "").trim();
        }
        return "Неизвестный";
    }

    protected static List<String> generateStudentParams() {
        List<String> names = new ArrayList<>();
        names.add("Иванов");
        names.add("Петров");
        names.add("Сидоров");

        if (random.nextBoolean()) {
            names.add("");
        }

        return names;
    }

    protected static List<String> generateGradeParams() {
        List<String> grades = new ArrayList<>();
        grades.add("4");
        grades.add("5");
        grades.add("3");

        if (random.nextBoolean()) {
            grades.add("6");
        }

        return grades;
    }

    private static void printStudents(String header, List<Student> students) {
        System.out.println(header);
        for (Student student : students) {
            System.out.println(student.getName() + ": " + student.getGrades());
        }
    }
}