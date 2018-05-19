package ua.simpleproject.dao.Impl;


import ua.simpleproject.dao.DAOUsers;
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

public class DAOUsersImpl implements DAOUsers {
    private static  final String SQL_SELECT = "SELECT * FROM users where login = ?";
    private static  final String SQL_INSERT = "INSERT INTO cash_register.users (position, name, login, password, " +
            "check_word) VALUES(?,?,?,?,?)";
    private static  final String SQL_DELETE = "DELETE from cash_register.users WHERE login = ?";

    protected DAOUsersImpl(){
    }
    public void create(User user) throws DAOException {
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_INSERT);
            preparedStatement.setString(1, user.getPosition());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurName());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getCheckWord());
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            throw new DAOException();
        }
    }

    public User read(String login) throws DAOException{
        User user = null;
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_SELECT);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt(1));
                user.setPosition(resultSet.getString(2));
                user.setName(resultSet.getString(3));
                user.setSurName(resultSet.getString(4));
                user.setLogin(resultSet.getString(5));
                user.setPassword(resultSet.getString(6));
                user.setCheckWord(resultSet.getString(7));
                user.setSalary(resultSet.getBigDecimal(8));
                }
        } catch (SQLException | ConnectionException e){
            throw new DAOException(String.format("Method read(login: '%s') has thrown an exception.", login), e);
        }
        return user;
    }
    public void delete(String login) throws DAOException {
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_DELETE);
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            throw new DAOException(String.format("Method delete(login: '%s') has thrown an exception.", login), e);
        }
    }
}
