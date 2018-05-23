package ua.trackingFood.entity;

import java.math.BigDecimal;
import java.sql.Date;

public class EatenProducts {
    private int id;
    private int userId;
    private int productId;
    private String name;
    private BigDecimal weight;
    private BigDecimal energyValue;
    private BigDecimal proteins;
    private BigDecimal carbohydrates;
    private BigDecimal fats;
    private Date date;

    public EatenProducts() {
    }

    public EatenProducts(int id, int userId, int productId, BigDecimal weight, BigDecimal energyValue,
                         BigDecimal proteins, BigDecimal carbohydrates, BigDecimal fats, Date date) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.weight = weight;
        this.energyValue = energyValue;
        this.proteins = proteins;
        this.carbohydrates = carbohydrates;
        this.fats = fats;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getEnergyValue() {
        return energyValue;
    }

    public void setEnergyValue(BigDecimal energyValue) {
        this.energyValue = energyValue;
    }

    public BigDecimal getProteins() {
        return proteins;
    }

    public void setProteins(BigDecimal proteins) {
        this.proteins = proteins;
    }

    public BigDecimal getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(BigDecimal carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public BigDecimal getFats() {
        return fats;
    }

    public void setFats(BigDecimal fats) {
        this.fats = fats;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
