package ua.trackingFood.dao;

import ua.trackingFood.entity.LifeStyle;
import ua.trackingFood.entity.Product;
import ua.trackingFood.exception.DAOException;

import java.util.List;

public interface DAOLifeStyle {

    LifeStyle read(int id) throws DAOException;
}