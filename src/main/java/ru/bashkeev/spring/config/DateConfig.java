package ru.bashkeev.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.Date;

@Configuration
public class DateConfig {
    @Bean
    @Lazy
    public Date startDate() {
        return new Date();
    }
}
