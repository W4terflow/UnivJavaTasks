package org.ivan.jdbc.auth;

public class UnivPcPostgresCredentials implements IDatabaseCredentials {
    @Override
    public String getDatabaseUrl() {
        return "jdbc:postgresql://localhost:5432/";
    }

    @Override
    public String getUsername() {
        return "postgres";
    }

    @Override
    public String getPassword() {
        return "postgres";
    }
}
