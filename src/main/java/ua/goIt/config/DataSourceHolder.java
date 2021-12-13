package ua.goIt.config;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.util.Properties;

public class DataSourceHolder {

    private static DataSourceHolder value;

    private final DataSource dataSource;

    private DataSourceHolder() {
        Properties props = AppProperties.getProperties();
        PGSimpleDataSource dataSource = initDb(props);
        this.dataSource = dataSource;
    }

    private PGSimpleDataSource initDb(Properties props) {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setServerNames(new String[]{props.getProperty("db.host")});
        dataSource.setPortNumbers(new int[]{Integer.parseInt(props.getProperty("db.port"))});
        dataSource.setDatabaseName(props.getProperty("db.databaseName"));
        dataSource.setUser(props.getProperty("db.username"));
        dataSource.setPassword(props.getProperty("db.password"));
        return dataSource;
    }


    public static DataSource getDataSource() {
        if (value == null) {
            value = new DataSourceHolder();
        }
        return value.dataSource;
    }
}
