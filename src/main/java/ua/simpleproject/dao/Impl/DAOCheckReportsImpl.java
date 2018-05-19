package ua.simpleproject.dao.Impl;

import ua.simpleproject.dao.DAOCheckReports;
import ua.simpleproject.entity.CheckReports;
import ua.simpleproject.exception.ConnectionException;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.transactions.ConnectionPool;
import ua.simpleproject.transactions.ConnectionWrapper;
import ua.simpleproject.transactions.TransactionManager;

import java.math.BigDecimal;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class DAOCheckReportsImpl implements DAOCheckReports {
    public static final String SQL_INSERT = "INSERT INTO cash_register.check_reports (user_id, number_of_product, check_amount, time_close) VALUES (?,?,?,NOW())";
    public static final String SQL_SELECT = "SELECT* FROM cash_register.check_reports where user_id = ? and time_close > ?";

    protected DAOCheckReportsImpl(){
    }

    public void create(CheckReports checkReports) throws DAOException {
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement prStatement = connection.preparedStatement(SQL_INSERT);
            prStatement.setInt(1, checkReports.getUserId());// userId
            prStatement.setInt(2, checkReports.getNumberOfProduct());//count
            prStatement.setBigDecimal(3, checkReports.getCheckAmount());//checkAmount
            prStatement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            throw new DAOException("Method insertToCurrentCheck has thrown an exception.", e);
        }

    }
    public List<CheckReports> read(LocalDate currentTime , int userID) throws DAOException {
        List<CheckReports> checkReportsList = new ArrayList<>();
        java.sql.Date sqlDate = java.sql.Date.valueOf( currentTime );

        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement prStatement = connection.preparedStatement(SQL_SELECT);
            prStatement.setInt(1, userID);
            prStatement.setDate(2, sqlDate);
            ResultSet resultSet = prStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                int userId = resultSet.getInt(2);
                int count = resultSet.getInt(3);
                BigDecimal checkAmount = resultSet.getBigDecimal(4);
                java.sql.Date timeClose = resultSet.getDate(5);
                CheckReports checkReports = new CheckReports(id, userId, count, checkAmount, timeClose);
                checkReportsList.add(checkReports);
            }
        } catch (SQLException | ConnectionException e) {
            throw new DAOException(String.format("Method read(user id: '%d') has thrown an exception.", userID), e);
        }
        return checkReportsList;

    }
}
