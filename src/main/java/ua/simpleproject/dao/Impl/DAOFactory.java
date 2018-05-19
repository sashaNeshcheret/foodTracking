package ua.simpleproject.dao.Impl;

import ua.simpleproject.dao.*;

public class DAOFactory {
    private static DAOFactory daoFactory = new DAOFactory();
    private DAOUsers daoUsers = new DAOUsersImpl();
    private DAOStock daoStock = new DAOStockImpl();
    private DAOProduct daoProduct = new DAOProductImpl();
    private DAOCheckReports daoCheckReports = new DAOCheckReportsImpl();
    private DAOCurrentCheck daoCurrentCheck = new DAOCurrentCheckImpl();

    private DAOFactory(){
    }

    public static DAOFactory getDaoFactory() {
        return daoFactory;
    }
    public DAOUsers getDaoUsers() {
        return daoUsers;
    }
    public DAOStock getDaoStock() {
        return daoStock;
    }
    public DAOProduct getDaoProduct() {
        return daoProduct;
    }
    public DAOCheckReports getDaoCheckReports() {
        return daoCheckReports;
    }
    public DAOCurrentCheck getDaoCurrentCheck() {
        return daoCurrentCheck;
    }
}
