package ua.trackingFood.dao;

import ua.trackingFood.entity.EatenProducts;
import ua.trackingFood.exception.DAOException;

import java.sql.Date;
import java.util.List;

public interface DAOEatenProducts {
    void create(EatenProducts product) throws DAOException;
    void update(int id, EatenProducts product) throws DAOException;
    List<EatenProducts> read(int id) throws DAOException;
    void delete(int id) throws DAOException;
}
