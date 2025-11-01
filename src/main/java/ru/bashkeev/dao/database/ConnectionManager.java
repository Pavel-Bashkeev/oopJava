package ru.bashkeev.dao.database;

import ru.bashkeev.dao.config.DatabaseConfig;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class ConnectionManager {
    private final String url;
    private final String username;
    private final String password;

    public ConnectionManager() {
        this.url      = DatabaseConfig.getUrl();
        this.username = DatabaseConfig.getUsername();
        this.password = DatabaseConfig.getPassword();
        initializeDatabase();
    }

    public ConnectionManager(String url, String username, String password) {
        this.url      = url;
        this.username = username;
        this.password = password;
    }

    @SneakyThrows
    public Connection getConnection() {
        return DriverManager.getConnection(url, username, password);
    }

    @SneakyThrows
    private void initializeDatabase() {
        String[] schemaFiles = {"departmentsSchema.sql", "employeesSchema.sql"};

        for (String schemaFile : schemaFiles) {
            try (Connection connection = getConnection();
                 Statement statement = connection.createStatement();
                 InputStream input = getClass().getClassLoader().getResourceAsStream(schemaFile)) {

                if (input == null) {
                    throw new RuntimeException("Unable to find " + schemaFile);
                }

                try (Scanner scanner = new Scanner(input)) {
                    StringBuilder sql = new StringBuilder();
                    while (scanner.hasNextLine()) {
                        sql.append(scanner.nextLine());
                    }
                    statement.execute(sql.toString());
                }
            } catch (Exception e) {
                System.err.println("Error initializing database from " + schemaFile + ": " + e.getMessage());
            }
        }
    }
}
