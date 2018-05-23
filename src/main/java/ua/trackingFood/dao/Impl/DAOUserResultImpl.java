package ua.trackingFood.dao.Impl;


import ua.trackingFood.dao.DAOUserResult;
import ua.trackingFood.entity.UserResult;
import ua.trackingFood.exception.ConnectionException;
import ua.trackingFood.exception.DAOException;
import ua.trackingFood.transactions.ConnectionWrapper;
import ua.trackingFood.transactions.TransactionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DAOUserResultImpl implements DAOUserResult {
    private static final Logger logger = Logger.getLogger("DAOUsersImpl.class");
    private static  final String SQL_SELECT = "SELECT * FROM user_result where user_id = ?";
    private static  final String SQL_INSERT = "INSERT INTO user_result (user_id, level_metabolism, norm_calories, " +
            "expected_norm_calories, proteins, carbohydrates, fats) VALUES(?,?,?,?,?,?,?)";
    private static  final String SQL_UPDATE = "UPDATE user_result SET level_metabolism = ?, norm_calories = ?," +
            "expected_norm_calories = ?, proteins = ?, carbohydrates = ?, fats = ? where user_id = ?";
    private static  final String SQL_DELETE = "DELETE from user_param.users WHERE login = ?";

    protected DAOUserResultImpl(){
    }

    /**Insert object user in database "UserContact"
     *
     * @param user
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public void create(UserResult user) throws DAOException {
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_INSERT);
            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.setBigDecimal(2, user.getLevelMetabolism());
            preparedStatement.setBigDecimal(3, user.getNormaCalories());
            preparedStatement.setBigDecimal(4, user.getExpectedNormCalories());
            preparedStatement.setBigDecimal(5, user.getProteins());
            preparedStatement.setBigDecimal(6, user.getCarbohydrates());
            preparedStatement.setBigDecimal(7, user.getFats());
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
    public UserResult read(int userId) throws DAOException {
        UserResult userResult = null;
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_SELECT);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                userResult = new UserResult();
                userResult.setId(resultSet.getInt(1));
                userResult.setUserId(resultSet.getInt(2));
                userResult.setLevelMetabolism(resultSet.getBigDecimal(3));
                userResult.setNormaCalories(resultSet.getBigDecimal(4));
                userResult.setExpectedNormCalories(resultSet.getBigDecimal(5));
                userResult.setProteins(resultSet.getBigDecimal(6));
                userResult.setCarbohydrates(resultSet.getBigDecimal(7));
                userResult.setFats(resultSet.getBigDecimal(8));
            }
        } catch (SQLException | ConnectionException e){
            logger.warning(String.format("Method read(login: '%s') has thrown an exception.", userId));
            throw new DAOException(String.format("Method read(login: '%s') has thrown an exception.", userId), e);
        }
        return userResult;
    }

    /**Insert object user in database "user_result"
     *
     * @param user
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public void update(UserResult user) throws DAOException {
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_UPDATE);
            preparedStatement.setBigDecimal(1, user.getLevelMetabolism());
            preparedStatement.setBigDecimal(2, user.getNormaCalories());
            preparedStatement.setBigDecimal(3, user.getExpectedNormCalories());
            preparedStatement.setBigDecimal(4, user.getProteins());
            preparedStatement.setBigDecimal(5, user.getCarbohydrates());
            preparedStatement.setBigDecimal(6, user.getFats());
            preparedStatement.setInt(7, user.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            logger.warning(String.format("Method update(UserResult " + user + ") has thrown an exception."));
            throw new DAOException(String.format("Method update(UserResult " + user + ") has thrown an exception."), e);
        }
    }
    /**Delete user in DB by login
     *
     * @param userId
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public void delete(String userId) throws DAOException {
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_DELETE);
            preparedStatement.setString(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            logger.warning(String.format("Method delete(userId: '%s') has thrown an exception.", userId));
            throw new DAOException(String.format("Method delete(userId: '%s') has thrown an exception.", userId), e);
        }
    }
}
