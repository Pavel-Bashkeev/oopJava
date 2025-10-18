package ru.bashkeev.spring.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.bashkeev.utils.Predicate;

@Component
public class RangePredicate implements Predicate<Integer> {
    private final int max;
    private final int min;

    @Autowired
    public RangePredicate(@Qualifier("min") int min, @Qualifier("max") int max) {
        this.max = max;
        this.min = min;
    }

    public boolean test(Integer t) {
        return t >= min && t <= max;
    }
}
