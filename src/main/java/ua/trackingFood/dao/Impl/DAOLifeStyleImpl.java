package ua.trackingFood.dao.Impl;

import ua.trackingFood.dao.DAOEatenProducts;
import ua.trackingFood.dao.DAOLifeStyle;
import ua.trackingFood.entity.EatenProducts;
import ua.trackingFood.entity.LifeStyle;
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

public class DAOLifeStyleImpl implements DAOLifeStyle {
    private static final Logger logger = Logger.getLogger("DAOProductImpl.class");
    private static  final String SQL_SELECT = "SELECT * FROM life_style where id = ?";

    protected DAOLifeStyleImpl(){
    }

    /**
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public LifeStyle read(int id) throws DAOException {
        LifeStyle lifeStyle = null;
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_SELECT);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                lifeStyle = new LifeStyle();
                lifeStyle.setId(resultSet.getInt(1));
                lifeStyle.setLifeStyle(resultSet.getString(2));
                lifeStyle.setIndex(resultSet.getBigDecimal(3));
            }
        } catch (SQLException | ConnectionException e){
            logger.warning(String.format("Method LifeStyle read(id: '%s') has thrown an exception.", id));
            throw new DAOException(String.format("Method LifeStyle read(id: '%s') has thrown an exception.", id), e);
        }
        return lifeStyle;
    }


}
