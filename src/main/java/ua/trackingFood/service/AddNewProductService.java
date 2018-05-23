package ua.trackingFood.service;

import ua.trackingFood.dao.DAOEatenProducts;
import ua.trackingFood.dao.DAOProduct;
import ua.trackingFood.dao.Impl.DAOFactory;
import ua.trackingFood.entity.EatenProducts;
import ua.trackingFood.entity.Product;
import ua.trackingFood.exception.DAOException;
import ua.trackingFood.exception.ServiceException;

import java.math.BigDecimal;
import java.util.logging.Logger;

public class AddNewProductService {
    private DAOEatenProducts daoEatenProducts = DAOFactory.getDaoFactory().getDAOEatenProducts();
    private DAOProduct daoProduct = DAOFactory.getDaoFactory().getDAOProduct();
    //private DAOProduct daoProduct = DAOFactory.getDaoFactory().getDAOProduct();
    private Logger logger = Logger.getLogger("AddEatenProductsService.class");

    public void createProduct(String categoryId,String name,String energyValue,String proteins,String carbohydrates,String fats) throws ServiceException {
        Product product = new Product();
        product.setCategoryId(Integer.parseInt(categoryId));
        product.setName(name);
        product.setEnergyValue(new BigDecimal(energyValue));
        product.setProteins(new BigDecimal(proteins));
        product.setCarbohydrates(new BigDecimal(carbohydrates));
        product.setFats(new BigDecimal(fats));
        BigDecimal result = product.getProteins().add(product.getCarbohydrates().add(product.getFats()));
        if(product.getEnergyValue().add(new BigDecimal(0.1)).compareTo(result) == 1 &&
                product.getEnergyValue().subtract(new BigDecimal(0.1)).compareTo(result) == -1){
            try{
                daoProduct.create(product);
            } catch (DAOException e) {
//
            }
        }else
        {
            throw new ServiceException();
        }
    }
}
