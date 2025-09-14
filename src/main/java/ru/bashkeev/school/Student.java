package ru.bashkeev.school;

import ru.bashkeev.exceptions.InvalidGradeException;
import ru.bashkeev.helpers.ArrayToString;
import ru.bashkeev.school.interfaces.Action;
import ru.bashkeev.school.interfaces.GradeValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Student implements Comparable<Student> {
    private       String         name;
    private       List<Integer>  grades;
    private final GradeValidator gradeValidator;
    private       List<Action>   undoOps = new ArrayList<>();
    private int currentUndoIndex = -1;

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
        this.name           = name;
        this.gradeValidator = gradeValidator;
        this.grades         = processGrades(grades);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        final String tmp = this.name;
        addUndoAction(() -> this.name = tmp);
        this.name = name;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    public void setGrades(List<Integer> grades) {
        final List<Integer> oldGrades = new ArrayList<>(this.grades);
        addUndoAction(() -> this.grades = new ArrayList<>(oldGrades));
        this.grades = processGrades(grades);
    }

    public void addGrade(int grade) {
        validateGrade(grade);

        addUndoAction(() -> {
            if (!grades.isEmpty()) {
                grades.removeLast();
            }
        });

        this.grades.add(grade);
    }

    public void undo() {
        if (currentUndoIndex < 0) {
            throw new IllegalStateException("There is nothing to cancel");
        }

        undoOps.get(currentUndoIndex).act();
        currentUndoIndex--;
    }

    private void addUndoAction(Action action) {
        if (currentUndoIndex < undoOps.size() - 1) {
            undoOps.subList(currentUndoIndex + 1, undoOps.size()).clear();
        }
        undoOps.add(action);
        currentUndoIndex = undoOps.size() - 1;
    }

    private void validateGrade(Integer grade) {
        if (grade == null || !gradeValidator.isValid(grade)) {
            throw new InvalidGradeException("Неправильная оценка для студента " + name);
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student other)) return false;

        return Objects.equals(name, other.name) &&
                Double.compare(getAvgGrade(), other.getAvgGrade()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, getAvgGrade());
    }

    @Override
    public int compareTo(Student other) {
        double thisAvg  = this.getAvgGrade();
        double otherAvg = other.getAvgGrade();

        if (Math.abs(thisAvg - otherAvg) < 0.001) {
            return 0;
        }
        return thisAvg > otherAvg ? 1 : -1;
    }
}