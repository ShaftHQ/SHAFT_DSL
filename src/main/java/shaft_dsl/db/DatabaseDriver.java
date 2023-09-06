package shaft_dsl.db;


//import com.shaft.tools.io.reporting.ReportManagerHelper;
import com.shaft.tools.io.internal.ReportManagerHelper;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseDriver {

    public DBConnection dbConnection ;
    public DatabaseDriver(DBConnection dbConnection) {
       this.dbConnection =dbConnection;
    }
    public ResultObject executeQuery(String sql) {
        ResultSet resultSet = null;
            dbConnection.open();
        try {resultSet =dbConnection.getStatement().executeQuery(sql);}
        catch (SQLException | NullPointerException rootCauseException) {
            ReportManagerHelper.log(rootCauseException);
            DBReporter.failAction(DBLogger.getReportMessage("SELECT", sql), rootCauseException);
        }
        DBLogger.logSelectStmtResultStatus(sql, resultSet);
//        dbConnection.close();
        return new ResultObject(resultSet);
    }

    private int executeCommand(String sql, String queryType) {
        var updatedRows = 0;
        dbConnection.open();
        try {updatedRows = dbConnection.getStatement().executeUpdate(sql);
            DBReporter.passAction(sql);
        } catch (SQLException | NullPointerException rootCauseException) {
            ReportManagerHelper.log(rootCauseException);
            DBReporter.failAction(DBLogger.getReportMessage(queryType, sql), rootCauseException);
            //to be updated
        }
        dbConnection.close();
        return updatedRows;
    }

    public int executeUpdate(String sql) {
        return executeCommand(sql,"UPDATE");
    }

    public int executeInsert(String sql) {
        return executeCommand(sql,"INSERT");

    }

    public int executeDelete(String sql) {
        return executeCommand(sql,"DELETE");

    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
    public void close() {
        dbConnection.close();

    }
}
