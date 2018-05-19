package ua.simpleproject.dao;

import ua.simpleproject.entity.CheckReports;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.transactions.ConnectionPool;

import java.math.BigDecimal;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public interface DAOCheckReports {
    public void create(CheckReports checkReports) throws DAOException;
    public List<CheckReports> read(LocalDate currentTime , int userID) throws DAOException ;
}
