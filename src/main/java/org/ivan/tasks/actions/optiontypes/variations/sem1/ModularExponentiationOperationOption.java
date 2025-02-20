package org.ivan.tasks.actions.optiontypes.variations.sem1;

import org.ivan.jdbc.ConnectionManager;
import org.ivan.tasks.actions.optiontypes.SavableExpressionOperationOption;
import org.ivan.utils.InputUtils;

import java.sql.SQLException;
import java.util.Scanner;

public class ModularExponentiationOperationOption extends SavableExpressionOperationOption {
    private int value_base;
    private int value_exp;
    private int value_mod;

    @Override
    public String getOptionCode() {
        return "mod_exponentiation";
    }

    @Override
    public String getOptionDescription() {
        return "Возведение числа в модуль, результат сохранить в PostgreSQL с последующим выводом в консоль";
    }

    @Override
    protected String getExpressionText() {
        return value_base + " ^ " + value_exp + " (mod " + value_mod + ")";
    }

    @Override
    protected Object calculateResult() {
        return (value_base ^ value_exp) % value_mod;
    }

    @Override
    public void activate(Scanner scanner, ConnectionManager connectionManager) throws SQLException {
        System.out.print("Введите основание число: ");
        value_base = InputUtils.inputInt(scanner);

        System.out.print("Введите степень: ");
        value_exp = InputUtils.inputInt(scanner);

        System.out.print("Введите модуль: ");
        value_mod = InputUtils.inputInt(scanner);
        
        super.saveToDatabase(connectionManager, getExpressionText(), calculateResult());
    }
}
