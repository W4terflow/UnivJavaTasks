package org.ivan.jdbc.utils;

import org.apache.poi.ss.usermodel.*;
import org.ivan.jdbc.ConnectionManager;
import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class DbFileUtils {
    public static File getTableAsFile(ConnectionManager connectionManager, String tableName, File folder) throws SQLException, IOException {
        String query = "SELECT * FROM " + tableName;

        CopyManager copyManager = new CopyManager((BaseConnection) connectionManager.getConnection());

        File sheetFile = new File(folder, "tmp/sheet.csv");
        sheetFile.getParentFile().mkdirs();

        try(FileOutputStream fileOutputStream = new FileOutputStream(sheetFile)) {
            copyManager.copyOut("COPY (" + query + ") TO STDOUT WITH (FORMAT CSV, HEADER, DELIMITER ';')", fileOutputStream); // , FORCE_QUOTE *
        }
        return sheetFile;
    }

    public static void readCsvToSheet(File csvFile, Workbook workbook, Sheet sheet, StringBuilder textForm) throws IOException {
        DataFormatter formatter = new DataFormatter();
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        try (FileInputStream fis = new FileInputStream(csvFile); Scanner scanner = new Scanner(fis)) {
            int rowIndex = 0;
            boolean empty = true;
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(";");
                Row row = sheet.createRow(rowIndex++);
                for (int i = 0; i < data.length; i++) {
                    Cell cell = row.createCell(i);

                    cell.setCellValue(formatter.formatCellValue(cell, evaluator));
                    cell.setCellValue(data[i]);

                    textForm.append(data[i]).append(' ');
                    if (!data[i].isEmpty()) empty = false;
                }
                if (!empty) textForm.append("\n");
            }

            if (empty) textForm.append("(Нет данных)\n");
        }
    }
}
