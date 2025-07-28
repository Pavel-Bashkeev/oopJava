package ru.bashkeev.school;

import ru.bashkeev.helpers.ArrayToString;
import ru.bashkeev.school.interfaces.GradeValidator;

import java.util.ArrayList;
import java.util.List;

public final class Student {
    private String name;
    private List<Integer>  grades;
    private GradeValidator gradeValidator;

    public Student(String name) {
        this(name, grade -> true, new ArrayList<>());
    }

    public Student(String name, GradeValidator gradeValidator) {
        this(name, gradeValidator, new ArrayList<>());
    }

    public Student(String name, List<Integer> grades) {
        this(name, grade -> true, grades);
    }

    public Student(String name, GradeValidator gradeValidator, List<Integer> grades) {
        this.name = name;
        this.gradeValidator = gradeValidator;
        this.grades = processGrades(grades);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    public void setGrades(List<Integer> grades) {
        this.grades = processGrades(grades);
    }

    public void addGrade(int grade) {
        validateGrade(grade);
        this.grades.add(grade);
    }

    private void validateGrade(Integer grade) {
        if (grade == null || !gradeValidator.isValid(grade)) {
            throw new IllegalArgumentException("Оценка не соответствует заданным требованиям");
        }
    }

    private void validateGrades(List<Integer> grades) {
        for (Integer grade : grades) {
            validateGrade(grade);
        }
    }

    private List<Integer> processGrades(List<Integer> grades) {
        if (grades == null) {
            return new ArrayList<>();
        }

        validateGrades(grades);
        return grades;
    }

    @Override
    public String toString() {
        return name + ": " + ArrayToString.intListToString(grades);
    }

    public double getAvgGrade() {
        if (this.grades.isEmpty()) {
            return 0;
        }

        int sum = 0;
        for (Integer grade : this.grades) {
            sum += grade;
        }

        return (double) sum / this.grades.size();
    }

    public boolean isExcellentStudent() {
        if (this.grades.isEmpty()) {
            return false;
        }

        for (Integer grade : grades) {
            if (grade != 5) {
                return false;
            }
        }
        return true;
    }
}