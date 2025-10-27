package ru.bashkeev.spring.service;

import org.springframework.stereotype.Service;
import ru.bashkeev.spring.annotation.EnableCaching;

@Service
@EnableCaching
public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public double complexCalculation(double a, double b) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return a * b + Math.sqrt(a + b);
    }
}