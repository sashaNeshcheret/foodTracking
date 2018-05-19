package ua.simpleproject.services;

import ua.simpleproject.dao.DAOProduct;
import ua.simpleproject.dao.Impl.DAOFactory;
import ua.simpleproject.entity.Product;
import ua.simpleproject.exception.DAOException;

import java.util.Objects;

public class CreateProductLogic {
    private final static DAOProduct daoProduct = DAOFactory.getDaoFactory().getDaoProduct();

    public boolean createProductStock(Product product) throws DAOException {
        product.getCodeProduct();
        product.getName();
        Product oldProduct = null;
        oldProduct = daoProduct.read(product.getCodeProduct());

        if (Objects.isNull(oldProduct)) {
            daoProduct.create(product);
            return true;
        }
        return false;
    }
}
