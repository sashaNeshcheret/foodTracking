package ua.trackingFood.dao;

import ua.trackingFood.entity.User;
import ua.trackingFood.entity.UserParam;
import ua.trackingFood.exception.DAOException;

public interface DAOUserParam {
    void create(UserParam user) throws DAOException;
    UserParam read(int userId) throws DAOException;
    void delete(String login) throws DAOException;
}