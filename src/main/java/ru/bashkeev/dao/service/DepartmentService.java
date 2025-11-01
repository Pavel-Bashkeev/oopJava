package ru.bashkeev.dao.service;

import ru.bashkeev.dao.database.ConnectionManager;
import ru.bashkeev.dao.entity.Department;
import ru.bashkeev.dao.repository.DepartmentRepository;
import ru.bashkeev.dao.repository.DepartmentRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService() {
        ConnectionManager connectionManager = new ConnectionManager();
        this.departmentRepository = new DepartmentRepositoryImpl(connectionManager);
    }

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(Integer id) {
        return departmentRepository.findById(id);
    }

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public void updateDepartment(Department department) {
        departmentRepository.update(department);
    }

    public void deleteDepartment(Integer id) {
        departmentRepository.deleteById(id);
    }

    public void clearAll() {
        departmentRepository.clear();
    }
}
