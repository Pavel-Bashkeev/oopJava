package ru.bashkeev.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class RangeRandomGenerator implements RandomGenerator {
    private final int          min;
    private final int          max;
    private final Random       random = new Random();
    private final Set<Integer> used   = new HashSet<>();

    @Autowired
    public RangeRandomGenerator(@Qualifier("min") int min, @Qualifier("max") int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public int next() {
        if (used.size() >= (max - min + 1)) {
            used.clear();
        }

        int value;
        do {
            value = random.nextInt(max - min + 1) + min;
        } while (used.contains(value));

        used.add(value);
        return value;
    }
}
