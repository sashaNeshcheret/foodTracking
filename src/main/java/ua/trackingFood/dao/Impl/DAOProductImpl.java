package ua.trackingFood.dao.Impl;

import ua.trackingFood.dao.DAOProduct;
import ua.trackingFood.entity.Product;
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
    private static  final String SQL_SELECT = "SELECT * FROM product where category_id = ?  ORDER BY energy_value LIMIT ?,?";
    private static  final String SQL_COUNT = "SELECT COUNT(*) FROM tracking_food.product";
    private static  final String SQL_SELECT_Join = "SELECT * FROM product inner join product_category on  product_category.id = ?";
    private static  final String SQL_SELECT_ID = "SELECT * FROM product where id = ?";
    private static  final String SQL_INSERT = "INSERT INTO product (category_id, name, energy_value, " +
            "proteins, carbohydrates, fats) VALUES(?,?,?,?,?,?)";
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
            preparedStatement.setInt(1, product.getCategoryId());
            preparedStatement.setString(2,product.getName());
            preparedStatement.setBigDecimal(3,product.getEnergyValue());
            preparedStatement.setBigDecimal(4,product.getProteins());
            preparedStatement.setBigDecimal(5,product.getCarbohydrates());
            preparedStatement.setBigDecimal(6,product.getFats());
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            logger.warning(String.format("Method create(product " + product + ") has thrown an exception."));
            throw new DAOException(String.format("Method create(product " + product + ") has thrown an exception."), e);
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
            logger.warning(String.format("Method count() has thrown an exception."));
            throw new DAOException(String.format("Method count() has thrown an exception."), e);
        }
        return count;
    }



    /**
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
/*    public List<Product> read(int id) throws DAOException {
        List<Product> productList = new ArrayList<>();
        Product product = null;
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_SELECT_Join);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                product = new Product();
                product.setId(resultSet.getInt(1));
                product.setCategoryId(resultSet.getInt(2));
                product.setName(resultSet.getString(3));
                product.setEnergyValue(resultSet.getBigDecimal(4));
                product.setProteins(resultSet.getBigDecimal(5));
                product.setCarbohydrates(resultSet.getBigDecimal(6));
                product.setFats(resultSet.getBigDecimal(7));
                product.setCategoryName(resultSet.getString(9));
                productList.add(product);
            }
        } catch (SQLException | ConnectionException e){
            logger.warning(String.format("Method Product read(id: '%s') has thrown an exception.", id));
            throw new DAOException(String.format("Method Product read(id: '%s') has thrown an exception.", id), e);
        }
        return productList;
    }
    */
    public List<Product> read(int id, int from, int size) throws DAOException {
        List<Product> productList = new ArrayList<>();
        Product product = null;
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_SELECT);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, from);
            preparedStatement.setInt(3, size);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                product = new Product();
                product.setId(resultSet.getInt(1));
                product.setCategoryId(resultSet.getInt(2));
                product.setName(resultSet.getString(3));
                product.setEnergyValue(resultSet.getBigDecimal(4));
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
                product.setCategoryId(resultSet.getInt(2));
                product.setName(resultSet.getString(3));
                product.setEnergyValue(resultSet.getBigDecimal(4));
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
