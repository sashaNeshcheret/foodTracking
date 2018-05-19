package ua.simpleproject.dao;


import ua.simpleproject.entity.Product;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.transactions.ConnectionPool;

import java.sql.*;
import java.util.Objects;

public interface DAOProduct {

    public Product readById(int id) throws DAOException;
    public Product read (int code) throws DAOException;
    public Product read(String name) throws DAOException;
    public void create(Product product) throws DAOException;
}
