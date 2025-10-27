package ru.bashkeev.spring.postprocessor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.bashkeev.spring.annotation.Validate;
import ru.bashkeev.spring.validation.ValidatorRule;

import static org.junit.jupiter.api.Assertions.*;

class ValidationBeanPostProcessorFailureTest {

    @Test
    void testValidationFailure_WhenBeanIsInvalid() {
        BeanCreationException exception = assertThrows(BeanCreationException.class, () -> {
            try (var context = new AnnotationConfigApplicationContext(FailureTestConfig.class)) {
            }
        });

        assertTrue(exception.getMessage().contains("Ошибка валидации бина 'invalidBean'"));
        assertTrue(exception.getMessage().contains("правилом 'alwaysFailRule'"));
        assertTrue(exception.getMessage().contains("Validation always fails"));
    }

    @Configuration
    @Import(ValidationBeanPostProcessor.class)
    static class FailureTestConfig {
        @Bean
        public ValidatorRule alwaysFailRule() {
            return obj -> {
                throw new Exception("Validation always fails");
            };
        }

        @Bean
        public InvalidBean invalidBean() {
            return new InvalidBean();
        }
    }

    @Validate(rule = "alwaysFailRule")
    static class InvalidBean {
        private String data = "invalid";
    }
}