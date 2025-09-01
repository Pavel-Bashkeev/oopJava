package ru.bashkeev.main;

import java.util.*;
import java.util.function.*;

public class Main implements Cloneable {
    public static void main(String[] args) {
        List<String> strs = List.of("123", "3456", "1");
        List<Integer> ints = DataStream.of(strs)
                .map(Integer::parseInt)
                .map(x -> x / 10)
                .map(x -> x * 10)
                .filter(x -> x > 100)
                .filter(x -> x > 150)
                .collect(ArrayList::new, (list, item) -> list.add(item));
        System.out.println(ints);

        Integer sum = DataStream.of(strs)
                .map(Integer::parseInt)
                .map(x -> x / 10)
                .map(x -> x * 10)
                .reduce(0, (a, b) -> a + b);
        System.out.println("Reduce sum: " + sum);
    }

    @Override
    public Main clone() {
        try {
            return (Main) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

class DataStream<T> {
    private List<T> elements;
    private List<Function> functions = new ArrayList<>();
    private List<Predicate<T>> predicates = new ArrayList<>();

    private DataStream(List<T> elements) {
        this.elements = elements;
    }

    public static <T> DataStream<T> of(List<T> elements) {
        return new DataStream<>(elements);
    }

    public <R> DataStream<R> map(Function<T, R> applier) {
        functions.add(applier);
        return (DataStream<R>) this;
    }

    public DataStream<T> filter(Predicate<T> predicate) {
        predicates.add(predicate);
        return this;
    }

    public <P> P collect(Supplier<P> creator, BiConsumer<P, T> putter) {
        P collection = creator.get();
        for (T element : elements) {
            Object processed = element;

            for (Function function : functions) {
                processed = function.apply(processed);
            }

            boolean passedAllFilters = true;
            for (Predicate<T> predicate : predicates) {
                @SuppressWarnings("unchecked")
                T castedProcessed = (T) processed;
                if (!predicate.test(castedProcessed)) {
                    passedAllFilters = false;
                    break;
                }
            }

            if (passedAllFilters) {
                @SuppressWarnings("unchecked")
                T finalResult = (T) processed;
                putter.accept(collection, finalResult);
            }
        }
        return collection;
    }

    public T reduce(T identity, BinaryOperator<T> accumulator) {
        T result = identity;
        for (T element : elements) {
            Object processed = element;

            for (Function function : functions) {
                processed = function.apply(processed);
            }

            boolean passedAllFilters = true;
            for (Predicate<T> predicate : predicates) {
                @SuppressWarnings("unchecked")
                T castedProcessed = (T) processed;
                if (!predicate.test(castedProcessed)) {
                    passedAllFilters = false;
                    break;
                }
            }

            if (passedAllFilters) {
                @SuppressWarnings("unchecked")
                T finalResult = (T) processed;
                result = accumulator.apply(result, finalResult);
            }
        }
        return result;
    }
}