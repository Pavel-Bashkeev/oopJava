package ru.bashkeev.dao.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {
    List<T> findAll();
    Optional<T> findById(ID id);
    T save(T entity) throws RuntimeException;
    void update(T entity) throws RuntimeException;
    void deleteById(ID id);
    void clear();
}
