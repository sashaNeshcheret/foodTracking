package ua.trackingFood.service;

import org.apache.log4j.Logger;
import ua.trackingFood.dao.DAOEatenProducts;
import ua.trackingFood.dao.DAOProduct;
import ua.trackingFood.dao.Impl.DAOFactory;
import ua.trackingFood.entity.EatenProducts;
import ua.trackingFood.entity.Product;
import ua.trackingFood.exception.DAOException;
import ua.trackingFood.service.GeneralService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShowEatenProductsService {
    private static final DAOEatenProducts daoEatenProducts = DAOFactory.getDaoFactory().getDAOEatenProducts();
    private static final DAOProduct daoProduct = DAOFactory.getDaoFactory().getDAOProduct();
    private static final Logger LOGGER = Logger.getLogger(GeneralService.class.getSimpleName());


    //по айди продукта берем значения параметров продуктов
    //получение параметри  перемножем на вес
    //результат записіваем в entity eatenProducts


    public List<EatenProducts> getEatenProductList(int userId){
        List<EatenProducts> list = new ArrayList<>();
        try {
            list = daoEatenProducts.read(userId);
        } catch (DAOException e) {
            LOGGER.error("method createEatenProducts thrown DAOException", e);
        }
        return list;
    }

    public EatenProducts getResultEatenProduct(List<EatenProducts> list){

        EatenProducts eatenProducts = new EatenProducts();
        eatenProducts.setEnergyValue(new BigDecimal("0"));
        eatenProducts.setProteins(new BigDecimal("0"));
        eatenProducts.setCarbohydrates(new BigDecimal("0"));
        eatenProducts.setFats(new BigDecimal("0"));
        for(EatenProducts eatenProduct : list){
            eatenProducts.setEnergyValue(eatenProducts.getEnergyValue().add(eatenProduct.getEnergyValue()));
            eatenProducts.setProteins(eatenProducts.getProteins().add(eatenProduct.getProteins()));
            eatenProducts.setCarbohydrates(eatenProducts.getCarbohydrates().add(eatenProduct.getCarbohydrates()));
            eatenProducts.setFats(eatenProducts.getFats().add(eatenProduct.getFats()));
        }
        return eatenProducts;
    }
    
    public List<Product> getProductsList(int categoryId, int from, int size) {
        List<Product> productList = null;
        try {
            productList = daoProduct.read(categoryId, from, size);
        } catch (DAOException e) {
            LOGGER.error("method getProductsList thrown DAOException", e);
        }
        return productList;
    }
    public Product getProduct(int id) {
        Product product = null;
        try {
            product = daoProduct.readById(id);
        } catch (DAOException e) {
            LOGGER.error("method getProduct thrown DAOException", e);
        }
        return product;
    }
}
