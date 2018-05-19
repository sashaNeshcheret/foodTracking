package ua.trackingFood.service;

import ua.trackingFood.dao.DAOEatenProducts;
import ua.trackingFood.dao.DAOProduct;
import ua.trackingFood.dao.Impl.DAOFactory;
import ua.trackingFood.entity.EatenProducts;
import ua.trackingFood.entity.Product;
import ua.trackingFood.exception.DAOException;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ShowEatenProductsService {
    private DAOEatenProducts daoEatenProducts = DAOFactory.getDaoFactory().getDAOEatenProducts();
    private DAOProduct daoProduct = DAOFactory.getDaoFactory().getDAOProduct();
    //private DAOProduct daoProduct = DAOFactory.getDaoFactory().getDAOProduct();
    private Logger logger = Logger.getLogger("AddEatenProductsService.class");


    //по айди продукта берем значения параметров продуктов
    //получение параметри  перемножем на вес
    //результат записіваем в entity eatenProducts


    public List<EatenProducts> getEatenProductList(int userId){
        List<EatenProducts> list = new ArrayList<>();
        LocalDate date = LocalDate.now();
        Date dateSql = Date.valueOf(date);
        try {
            list = daoEatenProducts.read(dateSql, userId);
        } catch (DAOException e) {
//
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
    /**Read info about exists product's category
     */
    /*public List<CategoryProducts> readCategory() {
        List<CategoryProducts> categoryProductsList = null;
        try {
            categoryProductsList = daoEatenProducts.read();
        } catch (DAOException e) {
//
        }
        return categoryProductsList;
    }*/
    /**Read info about exists product's category
     */
    public List<Product> getProductsList(int categoryId) {
        List<Product> productList = null;
        try {
            productList = daoProduct.read(categoryId);
        } catch (DAOException e) {
//
        }
        return productList;
    }
    public Product getProduct(int id) {
        Product product = null;
        try {
            product = daoProduct.readById(id);
        } catch (DAOException e) {
//
        }
        return product;
    }
}
