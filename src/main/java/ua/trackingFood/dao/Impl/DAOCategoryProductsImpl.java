package ua.trackingFood.dao.Impl;

import ua.trackingFood.dao.DAOCategoryProducts;
import ua.trackingFood.entity.CategoryProducts;
import ua.trackingFood.entity.User;
import ua.trackingFood.entity.UserParam;
import ua.trackingFood.exception.ConnectionException;
import ua.trackingFood.exception.DAOException;
import ua.trackingFood.transactions.ConnectionWrapper;
import ua.trackingFood.transactions.TransactionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DAOCategoryProductsImpl implements DAOCategoryProducts {
    private static final Logger logger = Logger.getLogger("DAOUsersImpl.class");
    private static  final String SQL_SELECT = "SELECT * FROM product_category";
    private static  final String SQL_INSERT = "INSERT INTO cash_register.users (position, name, surname, " +
            "login, password, check_word) VALUES(?,?,?,?,?,?)";
    private static  final String SQL_DELETE = "DELETE from cash_register.users WHERE login = ?";

    protected DAOCategoryProductsImpl(){
    }

    /**Insert object user in database "User"
     *
     * @param user
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public void create(CategoryProducts user) throws DAOException {
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_INSERT);

            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            logger.warning(String.format("Method create(user " + user + ") has thrown an exception."));
            throw new DAOException(String.format("Method create(user " + user + ") has thrown an exception."), e);
        }
    }

    /**Select user in DB by login
     *
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public List<CategoryProducts> read() throws DAOException {
        CategoryProducts category = null;
        List<CategoryProducts> list = new ArrayList<>();
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_SELECT);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                category = new CategoryProducts();
                category.setId(resultSet.getInt(1));
                category.setName(resultSet.getString(2));
                list.add(category);
            }
        } catch (SQLException | ConnectionException e){
            logger.warning(String.format("Method CategoryProducts read() has thrown an exception."));
            throw new DAOException(String.format("Method CategoryProducts read(login: '%s') has thrown an exception."), e);
        }
        return list;
    }
    /**Select user in DB by login
     *
     * @param login
     * @return
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public CategoryProducts read(String login) throws DAOException {
        User user = null;
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_SELECT);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setLogin(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
            }
        } catch (SQLException | ConnectionException e){
            logger.warning(String.format("Method read(login: '%s') has thrown an exception.", login));
            throw new DAOException(String.format("Method read(login: '%s') has thrown an exception.", login), e);
        }
        return new CategoryProducts();
    }

    /**Delete user in DB by login
     *
     * @param login
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public void delete(String login) throws DAOException {
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_DELETE);
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            logger.warning(String.format("Method delete(login: '%s') has thrown an exception.", login));
            throw new DAOException(String.format("Method delete(login: '%s') has thrown an exception.", login), e);
        }
    }
}
