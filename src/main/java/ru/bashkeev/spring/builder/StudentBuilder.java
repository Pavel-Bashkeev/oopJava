package ru.bashkeev.spring.builder;

import org.springframework.stereotype.Component;
import ru.bashkeev.school.Student;
import ru.bashkeev.school.interfaces.GradeValidator;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentBuilder {

    private final GradeValidator gradeValidator;

    public StudentBuilder(GradeValidator gradeValidator) {
        this.gradeValidator = gradeValidator;
    }

    public Student create(String name) {
        return new Student(name, gradeValidator, new ArrayList<>());
    }

    public Student create(String name, List<Integer> grades) {
        return new Student(name, gradeValidator, new ArrayList<>(grades));
    }
}
