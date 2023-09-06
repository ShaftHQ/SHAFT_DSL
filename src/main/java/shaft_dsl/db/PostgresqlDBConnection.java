package shaft_dsl.db;


import com.shaft.tools.io.internal.ReportManagerHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresqlDBConnection extends DBConnection{

    public PostgresqlDBConnection(String dbServerIp, String dbPort, String dbName, String username, String password) {
        super(dbServerIp, dbPort, dbName, username, password);
    }

    @Override
    public Connection open() {
        try {
            if (System.getProperty("databaseLoginTimeout") == null) {
//                PropertyFileManager.readPropertyFiles();
            }
            DriverManager.setLoginTimeout(Integer.parseInt(System.getProperty("databaseLoginTimeout")));
            connection = DriverManager.getConnection(connectionString, username, password);
        } catch (SQLException rootCauseException) {
            ReportManagerHelper.log(rootCauseException);
            DBReporter.failAction(connectionString, rootCauseException);
        }
        DBLogger.logConnectionStatus(this);
        return connection;
    }

    @Override
    String getString(String dbServerIP, String dbPort, String dbName) {
        return "jdbc:postgresql://" + dbServerIP + ":" + dbPort + "/" + dbName;
//        jdbc:postgresql://localhost:5432/example"
    }

}
