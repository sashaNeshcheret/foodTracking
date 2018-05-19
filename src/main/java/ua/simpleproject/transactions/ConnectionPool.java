package ua.simpleproject.transactions;

import ua.simpleproject.exception.ConnectionException;

import javax.naming.Context;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static final ConnectionPool CONNECTION_POOL = new ConnectionPool();

    private static DataSource dataSource;

    public void setDataSource(DataSource dataSources) {
        dataSource = dataSources;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public static ConnectionPool getConnectionPool() {
        return CONNECTION_POOL;
    }

    public static Connection getConnection() throws ConnectionException {
        Context context;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ConnectionException("Сталася помилка з'єднання з базою", e);
        }
        return connection;
    }
}
