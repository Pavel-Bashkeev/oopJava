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
                .collect(ArrayList::new, List::add);
        System.out.println("List<String> strs: " + ints);

        List<Integer> generatedInts = DataStream.of(() -> List.of("100", "200", "300", "400").iterator())
                .map(Integer::parseInt)
                .filter(x -> x > 150)
                .filter(x -> x < 400)
                .collect(ArrayList::new, List::add);
        System.out.println("List<Integer> generatedInts: " + generatedInts);

        Integer sum = DataStream.of(strs)
                .map(Integer::parseInt)
                .map(x -> x / 10)
                .map(x -> x * 10)
                .reduce(0, Integer::sum);
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
    private Supplier<Iterator<T>> dataSupplier;
    private List<Function>        functions  = new ArrayList<>();
    private List<Predicate<T>>    predicates = new ArrayList<>();

    private DataStream(Supplier<Iterator<T>> dataSupplier) {
        this.dataSupplier = dataSupplier;
    }

    private DataStream(List<T> elements) {
        this.dataSupplier = () -> elements.iterator();
    }

    public static <T> DataStream<T> of(List<T> elements) {
        return new DataStream<>(elements);
    }

    public static <T> DataStream<T> of(Supplier<Iterator<T>> dataSupplier) {
        return new DataStream<>(dataSupplier);
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
        processElements(element -> putter.accept(collection, element));
        return collection;
    }

    public T reduce(T identity, BinaryOperator<T> accumulator) {
        T[] resultHolder = (T[]) new Object[1];
        resultHolder[0] = identity;

        processElements(element ->
                resultHolder[0] = accumulator.apply(resultHolder[0], element));

        return resultHolder[0];
    }

    private void processElements(Consumer<T> action) {
        Iterator<T> iterator = dataSupplier.get();

        while (iterator.hasNext()) {
            T element = iterator.next();
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
                action.accept(finalResult);
            }
        }
    }
}