package ua.trackingFood.dao;

import ua.trackingFood.entity.User;
import ua.trackingFood.exception.DAOException;

public interface DAOUsers {
    void create(User user) throws DAOException;
    User read(String login) throws DAOException;
    void delete(String login) throws DAOException;
}