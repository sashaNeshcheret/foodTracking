package ua.simpleproject.entity;

import java.math.BigDecimal;

public class CurrentCheck {
    private int id;
    private int productID;
    private int userId;
    private BigDecimal count;
    private BigDecimal resultPrice;
    public CurrentCheck(int id, int productID, int userId, BigDecimal count, BigDecimal resultPrice) {
        this.id = id;
        this.productID = productID;
        this.userId = userId;
        this.count = count;
        this.resultPrice = resultPrice;
    }
    public CurrentCheck() {

    }
    public int getId() {
        return id;
    }
    public int getUserId() {
        return userId;
    }
    public int getProductID() {
        return productID;
    }
    public BigDecimal getCount() {
        return count;
    }
    public BigDecimal getResultPrice() {
        return resultPrice;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void setProductID(int productID) {
        this.productID = productID;
    }
    public void setCount(BigDecimal count) {
        this.count = count;
    }
    public void setResultPrice(BigDecimal resultPrice) {
        this.resultPrice = resultPrice;
    }
}
