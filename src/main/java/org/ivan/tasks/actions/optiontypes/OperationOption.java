package org.ivan.tasks.actions.optiontypes;

import org.ivan.jdbc.ConnectionManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public abstract class OperationOption {
    public abstract String getOptionCode();

    public abstract String getOptionDescription();

    public abstract void activate(Scanner scanner, ConnectionManager connectionManager) throws SQLException, IOException;
}
