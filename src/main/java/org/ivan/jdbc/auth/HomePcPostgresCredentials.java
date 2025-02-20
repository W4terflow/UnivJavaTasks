package org.ivan.jdbc.auth;

public class HomePcPostgresCredentials implements IDatabaseCredentials {
    @Override
    public String getDatabaseUrl() {
        return "jdbc:postgresql://localhost:5401/";
    }

    @Override
    public String getUsername() {
        return "ivan";
    }

    @Override
    public String getPassword() {
        return "123456";
    }
}
