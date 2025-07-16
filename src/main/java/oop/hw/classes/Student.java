package oop.hw.classes;

import oop.hw.helpers.ArrayToString;

import java.util.ArrayList;
import java.util.List;

public final class Student {
    private String        name;
    private List<Integer> grades;

    public Student(String name) {
        this(name, new ArrayList<>());
    }

    public Student(String name, List<Integer> grades) {
        this.name   = name;
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

    private void validateGrade(int grade) {
        if (grade < 2 || grade > 5) {
            throw new IllegalArgumentException("Оценка должна быть в диапазоне от 2 до 5");
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