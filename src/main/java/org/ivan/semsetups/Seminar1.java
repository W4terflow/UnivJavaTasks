package org.ivan.semsetups;

import org.ivan.jdbc.ConnectionManager;
import org.ivan.tasks.actions.OptionsList;
import org.ivan.tasks.actions.optiontypes.OperationOption;
import org.ivan.tasks.actions.optiontypes.variations.sem1.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Seminar1 implements SemSetup {
    @Override
    public void initOptions(OptionsList optionsList) {
        optionsList
                .addOption(new ShowAllTablesOperationOption())
                .addOption(new CreateTableOperationOption())
                .addOption(new SumOperationOption())
                .addOption(new SubtractionOperationOption())
                .addOption(new MultiplicationOperationOption())
                .addOption(new DivisionOperationOption())
                .addOption(new ModularDivisionOperationOption())
                .addOption(new ModularExponentiationOperationOption())
                .addOption(new ExponentiationOperationOption())
                .addOption(new TablesExcelSaveOperationOption())
        ;
    }

    @Override
    public void initTables(ConnectionManager connectionManager) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS math_expressions " +
                "(id SERIAL PRIMARY KEY," +
                "expression varchar(255)," +
                "result varchar(255))";

        try (PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public String getSeminarDatabaseName() {
        return "java_tasks_sem1";
    }
}
