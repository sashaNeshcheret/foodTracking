package ua.trackingFood.dao.Impl;


import ua.trackingFood.dao.DAOUsers;
import ua.trackingFood.entity.UserContact;
import ua.trackingFood.exception.ConnectionException;
import ua.trackingFood.exception.DAOException;
import ua.trackingFood.transactions.ConnectionWrapper;
import ua.trackingFood.transactions.TransactionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DAOUsersImpl implements DAOUsers {
    private static final Logger logger = Logger.getLogger("DAOUsersImpl.class");
    private static  final String SQL_SELECT = "SELECT * FROM user_contact where login = ?";
    private static  final String SQL_INSERT = "INSERT INTO user_contact (name, surname, " +
            "login, password, mail_address) VALUES(?,?,?,?,?)";
    //private static  final String SQL_DELETE = "DELETE from cash_register.users WHERE login = ?";

    protected DAOUsersImpl(){
    }

    /**Insert object userContact in database "UserContact"
     *
     * @param userContact
     * @throws ua.trackingFood.exception.DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public void create(UserContact userContact) throws DAOException {
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_INSERT);
            preparedStatement.setString(1, userContact.getName());
            preparedStatement.setString(2, userContact.getSurname());
            preparedStatement.setString(3, userContact.getLogin());
            preparedStatement.setString(4, userContact.getPassword());
            preparedStatement.setString(5, userContact.getMail_adress());
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            logger.warning(String.format("Method create(userContact " + userContact + ") has thrown an exception."));
            throw new DAOException(String.format("Method create(userContact " + userContact + ") has thrown an exception."), e);
        }
    }

    /**Select user in DB by login
     *
     * @param login
     * @return
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public UserContact read(String login) throws DAOException {
        UserContact userContact = null;
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_SELECT);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                userContact = new UserContact();
                userContact.setId(resultSet.getInt(1));
                userContact.setName(resultSet.getString(2));
                userContact.setSurname(resultSet.getString(3));
                userContact.setLogin(resultSet.getString(4));
                userContact.setPassword(resultSet.getString(5));
                userContact.setMail_adress(resultSet.getString(6));
                }
        } catch (SQLException | ConnectionException e){
            logger.warning(String.format("Method read(login: '%s') has thrown an exception.", login));
            throw new DAOException(String.format("Method read(login: '%s') has thrown an exception.", login), e);
        }
        return userContact;
    }

    /**Delete user in DB by login
     *
     * @param login
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public void delete(String login) throws DAOException {
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
PreparedStatement preparedStatement = connection.preparedStatement("j");
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            logger.warning(String.format("Method delete(login: '%s') has thrown an exception.", login));
            throw new DAOException(String.format("Method delete(login: '%s') has thrown an exception.", login), e);
        }
    }
}
