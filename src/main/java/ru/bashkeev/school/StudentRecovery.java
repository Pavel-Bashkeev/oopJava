package ru.bashkeev.school;

import ru.bashkeev.exceptions.InvalidGradeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StudentRecovery {
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
            } catch (InvalidGradeException | IllegalArgumentException ex) {
                System.out.println("Неожиданная ошибка: " + ex.getMessage());
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
            students.add(new Student(name, grade -> grade >= 0 && grade <= 50));
        }

        if (addArgs == null || addArgs.isEmpty()) {
            return students;
        }

        List<Integer> validGrades = new ArrayList<>();

        for (String gradeStr : addArgs) {
            try {
                int grade = Integer.parseInt(gradeStr);
                if (grade >= 1 && grade <= 5) {
                    validGrades.add(grade);
                } else {
                    break;
                }
            } catch (NumberFormatException e) { }
        }

        if (!students.isEmpty()) {
            for (Student student : students) {
                for (Integer grade : validGrades) {
                    student.addGrade(grade);
                }
            }
        } else {
            throw new InvalidGradeException("Обнаружены недопустимые оценки");
        }

        return students;
    }

    private static void printStudents(String header, List<Student> students) {
        System.out.println(header);
        students.forEach(System.out::println);
    }

    private static String extractStudentName(String errorMessage) {
        if (errorMessage.contains("Ошибка создания студента")) {
            return errorMessage.replace("Ошибка создания студента", "").trim();
        }
        return "Неизвестный";
    }

    protected static List<String> generateStudentParams() {
        return List.of("Иванов", "Петров", "Сидоров");
    }

    protected static List<String> generateGradeParams() {
        return List.of("4", "5", "2", "5", "3", "6", "1", "5", "10", "5");
    }
}