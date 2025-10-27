package ru.bashkeev.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.bashkeev.school.Student;
import ru.bashkeev.school.interfaces.GradeValidator;
import ru.bashkeev.spring.component.RangePredicate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    public GradeValidator rangeValidator(RangePredicate rangePredicate) {
        return rangePredicate::test;
    }

    @Bean
    public List<Student> students(GradeValidator rangeValidator) {
        return List.of(
                new Student("Иван", rangeValidator, new ArrayList<>(List.of(3, 4, 5))),
                new Student("Пётр", rangeValidator, new ArrayList<>(List.of(2, 5, 4))),
                new Student("Мария", rangeValidator, new ArrayList<>(List.of(5, 5, 5)))
        );
    }
}