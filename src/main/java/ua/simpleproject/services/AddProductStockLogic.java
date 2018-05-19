package ua.simpleproject.services;

import ua.simpleproject.DTO.ProductCurrentCheck;
import ua.simpleproject.dao.DAOCurrentCheck;
import ua.simpleproject.dao.DAOProduct;
import ua.simpleproject.dao.DAOStock;
import ua.simpleproject.dao.DAOUsers;
import ua.simpleproject.dao.Impl.DAOFactory;
import ua.simpleproject.entity.Product;
import ua.simpleproject.entity.Stock;
import ua.simpleproject.exception.ConnectionException;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.exception.LogicException;
import ua.simpleproject.exception.TransactionException;
import ua.simpleproject.transactions.TransactionManager;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddProductStockLogic {
    private final static DAOStock daoStock = DAOFactory.getDaoFactory().getDaoStock();
    private final static DAOProduct daoProduct = DAOFactory.getDaoFactory().getDaoProduct();


    public boolean addProductStock(Product product, BigDecimal number) throws DAOException {
        Product oldProduct = null;
        Stock stock = null;
        oldProduct = daoProduct.read(product.getCodeProduct());
        /*if (codeStr.equals("")) {
            if (!name.equals("")) {
                product = AddProductLogic.defineIdProducts(name);
                System.out.println(product);
            }
        } else{
            int code = Integer.parseInt(codeStr);
            product = AddProductLogic.defineIdProducts(code);
            System.out.println(product.getName());
        }*/
        try {
            stock = daoStock.read(oldProduct);
            BigDecimal newNumber = number.add(stock.getNumber());
            stock.setNumber(newNumber);
            daoStock.update(stock);

        } catch (LogicException e) {
            daoStock.create(oldProduct, number);
        }
        return true;
    }
}
