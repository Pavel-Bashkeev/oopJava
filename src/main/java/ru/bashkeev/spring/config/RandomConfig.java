package ru.bashkeev.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Random;

@Configuration
public class RandomConfig {
    @Bean
    @Scope("prototype")
    public int random() {
        return new Random().nextInt(100);
    }
}
