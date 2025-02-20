package org.ivan.tasks.actions.optiontypes;

import org.ivan.jdbc.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class SavableExpressionOperationOption extends OperationOption {
    protected abstract String getExpressionText();

    protected abstract Object calculateResult();

    protected String getTableName() {
        return "math_expressions";
    }

    protected void saveToDatabase(ConnectionManager connectionManager, String expression, Object result) throws SQLException {
        String sql = "INSERT INTO " + getTableName() + " (expression, result) VALUES(?, ?)";
        try (PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, expression);
            preparedStatement.setObject(2, result);
            preparedStatement.executeUpdate();
        }
        System.out.println("В таблицу " + getTableName() + " добавлен результат выражения \"" + expression + "\", равный " + result);
    }
}
