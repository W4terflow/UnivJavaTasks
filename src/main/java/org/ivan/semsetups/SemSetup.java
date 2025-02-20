package org.ivan.semsetups;

import org.ivan.jdbc.ConnectionManager;
import org.ivan.tasks.actions.OptionsList;

import java.sql.SQLException;

public interface SemSetup {
    void initOptions(OptionsList optionsList);
    void initTables(ConnectionManager connectionManager) throws SQLException;
    String getSeminarDatabaseName();
}
