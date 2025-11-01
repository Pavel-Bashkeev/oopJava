package ru.bashkeev.dao.service;

import ru.bashkeev.dao.database.ConnectionManager;
import ru.bashkeev.dao.entity.Employee;
import ru.bashkeev.dao.repository.EmployeeRepository;
import ru.bashkeev.dao.repository.EmployeeRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService() {
        ConnectionManager connectionManager = new ConnectionManager();
        this.employeeRepository = new EmployeeRepositoryImpl(connectionManager);
    }

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Integer id) {
        return employeeRepository.findById(id);
    }

    public Optional<Employee> getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    public List<Employee> getEmployeesByDepartment(Integer departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void updateEmployee(Employee employee) {
        employeeRepository.update(employee);
    }

    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }

    public void clearAll() {
        employeeRepository.clear();
    }

    public boolean employeeExistsByEmail(String email) {
        return employeeRepository.existsByEmail(email);
    }
}
