package ua.simpleproject.services;

import ua.simpleproject.dao.*;
import ua.simpleproject.dao.Impl.DAOFactory;
import ua.simpleproject.entity.CurrentCheck;
import ua.simpleproject.entity.Product;
import ua.simpleproject.DTO.ProductCurrentCheck;
import ua.simpleproject.entity.Stock;
import ua.simpleproject.entity.User;
import ua.simpleproject.exception.ConnectionException;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.exception.LogicException;
import ua.simpleproject.exception.TransactionException;
import ua.simpleproject.transactions.TransactionManager;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddProductLogic {
    private final static DAOCurrentCheck daoCurrentCheck = DAOFactory.getDaoFactory().getDaoCurrentCheck();
    private final static DAOUsers daoUser = DAOFactory.getDaoFactory().getDaoUsers();
    private final static DAOStock daoStock = DAOFactory.getDaoFactory().getDaoStock();
    private final static DAOProduct daoProduct = DAOFactory.getDaoFactory().getDaoProduct();


//   по id перевірити чи існує даний товар на складі в достатній кількості, якщо ні то повернути повідомлення про помилку
//   якщо існує то виконати транзакцію
    // зменшити кількість на складі
    //збільшити кількість в current check
    //з отриманих обєктів сформувати ProductCurrentCheck, додати його до списку і передати її в jsp

    public static List<ProductCurrentCheck> addProduct(String name, String codeStr, String numberStr, String login) throws DAOException, LogicException {
        Product product = null;
        List<ProductCurrentCheck> productCurrentCheckList = new ArrayList<>();
        if (codeStr.equals("")) {
            if (!name.equals("")) {
                product = AddProductLogic.defineIdProducts(name);
                System.out.println(product);
            }
        } else{
            int code = Integer.parseInt(codeStr);
            product = AddProductLogic.defineIdProducts(code);
            System.out.println(product.getName());
        }

System.out.println("begin transaction");
        try{
            TransactionManager.beginTransaction();
            System.out.println(" transaction");

            reduceOnStock(product, numberStr);
            addToCurrentCheck(product, numberStr, login);
            productCurrentCheckList = getCurrentCheck(login);
//throw new TransactionException();
            TransactionManager.endTransaction();

            //System.out.println("end transaction");
        } catch (TransactionException | ConnectionException e) {
            try {
                TransactionManager.rollBack();
            } catch (SQLException sqlE) {
                e.printStackTrace();
            }
        }

        return productCurrentCheckList;

    }

//   по назві визначити іd товару
    public static Product defineIdProducts(String name) throws DAOException {
        Product product = daoProduct.read(name);
        return  product;
    }
//    по коду визначити іd товару
    public static Product defineIdProducts(int code) throws DAOException {
        Product product = daoProduct.read(code);
        return  product;
    }
    public static void reduceOnStock(Product product, String numberStr) throws LogicException, DAOException {//треба кидати Exception замість boolean
        Stock stock = daoStock.read(product);
//System.out.println(1);
        BigDecimal number = new BigDecimal(numberStr);
//System.out.println(number);
        if(stock.getNumber().compareTo(number) == 1){
            BigDecimal bigDecimal = stock.getNumber().subtract(number);
            stock.setNumber(bigDecimal);
            daoStock.update(stock);
        }
        else{
            throw new LogicException("Number products on stock lesser than you want get");
        }

    }
    public static boolean addToCurrentCheck(Product product, String numberStr, String login) throws DAOException {//треба кидати Exception замість boolean
        BigDecimal number = new BigDecimal(numberStr);
        User user = null;
        List<CurrentCheck> list = daoCurrentCheck.read(login);

        user = daoUser.read(login);
        daoCurrentCheck.read(login);

        for(CurrentCheck currentCheck : list){
            if(currentCheck.getProductID() == product.getId()){
                CurrentCheck newCurrentCheck = new CurrentCheck();
                BigDecimal newCount = currentCheck.getCount().add(number);
                BigDecimal newResultPrice = newCount.multiply(product.getPriceForOne());

                newCurrentCheck.setUserId(currentCheck.getUserId());
                newCurrentCheck.setProductID(currentCheck.getProductID());
                newCurrentCheck.setCount(newCount);
                newCurrentCheck.setResultPrice(newResultPrice);

                daoCurrentCheck.update(newCurrentCheck);
                return true;
            }
        }

        CurrentCheck currentCheck = new CurrentCheck();
        currentCheck.setUserId(user.getId());
        currentCheck.setProductID(product.getId());
        currentCheck.setCount(number);
        currentCheck.setResultPrice(product.getPriceForOne().multiply(number));
        daoCurrentCheck.create(currentCheck);

        return true;
    }

    public static List<ProductCurrentCheck> getCurrentCheck(String userId) throws DAOException {//треба кидати Exception замість boolean
        List<CurrentCheck> listCheck = daoCurrentCheck.read(userId);
        List<ProductCurrentCheck>  listProductsInCheck = new ArrayList<>();

        for(CurrentCheck currentCheck : listCheck){
            ProductCurrentCheck productCurrentCheck = new ProductCurrentCheck();
            Product product = daoProduct.readById(currentCheck.getProductID());
            productCurrentCheck.setNameProduct(product.getName());
            productCurrentCheck.setCodeProduct(product.getCodeProduct());
            productCurrentCheck.setNumberOfProduct(currentCheck.getCount());
            productCurrentCheck.setPriceForOne(product.getPriceForOne());
            productCurrentCheck.setResultPrice(currentCheck.getResultPrice());
            listProductsInCheck.add(productCurrentCheck);
        }
        return listProductsInCheck;
    }
}
