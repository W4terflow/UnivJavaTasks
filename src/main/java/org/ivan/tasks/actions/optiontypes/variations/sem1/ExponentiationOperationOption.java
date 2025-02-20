package org.ivan.tasks.actions.optiontypes.variations.sem1;

import org.ivan.jdbc.ConnectionManager;
import org.ivan.tasks.actions.optiontypes.SavableExpressionOperationOption;
import org.ivan.utils.InputUtils;

import java.sql.SQLException;
import java.util.Scanner;

public class ExponentiationOperationOption extends SavableExpressionOperationOption {
    private int value1;
    private int value2;

    @Override
    public String getOptionCode() {
        return "exponentiation";
    }

    @Override
    public String getOptionDescription() {
        return "Возведение числа в степень, результат сохранить в PostgreSQL с последующим выводом в консоль";
    }

    @Override
    protected String getExpressionText() {
        return value1 + " ^ " + value2;
    }

    @Override
    protected Object calculateResult() {
        return value1 ^ value2;
    }

    @Override
    public void activate(Scanner scanner, ConnectionManager connectionManager) throws SQLException {
        System.out.print("Введите первое число: ");
        value1 = InputUtils.inputInt(scanner);

        System.out.print("Введите второе число: ");
        value2 = InputUtils.inputInt(scanner);
        
        super.saveToDatabase(connectionManager, getExpressionText(), calculateResult());
    }
}
