package ua.trackingFood.dao;

import ua.trackingFood.entity.CategoryProducts;
import ua.trackingFood.entity.User;
import ua.trackingFood.entity.UserParam;
import ua.trackingFood.exception.DAOException;

import java.util.List;

public interface DAOCategoryProducts {
    void create(CategoryProducts category) throws DAOException;
    CategoryProducts read(String login) throws DAOException;
    List<CategoryProducts> read() throws DAOException;
    void delete(String login) throws DAOException;
}