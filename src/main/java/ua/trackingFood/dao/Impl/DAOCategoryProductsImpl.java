package ua.trackingFood.dao.Impl;

import ua.trackingFood.dao.DAOCategoryProducts;
import ua.trackingFood.entity.CategoryProducts;
import ua.trackingFood.entity.UserContact;
import ua.trackingFood.exception.ConnectionException;
import ua.trackingFood.exception.DAOException;
import ua.trackingFood.transactions.ConnectionWrapper;
import ua.trackingFood.transactions.TransactionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

public class DAOCategoryProductsImpl implements DAOCategoryProducts {
    private static final Logger logger = Logger.getLogger("DAOUsersImpl.class");
    private static  final String SQL_SELECT = "SELECT * FROM product_category";
    private static  final String SQL_COUNT = "SELECT COUNT(*) FROM tracking_food.product_category";
    private static  final String SQL_SELECT_BY_ID = "SELECT * FROM product_category where id = ?";
    private static  final String SQL_INSERT = "INSERT INTO product_category (name) VALUES(?)";
    private static  final String SQL_DELETE = "DELETE from product_category WHERE id = ?";

    protected DAOCategoryProductsImpl(){
    }

    /**Insert object user in database "UserContact"
     *
     * @param categoryProducts
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public void create(CategoryProducts categoryProducts) throws DAOException {
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_INSERT);
            preparedStatement.setString(1, categoryProducts.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            logger.warning(String.format("Method create(categoryProducts " + categoryProducts + ") has thrown an exception."));
            throw new DAOException(String.format("Method create(categoryProducts " + categoryProducts + ") has thrown an exception."), e);
        }
    }
    public int count() throws DAOException {
        int count = 0;
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_COUNT);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                count = resultSet.getInt(1);
            }
        } catch (SQLException | ConnectionException e) {
            logger.warning("Method count() has thrown an exception.");
            throw new DAOException(String.format("Method count() has thrown an exception."), e);
        }
        return count;
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
     * @param id
     * @return
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public CategoryProducts read(int id) throws DAOException {
        CategoryProducts category = null;
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_SELECT_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                category = new CategoryProducts();
                category.setId(resultSet.getInt(1));
                category.setName(resultSet.getString(2));
            }
        } catch (SQLException | ConnectionException e){
            logger.warning(String.format("Method read(id: '%s') has thrown an exception.", id));
            throw new DAOException(String.format("Method read(id: '%s') has thrown an exception.", id), e);
        }
        return category;
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
