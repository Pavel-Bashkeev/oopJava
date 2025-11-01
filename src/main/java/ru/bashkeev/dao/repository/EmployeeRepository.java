package ru.bashkeev.dao.repository;

import ru.bashkeev.dao.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    List<Employee> findAll();
    Optional<Employee> findById(Integer id);
    Optional<Employee> findByEmail(String email);
    List<Employee> findByDepartmentId(Integer departmentId);
    Employee save(Employee employee) throws RuntimeException;
    void update(Employee employee) throws RuntimeException;
    void deleteById(Integer id);
    void clear();
    boolean existsByEmail(String email);
}
