package ru.bashkeev.dao.repository;

import ru.bashkeev.dao.database.ConnectionManager;
import ru.bashkeev.dao.entity.Department;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentRepositoryImpl extends AbstractRepository<Department, Integer> implements DepartmentRepository {

    public DepartmentRepositoryImpl(ConnectionManager connectionManager) {
        super(connectionManager, "departments", Department.class);
    }

    @Override
    @SneakyThrows
    protected Department mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new Department(
                resultSet.getInt("id"),
                resultSet.getString("name")
        );
    }
}
