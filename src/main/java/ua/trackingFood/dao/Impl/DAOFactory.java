package ua.trackingFood.dao.Impl;

import ua.trackingFood.dao.*;

public class DAOFactory {
    private static DAOFactory daoFactory = new DAOFactory();
    private DAOUsers daoUsers = new DAOUsersImpl();
    private DAOUserParam daoUserParam = new DAOUserParamImpl();
    private DAOUserResult daoUserResult = new DAOUserResultImpl();
    private DAOCategoryProducts daoCategoryProducts = new DAOCategoryProductsImpl();
    private DAOProduct daoProduct = new DAOProductImpl();
    private DAOEatenProducts daoEatenProducts = new DAOEatenProductsImpl();
    private DAOLifeStyle daoLifeStyle = new DAOLifeStyleImpl();


    private DAOFactory(){
    }

    public static DAOFactory getDaoFactory() {
        return daoFactory;
    }
    public DAOUsers getDAOUsers() {
        return daoUsers;
    }
    public DAOUserParam getDAOUserParam() {
        return daoUserParam;
    }
    public DAOUserResult getDAOUserResult() {
        return daoUserResult;
    }
    public DAOCategoryProducts getDAOCategoryProducts() {
        return daoCategoryProducts;
    }
    public DAOProduct getDAOProduct(){ return daoProduct;}
    public DAOEatenProducts getDAOEatenProducts(){ return daoEatenProducts;}
    public DAOLifeStyle getDAOLifeStyle(){ return daoLifeStyle;}
}
