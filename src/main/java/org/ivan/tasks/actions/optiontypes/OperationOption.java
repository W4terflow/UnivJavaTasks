package org.ivan.tasks.actions.optiontypes;

import org.ivan.jdbc.ConnectionManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public interface OperationOption {
    String getOptionCode();

    String getOptionDescription();

    void activate(Scanner scanner, ConnectionManager connectionManager) throws SQLException, IOException;
}
