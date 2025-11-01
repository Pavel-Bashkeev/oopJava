package ru.bashkeev.dao.repository;

import lombok.RequiredArgsConstructor;
import ru.bashkeev.dao.database.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;

@RequiredArgsConstructor
public abstract class BaseRepository {
    protected final ConnectionManager connectionManager;

    protected Connection getConnection() throws SQLException {
        return connectionManager.getConnection();
    }
}
