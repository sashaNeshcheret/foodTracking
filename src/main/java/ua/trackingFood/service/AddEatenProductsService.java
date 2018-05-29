package ua.trackingFood.service;

import org.apache.log4j.Logger;
import ua.trackingFood.dao.DAOEatenProducts;
import ua.trackingFood.dao.DAOProduct;
import ua.trackingFood.dao.Impl.DAOFactory;
import ua.trackingFood.entity.EatenProducts;
import ua.trackingFood.entity.Product;
import ua.trackingFood.exception.DAOException;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AddEatenProductsService {
    private static final DAOEatenProducts daoEatenProducts = DAOFactory.getDaoFactory().getDAOEatenProducts();
    private static final Logger LOGGER = Logger.getLogger(AddEatenProductsService.class.getSimpleName());


    public void createEatenProducts(Product product, BigDecimal weightProduct, int userId){
        EatenProducts eatenProducts = null;
        List<EatenProducts> list = new ArrayList<>();
        try {
            list = daoEatenProducts.read(userId);
            for(EatenProducts products : list){
                if(products.getProductId() == product.getId()){
                    BigDecimal weight = products.getWeight().add(weightProduct);
                    eatenProducts = countProductsParam(product, weight, userId);
                    daoEatenProducts.update(products.getId(),eatenProducts);
                    return;
                }
            }
            eatenProducts = countProductsParam(product, weightProduct, userId);
            daoEatenProducts.create(eatenProducts);
        } catch (DAOException e) {
            LOGGER.error("method createEatenProducts thrown DAOException", e);
        }
    }

    private EatenProducts countProductsParam(Product product, BigDecimal weightProduct, int userId) {
        EatenProducts eatenProducts = new EatenProducts();
        eatenProducts.setUserId(userId);
        eatenProducts.setProductId(product.getId());
        eatenProducts.setWeight(weightProduct);
        BigDecimal weight = weightProduct.divide(new BigDecimal(100));
        eatenProducts.setEnergyValue(product.getEnergyValue().multiply(weight));
        eatenProducts.setProteins(product.getProteins().multiply(weight));
        eatenProducts.setCarbohydrates(product.getCarbohydrates().multiply(weight));
        eatenProducts.setFats(product.getFats().multiply(weight));
        return eatenProducts;
    }
    public void deleteProduct(int productId){
        try {
            daoEatenProducts.delete(productId);
        } catch (DAOException e) {
            LOGGER.error("method countProductsParam thrown DAOException", e);
        }
    }
}
