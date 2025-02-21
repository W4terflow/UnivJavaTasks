package org.ivan.tasks.actions.optiontypes.variations.sem1;

import org.ivan.jdbc.ConnectionManager;
import org.ivan.tasks.actions.optiontypes.OperationOption;
import org.ivan.utils.InputUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CreateTableOperationOption implements OperationOption {
    @Override
    public String getOptionCode() {
        return "show_all_tables";
    }

    @Override
    public String getOptionDescription() {
        return "Создать таблицу в PostgreSQL";
    }

    @Override
    public void activate(Scanner scanner, ConnectionManager connectionManager) throws SQLException {
        System.out.print("Введите название таблицы: ");
        String tableName = InputUtils.inputString(scanner);

        System.out.print("Введите определения столбцов (скобки добавлены автоматически): ");
        String parameters = InputUtils.inputString(scanner, true);

        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + "(" + parameters + ");";
        try (PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        }
        System.out.println("Создана таблица " + tableName);
    }
}
