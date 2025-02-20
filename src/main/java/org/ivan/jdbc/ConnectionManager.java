package org.ivan.jdbc;

import org.ivan.jdbc.auth.IDatabaseCredentials;

import java.sql.*;

public class ConnectionManager {
    private final IDatabaseCredentials credentials;
    private Connection connection = null;

    public ConnectionManager(IDatabaseCredentials credentials) {
        this.credentials = credentials;
    }

    public Connection getConnection() {
        return connection;
    }

    private static boolean checkDatabaseExist(Connection connection, String databaseName) throws SQLException {
        String query = "SELECT 1 FROM pg_database WHERE datname = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, databaseName);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
    }

    private void createDatabaseIfNeeded(String databaseName) {
        try (Connection tempConnection = createConnection("postgres")) {
//            assert Objects.requireNonNull(tempConnection).isValid(2000);
            if(!checkDatabaseExist(tempConnection, databaseName)) {
                String databaseDefinition = "CREATE DATABASE " + databaseName + ";";
                assert tempConnection != null;
                try(PreparedStatement preparedStatement = tempConnection.prepareStatement(databaseDefinition)) {
                preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection(String databaseName) throws SQLException {
        if(!databaseName.equals("postgres")) createDatabaseIfNeeded(databaseName);

        return DriverManager.getConnection(
                this.credentials.getDatabaseUrl() + databaseName,
                this.credentials.getUsername(),
                this.credentials.getPassword()
        );
    }

    public void establishConnection(String databaseName) throws SQLException {
        connection = createConnection(databaseName);
        System.out.println("Подключение к базе данных " + databaseName + " успешно установлено");
    }
}
