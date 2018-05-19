package ua.simpleproject.entity;

import java.math.BigDecimal;

public class Product {
    private int id;
    private int codeProduct;
    private String name;
    private boolean countable;
    private BigDecimal priceForOne;

    public Product() {
    }
    public Product(int codeProduct, String name, boolean countable, BigDecimal priceForOne) {
        this.codeProduct = codeProduct;
        this.name = name;
        this.countable = countable;
        this.priceForOne = priceForOne;
    }
    public Product(int id, int codeProduct, String name, boolean countable, BigDecimal priceForOne) {
        this.id = id;
        this.codeProduct = codeProduct;
        this.name = name;
        this.countable = countable;
        this.priceForOne = priceForOne;
    }

    public int getId() {
        return id;
    }
    public int getCodeProduct() {
        return codeProduct;
    }
    public String getName() {
        return name;
    }
    public boolean isCountable() {
        return countable;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setCodeProduct(int codeProduct) {
        this.codeProduct = codeProduct;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCountable(boolean countable) {
        this.countable = countable;
    }
    public BigDecimal getPriceForOne() {
        return priceForOne;
    }
    public void setPriceForOne(BigDecimal priceForOne) {
        this.priceForOne = priceForOne;
    }
}
