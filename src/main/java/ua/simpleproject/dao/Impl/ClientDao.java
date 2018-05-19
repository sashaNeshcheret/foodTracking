package ua.simpleproject.dao.Impl;

import ua.simpleproject.entity.User;
import ua.simpleproject.exception.ConnectionException;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.transactions.ConnectionPool;
import ua.simpleproject.transactions.ConnectionWrapper;
import ua.simpleproject.transactions.TransactionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDao {

    public static final String SQL = "select * from clients where login = ?";

    protected ClientDao() {
    }

    public User getByLogin(String login) throws DAOException {
        User user = null;
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setName(resultSet.getString("name"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException | ConnectionException e) {
            throw new DAOException(String.format("Method getByLogin(login: '%d') has thrown an exception.", login), e);
        }
        return null;
    }
}
