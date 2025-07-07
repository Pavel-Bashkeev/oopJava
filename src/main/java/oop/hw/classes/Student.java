package oop.hw.classes;

import oop.hw.helpers.ArrayToString;

import java.util.ArrayList;
import java.util.List;

public class Student {
    String name;
    List<Integer> grades;

    public Student(String name) {
        this(name , null);
    }

    public Student(String name, List<Integer> grades) {
        this.name = name;
        if (grades != null) {
            this.grades = grades;
        }
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
        this.grades = grades;
    }

    public void addGrade(Integer grade) {
        this.grades.add(grade);
    }

    @Override
    public String toString() {
        return  name + ": " + ArrayToString.intListToString(grades);
    }
}
