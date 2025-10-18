package ru.bashkeev.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloConfig {
    @Bean
    public String hello() {
        return "Hello world";
    }
}
