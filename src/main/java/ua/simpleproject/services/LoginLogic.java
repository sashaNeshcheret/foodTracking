package ua.simpleproject.services;

import ua.simpleproject.DTO.ProductCurrentCheck;
import ua.simpleproject.dao.DAOProduct;
import ua.simpleproject.dao.DAOStock;
import ua.simpleproject.dao.DAOUsers;
import ua.simpleproject.entity.Product;
import ua.simpleproject.entity.Stock;
import ua.simpleproject.exception.RegistrationException;
import ua.simpleproject.dao.Impl.DAOFactory;
import ua.simpleproject.entity.User;
import ua.simpleproject.exception.DAOException;

import java.util.ArrayList;
import java.util.List;

public class LoginLogic {
    private DAOFactory daoFactory = DAOFactory.getDaoFactory();
    private DAOUsers daoUsers = daoFactory.getDaoUsers();
    private DAOStock daoStock = daoFactory.getDaoStock();
    private DAOProduct daoProduct = daoFactory.getDaoProduct();

   public boolean checkLogin(String enterLogin, String enterPass) {
       User user = null;
       try {
           if(enterLogin == null){
               return false;
           }
           user = daoUsers.read(enterLogin);
           if(enterPass == null || user == null){
               return false;
           }
           if(enterLogin.equals(user.getLogin()) && enterPass.equals(user.getPassword())){
               return true;
           }
       } catch (DAOException e) {
          e.printStackTrace();// log4j
       }
       return false;
   }
   public User getUserByLogin(String login){
       User user = null;
       try {
           user = DAOFactory.getDaoFactory().getDaoUsers().read(login);
           return user;
       } catch (DAOException e) {
           e.printStackTrace();// log4j
       }
       return user;
   }
   public List<ProductCurrentCheck> getStock(int numPage) throws DAOException {

       int start = (numPage-1)*10;
       int end = (numPage)*10-1;
       List<ProductCurrentCheck> list = new ArrayList<>();
       List<Stock> stockList = daoStock.read(start, end);
       for(Stock stock : stockList){
           ProductCurrentCheck productCurrentCheck = new ProductCurrentCheck();
           Product product = daoProduct.readById(stock.getProductId());
           productCurrentCheck.setNameProduct(product.getName());
           productCurrentCheck.setCodeProduct(product.getCodeProduct());
           productCurrentCheck.setNumberOfProduct(stock.getNumber());
           productCurrentCheck.setPriceForOne(product.getPriceForOne());
           list.add(productCurrentCheck);
       }
       return list;
   }
}