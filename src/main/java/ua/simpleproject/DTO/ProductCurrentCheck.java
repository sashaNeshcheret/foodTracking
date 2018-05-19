package ua.simpleproject.DTO;

import java.math.BigDecimal;

public class ProductCurrentCheck {

    private String nameProduct;
    private int codeProduct;
    private BigDecimal numberOfProduct;
    private BigDecimal priceForOne;
    private BigDecimal resultPrice;

    public ProductCurrentCheck() {
    }

    public ProductCurrentCheck(String nameProduct, int codeProduct, BigDecimal numberOfProduct, BigDecimal priceForOne, BigDecimal resultPrice) {
        this.nameProduct = nameProduct;
        this.codeProduct = codeProduct;
        this.numberOfProduct = numberOfProduct;
        this.priceForOne = priceForOne;
        this.resultPrice = resultPrice;
    }

    public String getNameProduct() {
        return nameProduct;
    }
    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }
    public int getCodeProduct() {
        return codeProduct;
    }
    public void setCodeProduct(int codeProduct) {
        this.codeProduct = codeProduct;
    }
    public BigDecimal getNumberOfProduct() {
        return numberOfProduct;
    }
    public void setNumberOfProduct(BigDecimal numberOfProduct) {
        this.numberOfProduct = numberOfProduct;
    }
    public BigDecimal getPriceForOne() {
        return priceForOne;
    }
    public void setPriceForOne(BigDecimal priceForOne) {
        this.priceForOne = priceForOne;
    }
    public BigDecimal getResultPrice() {
        return resultPrice;
    }
    public void setResultPrice(BigDecimal resultPrice) {
        this.resultPrice = resultPrice;
    }

}
