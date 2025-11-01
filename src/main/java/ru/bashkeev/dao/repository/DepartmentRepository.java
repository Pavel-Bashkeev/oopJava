package ru.bashkeev.dao.repository;

import ru.bashkeev.dao.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository {
    List<Department> findAll();

    Optional<Department> findById(Integer id);

    Department save(Department department);

    void deleteById(Integer id);

    void update(Department department);

    void clear();
}
