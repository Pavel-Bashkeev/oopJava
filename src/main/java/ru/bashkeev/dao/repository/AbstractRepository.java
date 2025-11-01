package ru.bashkeev.dao.repository;

import ru.bashkeev.dao.database.ConnectionManager;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<T, ID> extends BaseRepository implements CrudRepository<T, ID> {
    protected final String tableName;
    protected final Class<T> entityClass;

    public AbstractRepository(ConnectionManager connectionManager, String tableName, Class<T> entityClass) {
        super(connectionManager);
        this.tableName = tableName;
        this.entityClass = entityClass;
    }

    @SneakyThrows
    @Override
    public List<T> findAll() {
        String sql = "SELECT * FROM " + tableName;

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            List<T> entities = new ArrayList<>();
            while (resultSet.next()) {
                entities.add(mapResultSetToEntity(resultSet));
            }
            return entities;
        }
    }

    @SneakyThrows
    @Override
    public Optional<T> findById(ID id) {
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(mapResultSetToEntity(resultSet));
            }
            return Optional.empty();
        }
    }

    @SneakyThrows
    @Override
    public T save(T entity) {
        if (getId(entity) == null) {
            return insert(entity);
        } else {
            update(entity);
            return entity;
        }
    }

    @SneakyThrows
    protected T insert(T entity) {
        Field[] fields = entityClass.getDeclaredFields();
        List<Field> nonIdFields = new ArrayList<>();

        for (Field field : fields) {
            if (!field.getName().equals("id")) {
                nonIdFields.add(field);
            }
        }

        StringBuilder columns = new StringBuilder();
        StringBuilder placeholders = new StringBuilder();

        for (int i = 0; i < nonIdFields.size(); i++) {
            if (i > 0) {
                columns.append(", ");
                placeholders.append(", ");
            }
            columns.append(toSnakeCase(nonIdFields.get(i).getName()));
            placeholders.append("?");
        }

        String sql = "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + placeholders + ")";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            for (int i = 0; i < nonIdFields.size(); i++) {
                Field field = nonIdFields.get(i);
                field.setAccessible(true);
                statement.setObject(i + 1, field.get(entity));
            }

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                setId(entity, (ID) generatedKeys.getObject(1));
            }

            return entity;
        }
    }

    @SneakyThrows
    @Override
    public void update(T entity) {
        Field[] fields = entityClass.getDeclaredFields();
        List<Field> nonIdFields = new ArrayList<>();

        for (Field field : fields) {
            if (!field.getName().equals("id")) {
                nonIdFields.add(field);
            }
        }

        StringBuilder setClause = new StringBuilder();
        for (int i = 0; i < nonIdFields.size(); i++) {
            if (i > 0) {
                setClause.append(", ");
            }
            setClause.append(toSnakeCase(nonIdFields.get(i).getName())).append(" = ?");
        }

        String sql = "UPDATE " + tableName + " SET " + setClause + " WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            for (int i = 0; i < nonIdFields.size(); i++) {
                Field field = nonIdFields.get(i);
                field.setAccessible(true);
                statement.setObject(i + 1, field.get(entity));
            }

            statement.setObject(nonIdFields.size() + 1, getId(entity));
            statement.executeUpdate();
        }
    }

    @SneakyThrows
    @Override
    public void deleteById(ID id) {
        String sql = "DELETE FROM " + tableName + " WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setObject(1, id);
            statement.executeUpdate();
        }
    }

    @SneakyThrows
    @Override
    public void clear() {
        String sql = "TRUNCATE TABLE " + tableName;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        }
    }

    @SneakyThrows
    protected ID getId(T entity) {
        Field idField = entityClass.getDeclaredField("id");
        idField.setAccessible(true);
        return (ID) idField.get(entity);
    }

    @SneakyThrows
    protected void setId(T entity, ID id) {
        Field idField = entityClass.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(entity, id);
    }

    protected String toSnakeCase(String camelCase) {
        return camelCase.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
    }

    protected abstract T mapResultSetToEntity(ResultSet resultSet) throws SQLException;
}
