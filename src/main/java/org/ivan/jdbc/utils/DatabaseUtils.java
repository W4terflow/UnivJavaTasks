package org.ivan.jdbc.utils;

import org.ivan.jdbc.ConnectionManager;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtils {
    public static List<String> getAllTableNames(ConnectionManager connectionManager) throws SQLException {
        List<String> tableNames = new ArrayList<>();

        DatabaseMetaData metaData = connectionManager.getConnection().getMetaData();
        String[] types = {"TABLE"};

        try (ResultSet resultSet = metaData.getTables(null, null, "%", types)) {
            while (resultSet.next()) {
                tableNames.add(resultSet.getString("TABLE_NAME"));
            }
        }

        return tableNames;
    }
}
