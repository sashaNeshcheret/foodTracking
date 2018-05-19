package ua.simpleproject.dao;


import ua.simpleproject.entity.CheckReports;
import ua.simpleproject.entity.CurrentCheck;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.transactions.ConnectionPool;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface DAOCurrentCheck {

    public void create(CurrentCheck currentCheck) throws DAOException;
    public void update(CurrentCheck currentCheck) throws DAOException;
    public List<CurrentCheck> read(String login) throws DAOException;
    public void delete(int userId) throws DAOException;
}
