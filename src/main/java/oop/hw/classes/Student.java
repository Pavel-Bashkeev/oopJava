package oop.hw.classes;

import oop.hw.helpers.ArrayToString;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String        name;
    private List<Integer> grades;

    public Student(String name) {
        this(name, new ArrayList<>());
    }

    public Student(String name, List<Integer> grades) {
        this.name   = name;
        this.grades = grades != null ? new ArrayList<>(grades) : new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getGrades() {
        return new ArrayList<>(grades);
    }

    public void setGrades(List<Integer> grades) {
        this.grades = grades;
    }

    public void addGrade(Integer grade) {
        this.grades.add(grade);
    }

    @Override
    public String toString() {
        return name + ": " + ArrayToString.intListToString(grades);
    }

    public double getAvgGrade() {
        if (this.grades.isEmpty()) {
            return 0;
        }

        if (this.grades.size() == 1) {
            return this.getGrades().getFirst();
        }

        int sum = 0;
        for (Integer grade : this.grades) {
            sum += grade;
        }

        return (double) sum / this.grades.size();
    }

    public boolean isExcellentStudent() {
        if  (this.grades.isEmpty()) {
            return false;
        }

        boolean isExcellent = true;
        for (Integer grade : grades) {
            if (grade != 5) {
                isExcellent = false;
                break;
            }
        }
        return isExcellent;
    }
}
