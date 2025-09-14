package ru.bashkeev.db;

import java.util.function.Function;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Db {
    private final List<String>              records;
    private final Map<Class<?>, Function<String, ?>> converters = new HashMap<>();

    public Db() {
        this.records = new ArrayList<>();
    }

    public Db(List<String> records) {
        this.records = records;
    }

    public void addRecord(String record) {
        records.add(record);
    }

    public void setRecord(int index, String record) {
        if (index < 0 || index >= records.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        records.set(index, record);
    }

    public <T> void addConverter(Class<T> type, Function<String, T> converter) {
        converters.put(type, converter);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(int index, Class<T> type) {
        if (index < 0 || index >= records.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        String record = records.get(index);
        Function<String, ?> converter = converters.get(type);

        if (converter == null) {
            throw new IllegalArgumentException("No converter registered for type: " + type);
        }

        return (T) converter.apply(record);
    }
}
