package ua.trackingFood.entity;

import java.math.BigDecimal;

public class Product {
    private int id;
    private int categoryId;
    private String name;
    private String categoryName;
    private BigDecimal energyValue;
    private BigDecimal proteins;
    private BigDecimal carbohydrates;
    private BigDecimal fats;

    public Product() {
    }

    public Product(int id, int categoryId, String name, BigDecimal energyValue, BigDecimal proteins, BigDecimal carbohydrates, BigDecimal fats) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.energyValue = energyValue;
        this.proteins = proteins;
        this.carbohydrates = carbohydrates;
        this.fats = fats;
    }
    public Product(int categoryId, String name, BigDecimal energyValue, BigDecimal proteins, BigDecimal carbohydrates, BigDecimal fats) {
        this.categoryId = categoryId;
        this.name = name;
        this.energyValue = energyValue;
        this.proteins = proteins;
        this.carbohydrates = carbohydrates;
        this.fats = fats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
}