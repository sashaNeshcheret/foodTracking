package ua.simpleproject.entity;

import java.math.BigDecimal;
import java.sql.Date;

public class CheckReports {
    private int id;
    private int userId;
    private int numberOfProduct;
    private BigDecimal checkAmount;
    private Date date;

    public CheckReports() {
    }

    public CheckReports(int id, int userId, int numberOfProduct, BigDecimal checkAmount, Date date) {
        this.id = id;
        this.userId = userId;
        this.numberOfProduct = numberOfProduct;
        this.checkAmount = checkAmount;
        this.date = date;
    }

    public CheckReports(int userId, int numberOfProduct, BigDecimal checkAmount) {
        this.userId = userId;
        this.numberOfProduct = numberOfProduct;
        this.checkAmount = checkAmount;
    }

    public int getId() {
        return id;
    }
    public int getUserId() {
        return userId;
    }
    public int getNumberOfProduct() {
        return numberOfProduct;
    }
    public BigDecimal getCheckAmount() {
        return checkAmount;
    }
    public Date getDate() {
        return date;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setNumberOfProduct(int numberOfProduct) {
        this.numberOfProduct = numberOfProduct;
    }

    public void setCheckAmount(BigDecimal checkAmount) {
        this.checkAmount = checkAmount;
    }



    public void setDate(Date date) {
        this.date = date;
    }
}
