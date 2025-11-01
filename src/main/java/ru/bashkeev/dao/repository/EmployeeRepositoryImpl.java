package ru.bashkeev.dao.repository;

import ru.bashkeev.dao.database.ConnectionManager;
import ru.bashkeev.dao.entity.Employee;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryImpl extends AbstractRepository<Employee, Integer> implements EmployeeRepository {

    public EmployeeRepositoryImpl(ConnectionManager connectionManager) {
        super(connectionManager, "employees", Employee.class);
    }

    @Override
    @SneakyThrows
    protected Employee mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new Employee(
                resultSet.getInt("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("email"),
                resultSet.getInt("department_id")
        );
    }

    @SneakyThrows
    @Override
    public Optional<Employee> findByEmail(String email) {
        String sql = "SELECT * FROM " + tableName + " WHERE email = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(mapResultSetToEntity(resultSet));
            }
            return Optional.empty();
        }
    }

    @SneakyThrows
    @Override
    public List<Employee> findByDepartmentId(Integer departmentId) {
        String sql = "SELECT * FROM " + tableName + " WHERE department_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, departmentId);
            ResultSet resultSet = statement.executeQuery();

            List<Employee> employees = new ArrayList<>();
            while (resultSet.next()) {
                employees.add(mapResultSetToEntity(resultSet));
            }
            return employees;
        }
    }

    @SneakyThrows
    @Override
    public Employee save(Employee employee) throws RuntimeException {
        if (existsByEmail(employee.getEmail())) {
            throw new RuntimeException("Employee with email '" + employee.getEmail() + "' already exists");
        }
        return super.save(employee);
    }

    @SneakyThrows
    @Override
    public void update(Employee employee) throws RuntimeException {
        Optional<Employee> existingEmployee = findByEmail(employee.getEmail());
        if (existingEmployee.isPresent() && !existingEmployee.get().getId().equals(employee.getId())) {
            throw new RuntimeException("Employee with email '" + employee.getEmail() + "' already exists");
        }
        super.update(employee);
    }

    @SneakyThrows
    @Override
    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE email = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
            return false;
        }
    }
}
