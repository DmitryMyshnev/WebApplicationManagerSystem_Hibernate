package ua.goIt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goIt.config.DataSourceHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbStatement {
    private static Connection connection;
    private static final Logger LOGGER = LogManager.getLogger(DbStatement.class);
    private static DbStatement dbStatement;

    private DbStatement() {
    }

    public static ResultSet executeStatementQuery(String query, ParameterSetter pSetter) {
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            pSetter.set(ps);
            return ps.executeQuery();
        } catch (SQLException e) {
            LOGGER.info(e.getSQLState());
            LOGGER.info(e.getMessage());
            return null;
        }
    }

    public static void executeStatementUpdate(String query, ParameterSetter pSetter) {
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            pSetter.set(ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info(e.getSQLState());
            LOGGER.info(e.getMessage());
        }
    }
   public static Connection createConnection(){
        if(connection == null){
            try {
                connection = DataSourceHolder.getDataSource().getConnection();
            } catch (SQLException e) {
                LOGGER.info(e.getSQLState());
                LOGGER.info(e.getMessage());
            }
        }
        return connection;
   }
    @FunctionalInterface
    public interface ParameterSetter {
        void set(PreparedStatement ps) throws SQLException;
    }

}
