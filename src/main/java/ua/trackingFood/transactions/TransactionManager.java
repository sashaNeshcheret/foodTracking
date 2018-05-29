package ua.trackingFood.transactions;

import ua.trackingFood.exception.ConnectionException;
import ua.trackingFood.exception.TransactionException;
import ua.trackingFood.transactions.ConnectionPool;
import ua.trackingFood.transactions.ConnectionWrapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class TransactionManager {
    
    private static final ThreadLocal<ua.trackingFood.transactions.ConnectionWrapper> threadLocal = new ThreadLocal<>();

    private TransactionManager() {
    }

    public static void beginTransaction() throws TransactionException, ConnectionException{
        if (Objects.nonNull(threadLocal.get()))
            throw new TransactionException();
        try {
            Connection connection = ua.trackingFood.transactions.ConnectionPool.getConnection();
            connection.setAutoCommit(false);
            ua.trackingFood.transactions.ConnectionWrapper wrapper = new ua.trackingFood.transactions.ConnectionWrapper(connection, true);
            threadLocal.set(wrapper);
        } catch (SQLException e) {
            throw new ConnectionException();
        }
    }

    public static void endTransaction() throws TransactionException, ConnectionException{
        if (Objects.isNull(threadLocal.get()))
            throw new TransactionException();
        try {
            ua.trackingFood.transactions.ConnectionWrapper wrapper = threadLocal.get();
            Connection connection = wrapper.getConnection();
            connection.commit();
            connection.close();
            threadLocal.set(null);
        } catch (SQLException e) {
            throw new ConnectionException("Сталася помилка при commit or close", e);
        }
    }

    public static void rollBack() throws SQLException {
        if (Objects.isNull(threadLocal.get()))
            return; // already closed
        ua.trackingFood.transactions.ConnectionWrapper wrapper = threadLocal.get();
        Connection connection = wrapper.getConnection();
        connection.rollback();
        connection.close();
        threadLocal.set(null);
    }

    public static ua.trackingFood.transactions.ConnectionWrapper getConnection() throws ConnectionException {
        if (Objects.isNull(threadLocal.get())) {
            Connection connection = ConnectionPool.getConnection();
            return new ConnectionWrapper(connection, false);
        } else {
            return threadLocal.get();
        }
    }
}
