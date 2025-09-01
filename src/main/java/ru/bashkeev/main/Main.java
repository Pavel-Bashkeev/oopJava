package ru.bashkeev.main;

import java.util.*;
import java.util.function.*;

public class Main implements Cloneable {
    public static void main(String[] args) {
        List<String> strs = List.of("123", "3456", "1");
        List<Integer> ints = DataStream.of(strs)
                .map(Integer::parseInt)
                .map(x -> x / 10)
                .map(String::valueOf)
                .filter(x -> x.length() > 2)
                .map(Integer::parseInt)
                .filter(x -> x > 150)
                .collect(ArrayList::new, List::add);
        System.out.println("List<String> strs: " + ints);

        List<Integer> generatedInts = DataStream.of(() -> List.of("100", "200", "300", "400").iterator())
                .map(Integer::parseInt)
                .filter(x -> x > 150)
                .filter(x -> x < 400)
                .collect(ArrayList::new, List::add);
        System.out.println("List<Integer> generatedInts: " + generatedInts);

        String concatenated = DataStream.of(strs)
                .map(Integer::parseInt)
                .map(x -> x / 10)
                .map(x -> x * 10)
                .map(String::valueOf)
                .reduce("", (a, b) -> a + b);
        System.out.println("String concatenated: " + concatenated);

        Integer sum = DataStream.of(strs)
                .map(Integer::parseInt)
                .map(x -> x / 10)
                .map(x -> x * 10)
                .reduce(0, Integer::sum);
        System.out.println("Integer sum: " + sum);
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
    private Supplier<Iterator<T>> dataSupplier;

    private DataStream(Supplier<Iterator<T>> dataSupplier) {
        this.dataSupplier = dataSupplier;
    }

    private DataStream(List<T> elements) {
        this.dataSupplier = elements::iterator;
    }

    public static <T> DataStream<T> of(List<T> elements) {
        return new DataStream<>(elements);
    }

    public static <T> DataStream<T> of(Supplier<Iterator<T>> dataSupplier) {
        return new DataStream<>(dataSupplier);
    }

    public <R> DataStream<R> map(Function<T, R> mapper) {
        return new DataStream<>(() -> new Iterator<R>() {
            private final Iterator<T> it = dataSupplier.get();

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public R next() {
                return mapper.apply(it.next());
            }
        });
    }

    public DataStream<T> filter(Predicate<T> predicate) {
        return new DataStream<>(() -> new Iterator<T>() {
            private final Iterator<T> it = dataSupplier.get();
            private T nextElement = null;
            private boolean hasNextElement = false;

            @Override
            public boolean hasNext() {
                while (!hasNextElement && it.hasNext()) {
                    T candidate = it.next();
                    if (predicate.test(candidate)) {
                        nextElement = candidate;
                        hasNextElement = true;
                    }
                }
                return hasNextElement;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                hasNextElement = false;
                return nextElement;
            }
        });
    }

    public <P> P collect(Supplier<P> creator, BiConsumer<P, T> putter) {
        P collection = creator.get();
        Iterator<T> iterator = dataSupplier.get();
        while (iterator.hasNext()) {
            putter.accept(collection, iterator.next());
        }
        return collection;
    }

    public T reduce(T identity, BinaryOperator<T> accumulator) {
        T result = identity;
        Iterator<T> iterator = dataSupplier.get();
        while (iterator.hasNext()) {
            result = accumulator.apply(result, iterator.next());
        }
        return result;
    }
}