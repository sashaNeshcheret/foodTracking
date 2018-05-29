package ua.trackingFood.service;

import org.apache.log4j.Logger;
import ua.trackingFood.dao.*;
import ua.trackingFood.dao.Impl.DAOFactory;
import ua.trackingFood.entity.*;
import ua.trackingFood.exception.DAOException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static ua.trackingFood.utils.resourceHolders.AttributesHolder.*;

public class GeneralService {
    private static final Logger LOGGER = Logger.getLogger(GeneralService.class.getSimpleName());
    private static final DAOCategoryProducts daoCategoryProducts = DAOFactory.getDaoFactory().getDAOCategoryProducts();
    private static final DAOProduct daoProduct = DAOFactory.getDaoFactory().getDAOProduct();
    private static final DAOUserResult daoUserResult = DAOFactory.getDaoFactory().getDAOUserResult();
    private static final DAOUserParam daoUserParam = DAOFactory.getDaoFactory().getDAOUserParam();


    /**Read info about exists product's category
     */
    public List<CategoryProducts> readCategories() {
        List<CategoryProducts> categoryProductsList = null;
        try {
            categoryProductsList = daoCategoryProducts.read();
        } catch (DAOException e) {
            LOGGER.error("method readCategories thrown DAOException", e);
        }
        return categoryProductsList;
    }
    /**Read info about exists product's category
            */
    public CategoryProducts readCategory(int categoryId) {
        CategoryProducts categoryProducts = null;
        try {
            categoryProducts = daoCategoryProducts.read(categoryId);
        } catch (DAOException e) {
            LOGGER.error("method readCategory thrown DAOException", e);
        }
        return categoryProducts;
    }
    /**Read info about exists product's category
     */
    public List<Product> getProductsList(int categoryId, int from, int size) {
        List<Product> productList = null;
        try {
            productList = daoProduct.read(categoryId, from, size);
        } catch (DAOException e) {
            LOGGER.error("method readCategory thrown DAOException", e);
        }
        return productList;
    }

    public UserResult readUserResultInfo(int userId) {
        UserResult userResult = null;
        try {
            userResult = daoUserResult.read(userId);
        } catch (DAOException e) {
            LOGGER.error("method readUserResultInfo thrown DAOException", e);
        }
        return userResult;
    }
    public UserParam readUserParamInfo(int userId) {
        UserParam userParam = null;
        try {
            userParam = daoUserParam.read(userId);
        } catch (DAOException e) {
            LOGGER.error("method readUserParamInfo thrown DAOException", e);
        }
        return userParam;
    }

    public EatenProducts availableBalance(UserResult userResult, EatenProducts eatenProduct) {
        if(Objects.isNull(userResult) || Objects.isNull(eatenProduct)){
            return null;
        }
        EatenProducts eatenProducts = new EatenProducts();
        eatenProducts.setEnergyValue(userResult.getExpectedNormCalories().subtract(eatenProduct.getEnergyValue()));
        eatenProducts.setProteins(userResult.getProteins().subtract(eatenProduct.getProteins()));
        eatenProducts.setCarbohydrates(userResult.getCarbohydrates().subtract(eatenProduct.getCarbohydrates()));
        eatenProducts.setFats(userResult.getFats().subtract(eatenProduct.getFats()));
        return  eatenProducts;
    }
    public String consumptionAnalysis(EatenProducts eatenProducts, UserResult userResult){
        StringBuilder message = new StringBuilder();
        String messageError = "";
        if(Objects.isNull(userResult) || Objects.isNull(eatenProducts)){
            return null;
        }
        if(eatenProducts.getEnergyValue().compareTo(userResult.getExpectedNormCalories().multiply(new BigDecimal("0.8"))) == 1){
            message.append("Ви наближаєтесь до максимального значенняя спожитих калорій. ");
        }
        if(eatenProducts.getEnergyValue().compareTo(userResult.getExpectedNormCalories()) == 1){
            messageError = "Ви перевищили максимальне значенняя спожитих калорій. ";
            return messageError;
        }
        if(eatenProducts.getProteins().compareTo(userResult.getProteins()) != -1){
            message.append("Треба припинити вживання білків. ");
        }
        else if(eatenProducts.getProteins().compareTo(eatenProducts.getEnergyValue().multiply(new BigDecimal("0.2"))) == -1){
            message.append("Треба збільшити кількість протеїну. ");
        }
        else if(eatenProducts.getProteins().compareTo(eatenProducts.getEnergyValue().multiply(new BigDecimal("0.3"))) == 1){
            message.append("Треба зменшити кількість протеїну. ");
        }
        if(eatenProducts.getFats().compareTo(userResult.getFats()) != -1){
            message.append("Треба припинити вживання жирів. ");
        }
        else if(eatenProducts.getFats().compareTo(eatenProducts.getEnergyValue().multiply(new BigDecimal("0.15"))) == -1){
            message.append("Треба збільшити кількість жирів. ");
        }
        else if(eatenProducts.getFats().compareTo(eatenProducts.getEnergyValue().multiply(new BigDecimal("0.25"))) == 1){
            message.append("Треба зменшити кількість жирів. ");
        }
        if(eatenProducts.getCarbohydrates().compareTo(userResult.getCarbohydrates()) != -1){
            message.append("Треба припинити вживання вуглеводів. ");
        }
        else if(eatenProducts.getCarbohydrates().compareTo(eatenProducts.getEnergyValue().multiply(new BigDecimal("0.4"))) == -1){
            message.append("Треба збільшити кількість вуглеводів. ");
        }
        else if(eatenProducts.getCarbohydrates().compareTo(eatenProducts.getEnergyValue().multiply(new BigDecimal("0.5"))) == 1){
            message.append("Треба зменшити кількість вуглеводів. ");
        }
        return message.toString();

    }
    public int paginationCategory(int category, String nameSubmit1, String nameSubmit2) throws DAOException {
        if(Objects.equals(ATTR_PREVIOUS, nameSubmit1)){
            --category;
        }
        else if(Objects.equals(ATTR_NEXT, nameSubmit2)) {
            int numberCategories = daoCategoryProducts.count();
            if (!(category == numberCategories-1)) {
                ++category;
            }else{
                category = -1;
            }
        }
        return category;
    }
    public int paginationProducts(int from, String nameSubmit3, String nameSubmit4) throws DAOException {
        if(Objects.equals(ATTR_PREVIOUS_PROD, nameSubmit3)){
            --from;
        }
        else if(Objects.equals(ATTR_NEXT_PROD, nameSubmit4)){
            ++from;
        }
        return from;
    }
}
