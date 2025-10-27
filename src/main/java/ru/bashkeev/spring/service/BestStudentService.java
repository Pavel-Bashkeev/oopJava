package ru.bashkeev.spring.service;

import org.springframework.stereotype.Service;
import ru.bashkeev.school.Student;

import java.util.Comparator;
import java.util.List;

@Service
public class BestStudentService {
    private final List<Student> students;

    public BestStudentService(List<Student> students) {
        this.students = students;
    }

    public Student getBestStudent() {
        return students.stream()
                .max(Comparator.comparingDouble(Student::getAvgGrade))
                .orElseThrow(() -> new IllegalStateException("Нет студентов"));
    }
}
