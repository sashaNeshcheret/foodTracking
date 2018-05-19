package ua.simpleproject.entity;

import java.math.BigDecimal;

public class Stock {
    private int id;
    private int productId;
    private BigDecimal number;

    public Stock() {
    }

    public Stock(int productId, BigDecimal number) {
        this.productId = productId;
        this.number = number;
    }
    public int getId() {
        return id;
    }
    public int getProductId() {
        return productId;
    }
    public BigDecimal getNumber() {
        return number;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public void setNumber(BigDecimal number) {
        this.number = number;
    }
}
