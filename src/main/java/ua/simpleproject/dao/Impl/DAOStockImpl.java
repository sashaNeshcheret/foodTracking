package ua.simpleproject.dao.Impl;


import ua.simpleproject.dao.DAOStock;
import ua.simpleproject.entity.Product;
import ua.simpleproject.entity.Stock;
import ua.simpleproject.exception.ConnectionException;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.exception.LogicException;
import ua.simpleproject.transactions.ConnectionPool;
import ua.simpleproject.transactions.ConnectionWrapper;
import ua.simpleproject.transactions.TransactionManager;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOStockImpl implements DAOStock {
    public static final String SQL_UPDATE = "UPDATE stock SET stock.number = ? WHERE product_id = ?";
    public static final String SQL_INSERT = "INSERT INTO stock (product_id, number) VALUES (?,?)";
    public static final String SQL_SELECT = "SELECT * FROM stock LIMIT ?,?";
    protected DAOStockImpl(){
    }

    public List<Stock> read(int start, int end) throws DAOException{
        List<Stock> list = new ArrayList<>();
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            Stock stock = new Stock();

            PreparedStatement preparedStatement = connection.preparedStatement(SQL_SELECT);
            preparedStatement.setInt(1,start);
            preparedStatement.setInt(2,end);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                stock.setId(resultSet.getInt(1));
                stock.setProductId(resultSet.getInt(2));
                stock.setNumber(resultSet.getBigDecimal(3));
                list.add(stock);
            }
        } catch (SQLException | ConnectionException e) {
            throw new DAOException("From method read from stock hasn't got products", e);
        }
        return list;
    }

    public Stock read(Product product) throws DAOException, LogicException {
        Stock stock = new Stock();
        ResultSet resultSet = null;
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            String sqlSelect = "SELECT * FROM cash_register.stock WHERE product_id = (" +
                    "SELECT id FROM cash_register.products WHERE code_product = ?)";
            PreparedStatement preparedStatement = connection.preparedStatement(sqlSelect);
            preparedStatement.setInt(1,product.getCodeProduct());
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                stock.setId(resultSet.getInt(1));
                stock.setProductId(resultSet.getInt(2));
                stock.setNumber(resultSet.getBigDecimal(3));
            }else{
                throw new LogicException();
            }
        } catch (SQLException | ConnectionException e) {
            throw new DAOException("From method selectFromStock hasn't got product"+ product, e);
        }
        return stock;
    }

    public void update(Stock stock) throws DAOException {
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_UPDATE);
            preparedStatement.setBigDecimal(1, stock.getNumber());
            preparedStatement.setInt(2, stock.getProductId() );
            preparedStatement.executeUpdate();
        }catch (SQLException | ConnectionException e){
            throw new DAOException("Mistake in update stock", e);
        }
    }
    public void create(Product product, BigDecimal number) throws DAOException {
        try(ConnectionWrapper connection = TransactionManager.getConnection()){
            PreparedStatement pstatement = connection.preparedStatement(SQL_INSERT);
            pstatement.setInt(1, product.getId());
            System.out.println(product.getId());
            pstatement.setBigDecimal(2, number);
            pstatement.executeUpdate();
        }catch (SQLException | ConnectionException e){
            throw new DAOException("Mistake in insert to stock", e);
        }
    }

/*
    public ResultSet selectFromStock(Connection connection, int code) throws SQLException {
        String sqlSelect = "SELECT * FROM cash_register.stock WHERE product_id = (" +
                "SELECT id FROM cash_register.products WHERE code_product = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
        preparedStatement.setInt(1,code);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
    public ResultSet selectFromStock(Connection connection, String name) throws SQLException {
        String sqlSelect = "SELECT * FROM cash_register.stock WHERE product_id = (" +
                "SELECT id FROM cash_register.products WHERE name = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
        preparedStatement.setString(1,name);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }


    public void insertToStock(Connection connection, Stock stock){
        try{
            String sqlINSERT = "INSERT INTO cash_register.stock (product_id, number) VALUES (?,?)";
            PreparedStatement pstatement = connection.prepareStatement(sqlINSERT);
            pstatement.setInt(1, stock.getProductId());
            pstatement.setBigDecimal(2, stock.getNumber());
            pstatement.executeUpdate();
        }catch (SQLException sqlEx){
            System.out.println("Mistake in insert");
            sqlEx.printStackTrace();
            return;
        }
    }

    public void insertToStock(Connection connection, String name, int number){
        try{
            int id = 0;
            Product product = DAOFactory.getDaoFactory().getDaoProduct().read(name); //

            String sqlINSERT = "INSERT INTO cash_register.stock (product_id, number) VALUES (?,?)";
            PreparedStatement pstatement = connection.prepareStatement(sqlINSERT);
            pstatement.setInt(1, product.getId());
            pstatement.setInt(2, number);
            pstatement.executeUpdate();
        }catch (SQLException sqlEx){
            System.out.println("Mistake in insert");
            sqlEx.printStackTrace();
            return;
        }
    }
    public void updateStock(Connection connection, Stock stock, int id, BigDecimal number){
        try{
            String sqlUpdate = "UPDATE cash_register.stock SET stock.number = ? WHERE id = ?";
            PreparedStatement pstatement = connection.prepareStatement(sqlUpdate);
            BigDecimal bigDecimal = stock.getNumber().add(number);
            pstatement.setBigDecimal(1, bigDecimal);
            pstatement.setInt(2, id );
            pstatement.executeUpdate();
        }catch (SQLException sqlEx){
            System.out.println("Mistake in update");
            sqlEx.printStackTrace();
            return;
        }
    }
    public void insertToStock(Connection connection, Product product, int number){
        try{
            int id = 0;
            ResultSet resultSet = (new DAOProduct()).selectFromProduct(connection, product);
            while(resultSet.next()){
                id = resultSet.getInt(1);
            }
            String sqlINSERT = "INSERT INTO cash_register.stock (product_id, number) VALUES (?,?)";
            PreparedStatement pstatement = connection.prepareStatement(sqlINSERT);
            pstatement.setInt(1, id);
            pstatement.setInt(2, number);
            pstatement.executeUpdate();
        }catch (SQLException sqlEx){
            System.out.println("Mistake in insert");
            sqlEx.printStackTrace();
            return;
        }
    }
    public ResultSet selectFromStock(Connection connection, Stock stock) throws SQLException {
        String sqlSelect = "SELECT * FROM cash_register.stock WHERE product_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
        preparedStatement.setInt(1,stock.getProductId());
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
    public void add(Connection connection, Stock stock){
        int id = -1;
        int number = 0;
        try{
            ResultSet resultSet = selectFromStock(connection,stock);
            while (resultSet.next()){
                id = resultSet.getInt(1);
                number = resultSet.getInt(3);
            }
            if(id == -1){
                insertToStock(connection, stock);
            }else{
                updateStock(connection, stock, id, number);
            }
        }catch (SQLException sqlEx){
            System.out.println("Mistake in adding");
            sqlEx.printStackTrace();
            return;
        }
    }
    public void addByCode(Connection connection, Product product, int number){
        int productId = -1;
        int productNumber = 0;
        try{
            ResultSet resultSet = selectFromStock(product);
            insertToStock(connection, product, number);
            }else{
                updateStock(connection, productId, productNumber);
            }
        }catch (SQLException sqlEx){
            System.out.println("Mistake in adding");
            sqlEx.printStackTrace();
            return;
        }
    }
    public void addByCode(Connection connection, int code, int number){
        int productId = -1;
        int productNumber = 0;
        try{
            ResultSet resultSet = selectFromStock(connection, code);
            while (resultSet.next()){
                productId = resultSet.getInt(1);
                productNumber = resultSet.getInt(3);
            }
            if(productId == -1){
                insertToStock(connection, code, number);
            }else{
                updateStock(connection, productId, productNumber);
            }
        }catch (SQLException sqlEx){
            System.out.println("Mistake in adding");
            sqlEx.printStackTrace();
            return;
        }
    }
    public void addByName(Connection connection, Product product, int number) {

        Stock stock = null;
        try{
            stock = selectFromStock(product);
            insertToStock(connection, product, number);
        } catch (LogicException e) {
            updateStock(connection, product.getId(), number);
        }



    }
    public void addByName(Connection connection, String name, int number){
        int productId = -1;
        int productNumber = 0;
        try{
            ResultSet resultSet = selectFromStock(connection, name);
            while (resultSet.next()){
                productId = resultSet.getInt(1);
                productNumber = resultSet.getInt(3);
            }
            if(productId == -1){
                insertToStock(connection, name, number);
            }else{
                updateStock(connection, productId, productNumber);
            }
        }catch (SQLException sqlEx){
            System.out.println("Mistake in adding");
            sqlEx.printStackTrace();
            return;
        }
    }
    public void show(Connection connection) {
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM cash_register.stock WHERE stock.id > 0");
            while (resultSet.next()){
                System.out.print("id = " + resultSet.getInt(1));
                System.out.print(", product : " + (DAOFactory.getDaoFactory().daoProduct).show(connection, resultSet.getString(2)));;
                System.out.print(", number = " + resultSet.getInt(4));
                System.out.println();
            }
        } catch (SQLException sqlEx) {
            System.out.println("Mistake in show");
            sqlEx.printStackTrace();
            return;
        }
    }*/
}
