package ua.simpleproject.dao;


import ua.simpleproject.entity.Product;
import ua.simpleproject.entity.Stock;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.exception.LogicException;

import java.math.BigDecimal;
import java.util.List;

public interface DAOStock {

    public Stock read(Product product) throws DAOException, LogicException;
    public List<Stock> read(int start, int end) throws DAOException;
    public void create(Product product, BigDecimal number) throws DAOException;
    public void update(Stock stock) throws DAOException;
}