package ua.trackingFood.dao.Impl;


import ua.trackingFood.dao.DAOUserParam;
import ua.trackingFood.entity.UserParam;
import ua.trackingFood.exception.ConnectionException;
import ua.trackingFood.exception.DAOException;
import ua.trackingFood.transactions.ConnectionWrapper;
import ua.trackingFood.transactions.TransactionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DAOUserParamImpl implements DAOUserParam {
    private static final Logger logger = Logger.getLogger("DAOUsersImpl.class");
    private static  final String SQL_SELECT = "SELECT * FROM user_param where user_id = ?";
    private static  final String SQL_INSERT = "INSERT INTO user_param (user_id, sex, lifestyle_id, " +
            "age, height, weight, expected_result) VALUES(?,?,?,?,?,?,?)";
    private static  final String SQL_UPDATE = "Update tracking_food.user_param SET sex=?, lifestyle_id=?, age=?, " +
            "height=?, weight=?, expected_result=? where user_id = ?";
    private static  final String SQL_DELETE = "DELETE from user_param.users WHERE login = ?";

    protected DAOUserParamImpl(){
    }

    /**Insert object user in database "UserContact"
     *
     * @param user
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public void create(UserParam user) throws DAOException {
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_INSERT);
            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.setString(2, user.getSex());
            preparedStatement.setInt(3, user.getLifeStyleId());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.setBigDecimal(5, user.getHeight());
            preparedStatement.setBigDecimal(6, user.getWeight());
            preparedStatement.setString(7, user.getExpectedResult());
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            logger.warning(String.format("Method create(user " + user + ") has thrown an exception."));
            throw new DAOException(String.format("Method create(user " + user + ") has thrown an exception."), e);
        }
    }

    /**Select user in DB by login
     *
     * @param userId
     * @return
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public UserParam read(int userId) throws DAOException {
        UserParam userParam = null;
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_SELECT);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                userParam = new UserParam();
                userParam.setId(resultSet.getInt(1));
                userParam.setUserId(resultSet.getInt(2));
                userParam.setSex(resultSet.getString(3));
                userParam.setLifeStyleId(resultSet.getInt(4));
                userParam.setAge(resultSet.getInt(5));
                userParam.setHeight(resultSet.getBigDecimal(6));
                userParam.setWeight(resultSet.getBigDecimal(7));
                userParam.setExpectedResult(resultSet.getString(8));
            }
        } catch (SQLException | ConnectionException e){
            logger.warning(String.format("Method read(userId: '%s') has thrown an exception.", userId));
            throw new DAOException(String.format("Method read(userId: '%s') has thrown an exception.", userId), e);
        }
        return userParam;
    }
   // "Update tracking_food.user_param SET sex=?, lifestyle_id=?, age=?, height=?, weight=?, expected_result=? where user_id = ?";
    public void update(UserParam user) throws DAOException {
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_UPDATE);

            preparedStatement.setString(1, user.getSex());
            preparedStatement.setInt(2, user.getLifeStyleId());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setBigDecimal(4, user.getHeight());
            preparedStatement.setBigDecimal(5, user.getWeight());
            preparedStatement.setString(6, user.getExpectedResult());

            preparedStatement.setInt(7, user.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            logger.warning(String.format("Method create(user " + user + ") has thrown an exception."));
            throw new DAOException(String.format("Method create(user " + user + ") has thrown an exception."), e);
        }
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
