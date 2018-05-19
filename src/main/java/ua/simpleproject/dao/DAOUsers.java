package ua.simpleproject.dao;

import ua.simpleproject.entity.User;
import ua.simpleproject.exception.DAOException;

public interface DAOUsers {
    public void create(User user) throws DAOException;
    public User read(String login) throws DAOException;
    public void delete(String login) throws DAOException;
}