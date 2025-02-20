package org.ivan.tasks.actions.optiontypes.variations.sem1;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ivan.jdbc.ConnectionManager;
import org.ivan.jdbc.utils.DatabaseUtils;
import org.ivan.jdbc.utils.DbFileUtils;
import org.ivan.tasks.actions.optiontypes.OperationOption;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TablesExcelSaveOperationOption extends OperationOption {
    private List<String> tableNames;

    @Override
    public String getOptionCode() {
        return "save_tables_to_excel";
    }

    @Override
    public String getOptionDescription() {
        return "Сохранить все данные из PostgreSQL в Excel и вывести на экран";
    }

    public TablesExcelSaveOperationOption setTableNamesList(List<String> tableNames) {
        this.tableNames = tableNames;
        return this;
    }

    @Override
    public void activate(Scanner scanner, ConnectionManager connectionManager) throws SQLException, IOException {
        File folder = new File(System.getProperty("user.dir") + "/output");
        System.out.println("Сохраняется в директорию " + folder.getCanonicalPath() + "...");

        StringBuilder textForm = new StringBuilder("База данных:\n\n");
        try(Workbook workbook = new XSSFWorkbook()) {
            int i = -1;
            List<String> allTableNames = this.tableNames;
            if (allTableNames == null) allTableNames = DatabaseUtils.getAllTableNames(connectionManager);

            for (String tableName : allTableNames) {
                i++;
                textForm.append("Таблица \"").append(tableName).append("\":\n");

                Sheet sheet = workbook.createSheet(tableName);
                File file = DbFileUtils.getTableAsFile(connectionManager, tableName, folder);

                DbFileUtils.readCsvToSheet(file, workbook, sheet, textForm);

                if(i + 1 == allTableNames.size()) {
                    file.delete();
                } else {
                    textForm.append("\n");
                }
            }

            String databaseName = connectionManager.getConnection().getMetaData().getDatabaseProductName();
            File excelFile = new File(folder, databaseName + ".xlsx");
            excelFile.getParentFile().mkdirs();
            try (FileOutputStream fileOut = new FileOutputStream(excelFile)) {
                workbook.write(fileOut);
            }

            System.out.println(textForm);
        }
    }
}
