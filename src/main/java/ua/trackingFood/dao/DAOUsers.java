package ua.trackingFood.dao;

import ua.trackingFood.entity.UserContact;
import ua.trackingFood.exception.DAOException;

public interface DAOUsers {
    void create(UserContact userContact) throws DAOException;
    UserContact read(String login) throws DAOException;
    void delete(String login) throws DAOException;
}