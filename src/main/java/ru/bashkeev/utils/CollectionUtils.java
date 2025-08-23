package ru.bashkeev.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CollectionUtils {
    public static <T, R> List<R> map(List<T> list, Function<T, R> mapper) {
        if (list == null || mapper == null) {
            throw new IllegalArgumentException("Параметры не могут быть null");
        }

        List<R> result = new ArrayList<R>();
        for (T t : list) {
            result.add(mapper.apply(t));
        }

        return result;
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        if (list == null || predicate == null) {
            throw new IllegalArgumentException("Параметры не могут быть null");
        }

        List<T> result = new ArrayList<>();
        for (T item : list) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public static <T> Optional<T> reduce(List<T> list, Reduction<T> reduction) {
        if (list == null || reduction == null) {
            throw new IllegalArgumentException("Параметры не могут быть null");
        }

        if (list.isEmpty()) {
            return Optional.empty();
        }

        T result = list.getFirst();
        for (int i = 1; i < list.size(); i++) {
            result = reduction.apply(result, list.get(i)); // Для одного элемента цикл не выполняется
        }

        return Optional.of(result);
    }

    public static <T> T reduce(List<T> list, Reduction<T> operator, T identity) {
        if (list == null || operator == null) {
            throw new IllegalArgumentException("Параметры не могут быть null");
        }

        T result = identity;
        for (T item : list) {
            result = operator.apply(result, item);
        }
        return result;
    }

    public static <T, R> R collect(List<T> list, Provider<R> provider, Accumulator<R, T> accumulator) {
        if (list == null || provider == null || accumulator == null) {
            throw new IllegalArgumentException("Параметры не могут быть null");
        }

        R result = provider.get();
        for (T item : list) {
            accumulator.accept(result, item);
        }
        return result;
    }
}
