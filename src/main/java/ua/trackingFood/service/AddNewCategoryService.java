package ua.trackingFood.service;

import ua.trackingFood.dao.DAOCategoryProducts;
import ua.trackingFood.dao.DAOEatenProducts;
import ua.trackingFood.dao.DAOProduct;
import ua.trackingFood.dao.Impl.DAOFactory;
import ua.trackingFood.entity.CategoryProducts;
import ua.trackingFood.entity.Product;
import ua.trackingFood.exception.DAOException;
import ua.trackingFood.exception.ServiceException;

import java.math.BigDecimal;
import java.util.logging.Logger;

public class AddNewCategoryService {
    private DAOCategoryProducts daoCategoryProducts = DAOFactory.getDaoFactory().getDAOCategoryProducts();
    private Logger logger = Logger.getLogger("AddNewCategoryService.class");


    public void createCategory(CategoryProducts categoryProducts) throws ServiceException {
        try {
            daoCategoryProducts.create(categoryProducts);
        } catch (DAOException e) {
//
        }
    }
}
