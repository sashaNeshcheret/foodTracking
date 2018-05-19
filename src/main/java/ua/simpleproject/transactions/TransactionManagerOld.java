package ua.simpleproject.transactions;

import ua.simpleproject.exception.ConnectionException;
import ua.simpleproject.exception.TransactionException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class TransactionManagerOld {
    private static final ThreadLocal<ConnectionWrapper> threadLocal = new ThreadLocal<>();
    private TransactionManagerOld() {
    }

    public static void beginTransaction() throws SQLException, TransactionException {
        if (Objects.nonNull(threadLocal.get())) throw new TransactionException();
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (ConnectionException e) {
            e.printStackTrace();
        }
        ConnectionWrapper wrapper = new ConnectionWrapper(connection,true);
        threadLocal.set(wrapper);
    }

    public static void endTransaction() throws SQLException, TransactionException {
        if (Objects.isNull(threadLocal.get()))
            throw new TransactionException();
        ConnectionWrapper wrapper = threadLocal.get();
        wrapper.getConnection().close();
        threadLocal.set(null);
    }

    public static ConnectionWrapper getConnection() throws SQLException {
        if (Objects.isNull(threadLocal.get())){
            Connection connection = null;
            try {
                connection = ConnectionPool.getConnection();
            } catch (ConnectionException e) {
                e.printStackTrace();
            }
            ConnectionWrapper wrapper = new ConnectionWrapper(connection,false);
            return wrapper;
        } else {return threadLocal.get();}
    }
}
