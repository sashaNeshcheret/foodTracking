package ua.trackingFood.dao;

import ua.trackingFood.entity.CategoryProducts;
import ua.trackingFood.exception.DAOException;

import java.util.List;

public interface DAOCategoryProducts {
    void create(CategoryProducts category) throws DAOException;
    int count() throws DAOException;
    CategoryProducts read(int CategoryId) throws DAOException;
    List<CategoryProducts> read() throws DAOException;
    void delete(String login) throws DAOException;
}