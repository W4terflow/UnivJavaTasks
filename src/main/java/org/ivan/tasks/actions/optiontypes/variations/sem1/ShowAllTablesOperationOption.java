package org.ivan.tasks.actions.optiontypes.variations.sem1;

import org.ivan.jdbc.ConnectionManager;
import org.ivan.jdbc.utils.DatabaseUtils;
import org.ivan.tasks.actions.optiontypes.OperationOption;

import java.sql.SQLException;
import java.util.Scanner;

public class ShowAllTablesOperationOption extends OperationOption {
    @Override
    public String getOptionCode() {
        return "show_all_tables";
    }

    @Override
    public String getOptionDescription() {
        return "Вывести все таблицы из PostgreSQL";
    }

    @Override
    public void activate(Scanner scanner, ConnectionManager connectionManager) throws SQLException {
        StringBuilder sb = new StringBuilder("Список таблиц:\n");
        for (String tableName : DatabaseUtils.getAllTableNames(connectionManager)) {
            sb.append(tableName).append('\n');
        }
        System.out.println(sb.toString());
    }
}
