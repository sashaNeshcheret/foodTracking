package ua.trackingFood.dao;

import ua.trackingFood.entity.UserResult;
import ua.trackingFood.exception.DAOException;

public interface DAOUserResult {
    void create(UserResult user) throws DAOException;
    void update(UserResult user) throws DAOException;
    UserResult read(int userId) throws DAOException;
    void delete(String login) throws DAOException;
}