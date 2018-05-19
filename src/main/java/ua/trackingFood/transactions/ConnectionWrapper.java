package ua.trackingFood.transactions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionWrapper implements AutoCloseable{
    private Connection connection;
    private boolean isTransaction;

    public ConnectionWrapper(Connection connection, boolean isTransaction) {
        this.connection = connection;
        this.isTransaction = isTransaction;
    }

    Connection getConnection() {
        return connection;
    }

    public void close() throws SQLException {
        if (!isTransaction){
            connection.close();
        }
    }

    public PreparedStatement preparedStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }
    public Statement statement(String sql) throws SQLException {
        return connection.createStatement();
    }
}
