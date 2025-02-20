package org.ivan;

import org.ivan.jdbc.ConnectionManager;
import org.ivan.jdbc.auth.HomePcPostgresCredentials;
import org.ivan.jdbc.auth.IDatabaseCredentials;
import org.ivan.semsetups.SemSetup;
import org.ivan.semsetups.Seminar1;
import org.ivan.tasks.actions.OptionsList;
import org.ivan.tasks.actions.optiontypes.OperationOption;
import org.ivan.utils.InputUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    protected static Scanner scanner = new Scanner(System.in);

    private static final SemSetup semSetup = new Seminar1();
    private static final IDatabaseCredentials credentials = new HomePcPostgresCredentials();
    private static final ConnectionManager connectionManager = new ConnectionManager(credentials);
    private static final OptionsList optionsList = new OptionsList();

    private static void mainLoop() {
        while (true) {
            System.out.println(optionsList.formatOptionsList());
            while (true) {
                int number = 0;
                try {
                    number = InputUtils.inputInt(scanner);
                } catch (NoSuchElementException e) {
                    System.exit(0);
                }

                Optional<OperationOption> option = optionsList.getExpressionByNumber(number);
                if(option.isPresent()) {
                    try {
                        option.get().activate(scanner, connectionManager);
                    } catch (SQLException | IOException e) {
                        e.printStackTrace();
                    }
                    break;
                } else {
                    System.out.println("Нет такой операции, попробуйте снова");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        try {
            connectionManager.establishConnection(semSetup.getSeminarDatabaseName());
        } catch (SQLException e) {
            System.out.println("Не удалось подключиться к базе данных: " + e.getMessage());
            System.exit(1);
        }

        semSetup.initOptions(optionsList);
        try {
            semSetup.initTables(connectionManager);
        } catch (SQLException e) {
            System.out.println("Ошибка на инициализации таблиц: " + e.getMessage());
            System.exit(1);
        }

        System.out.println();
        mainLoop();
    }
}
