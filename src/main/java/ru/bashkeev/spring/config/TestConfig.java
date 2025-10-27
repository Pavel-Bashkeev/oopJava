package ru.bashkeev.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.bashkeev.spring.postprocessor.CachingBeanPostProcessor;
import ru.bashkeev.spring.service.CalculatorServiceImpl;

@Configuration
public class TestConfig {

    @Bean
    static CachingBeanPostProcessor cachingProcessor() {
        return new CachingBeanPostProcessor();
    }

    @Bean
    public CalculatorServiceImpl calculatorService() {
        return new CalculatorServiceImpl();
    }
}
