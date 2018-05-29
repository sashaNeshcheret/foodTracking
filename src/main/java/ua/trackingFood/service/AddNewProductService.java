package ua.trackingFood.service;

import org.apache.log4j.Logger;
import ua.trackingFood.command.AddNewCategoryCommand;
import ua.trackingFood.dao.DAOProduct;
import ua.trackingFood.dao.Impl.DAOFactory;
import ua.trackingFood.entity.Product;
import ua.trackingFood.exception.DAOException;
import ua.trackingFood.exception.ServiceException;

import java.math.BigDecimal;

public class AddNewProductService {
    private static final DAOProduct daoProduct = DAOFactory.getDaoFactory().getDAOProduct();
    private static final Logger LOGGER = Logger.getLogger(AddNewProductService.class.getSimpleName());

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
                LOGGER.error("method createProduct thrown DAOException", e);
            }
        }else
        {
            throw new ServiceException();
        }
    }
}
