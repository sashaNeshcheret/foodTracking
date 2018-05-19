package ua.trackingFood.dao.Impl;

import ua.trackingFood.dao.DAOEatenProducts;
import ua.trackingFood.entity.EatenProducts;
import ua.trackingFood.exception.ConnectionException;
import ua.trackingFood.exception.DAOException;
import ua.trackingFood.transactions.ConnectionWrapper;
import ua.trackingFood.transactions.TransactionManager;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DAOEatenProductsImpl implements DAOEatenProducts {
    private static final Logger logger = Logger.getLogger("DAOProductImpl.class");
//private static  final String SQL_SELECT = "SELECT * FROM eaten_products where date = ? AND user_id = ?";
    private static  final String SQL_SELECT = "SELECT * FROM eaten_products where user_id = ?";
    private static  final String SQL_INSERT = "INSERT INTO eaten_products (user_id, product_id, weight, " +
            "energy_value, proteins, carbohydrates, fats, date) VALUES(?,?,?,?,?,?,?, NOW())";
    private static  final String SQL_UPDATE = "Update eaten_products SET weight=?, energy_value=?, proteins=?, carbohydrates=?, fats=? WHERE id= ?";
    private static final String SQL_UPDATE_BY_ID = "UPDATE cash_register.current_check SET count = ?, result_price = ?  WHERE id = ?";
    private static  final String SQL_DELETE = "DELETE from eaten_products WHERE id = ?";

    protected DAOEatenProductsImpl(){
    }

    /**Insert object user in database "Product"
     * @param eatenProducts
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public void create(EatenProducts eatenProducts) throws DAOException {
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_INSERT);
            preparedStatement.setInt(1, eatenProducts.getUserId());
            preparedStatement.setInt(2, eatenProducts.getProductId());
            preparedStatement.setBigDecimal(3, eatenProducts.getWeight());
            preparedStatement.setBigDecimal(4, eatenProducts.getEnergyValue());
            preparedStatement.setBigDecimal(5, eatenProducts.getProteins());
            preparedStatement.setBigDecimal(6, eatenProducts.getCarbohydrates());
            preparedStatement.setBigDecimal(7, eatenProducts.getFats());
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            logger.warning(String.format("Method create(eatenProducts " + eatenProducts + ") has thrown an exception."));
            throw new DAOException(String.format("Method create(eatenProducts " + eatenProducts + ") has thrown an exception."), e);
        }
    }
    /**Update object user in database "Product"
     * @param eatenProducts, id
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public void update(int id, EatenProducts eatenProducts) throws DAOException {
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_UPDATE);
            preparedStatement.setBigDecimal(1, eatenProducts.getWeight());
            preparedStatement.setBigDecimal(2, eatenProducts.getEnergyValue());
            preparedStatement.setBigDecimal(3, eatenProducts.getProteins());
            preparedStatement.setBigDecimal(4, eatenProducts.getCarbohydrates());
            preparedStatement.setBigDecimal(5, eatenProducts.getFats());
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            logger.warning(String.format("Method create(eatenProducts " + eatenProducts + ") has thrown an exception."));
            throw new DAOException(String.format("Method create(eatenProducts " + eatenProducts + ") has thrown an exception."), e);
        }
    }


    /**
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public List<EatenProducts> read(Date date, int id) throws DAOException {
        LocalDate date1 = LocalDate.now();
        Date dateSql = Date.valueOf(date1);
        List<EatenProducts> productList = new ArrayList<>();
        EatenProducts eatenProducts = null;
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_SELECT);
//preparedStatement.setDate(1, dateSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                eatenProducts = new EatenProducts();
                eatenProducts.setId(resultSet.getInt(1));
                eatenProducts.setUserId(resultSet.getInt(2));
                eatenProducts.setProductId(resultSet.getInt(3));
                eatenProducts.setWeight(resultSet.getBigDecimal(4));
                eatenProducts.setEnergyValue(resultSet.getBigDecimal(5));
                eatenProducts.setProteins(resultSet.getBigDecimal(6));
                eatenProducts.setCarbohydrates(resultSet.getBigDecimal(7));
                eatenProducts.setFats(resultSet.getBigDecimal(8));
                productList.add(eatenProducts);
            }
        } catch (SQLException | ConnectionException e){
            logger.warning(String.format("Method Product read(id: '%s') has thrown an exception.", id));
            throw new DAOException(String.format("Method Product read(id: '%s') has thrown an exception.", id), e);
        }
        return productList;
    }

    /**Delete user in DB by login
     *
     * @param id
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public void delete(int id) throws DAOException {
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            logger.warning(String.format("Method delete(id: '%s') has thrown an exception.", id));
            throw new DAOException(String.format("Method delete(id: '%s') has thrown an exception.", id), e);
        }
    }
}
