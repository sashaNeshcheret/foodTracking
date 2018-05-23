package ua.trackingFood.dao;

import ua.trackingFood.entity.Product;
import ua.trackingFood.exception.DAOException;

import java.util.List;

public interface DAOProduct{
    void create(Product product) throws DAOException;
    int count() throws DAOException;
    List<Product> read(int id, int from, int size) throws DAOException;
    Product readById(int id) throws DAOException;
    void delete(String login) throws DAOException;
}