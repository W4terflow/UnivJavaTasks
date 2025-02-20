package org.ivan.jdbc.auth;

public interface IDatabaseCredentials {
    String getDatabaseUrl();
    String getUsername();
    String getPassword();
}
