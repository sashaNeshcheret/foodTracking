package ua.trackingFood.dao.Impl;

import ua.trackingFood.dao.DAOProduct;
import ua.trackingFood.entity.CategoryProducts;
import ua.trackingFood.entity.Product;
import ua.trackingFood.entity.User;
import ua.trackingFood.exception.ConnectionException;
import ua.trackingFood.exception.DAOException;
import ua.trackingFood.transactions.ConnectionWrapper;
import ua.trackingFood.transactions.TransactionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DAOProductImpl implements DAOProduct {
    private static final Logger logger = Logger.getLogger("DAOProductImpl.class");
    private static  final String SQL_SELECT = "SELECT * FROM product where category_id = ?";
    private static  final String SQL_SELECT_ID = "SELECT * FROM product where id = ?";
    private static  final String SQL_INSERT = "INSERT INTO cash_register.users (position, name, surname, " +
            "login, password, check_word) VALUES(?,?,?,?,?,?)";
    private static  final String SQL_DELETE = "DELETE from cash_register.users WHERE login = ?";

    protected DAOProductImpl(){
    }

    /**Insert object user in database "Product"
     * @param product
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public void create(Product product) throws DAOException {
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_INSERT);

            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            logger.warning(String.format("Method create(product " + product + ") has thrown an exception."));
            throw new DAOException(String.format("Method create(product " + product + ") has thrown an exception."), e);
        }
    }



    /**
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public List<Product> read(int id) throws DAOException {
        List<Product> productList = new ArrayList<>();
        Product product = null;
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_SELECT);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                product = new Product();
                product.setId(resultSet.getInt(1));
                product.setCategoriaId(resultSet.getInt(2));
                product.setName(resultSet.getString(3));
                product.setEnergy_value(resultSet.getBigDecimal(4));
                product.setProteins(resultSet.getBigDecimal(5));
                product.setCarbohydrates(resultSet.getBigDecimal(6));
                product.setFats(resultSet.getBigDecimal(7));
                productList.add(product);
            }
        } catch (SQLException | ConnectionException e){
            logger.warning(String.format("Method Product read(id: '%s') has thrown an exception.", id));
            throw new DAOException(String.format("Method Product read(id: '%s') has thrown an exception.", id), e);
        }
        return productList;
    }
    public Product readById(int id) throws DAOException {
        Product product = null;
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_SELECT_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                product = new Product();
                product.setId(resultSet.getInt(1));
                product.setCategoriaId(resultSet.getInt(2));
                product.setName(resultSet.getString(3));
                product.setEnergy_value(resultSet.getBigDecimal(4));
                product.setProteins(resultSet.getBigDecimal(5));
                product.setCarbohydrates(resultSet.getBigDecimal(6));
                product.setFats(resultSet.getBigDecimal(7));
            }
        } catch (SQLException | ConnectionException e){
            logger.warning(String.format("Method Product read(id: '%s') has thrown an exception.", id));
            throw new DAOException(String.format("Method Product read(id: '%s') has thrown an exception.", id), e);
        }
        return product;
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
