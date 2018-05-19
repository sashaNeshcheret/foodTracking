package ua.trackingFood.entity;

import java.math.BigDecimal;

public class Product {
    private int id;
    private int categoriaId;
    private String name;
    private BigDecimal energy_value;
    private BigDecimal proteins;
    private BigDecimal carbohydrates;
    private BigDecimal fats;

    public Product() {
    }

    public Product(int id, int categoriaId, String name, BigDecimal energy_value, BigDecimal proteins, BigDecimal carbohydrates, BigDecimal fats) {
        this.id = id;
        this.categoriaId = categoriaId;
        this.name = name;
        this.energy_value = energy_value;
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

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getEnergy_value() {
        return energy_value;
    }

    public void setEnergy_value(BigDecimal energy_value) {
        this.energy_value = energy_value;
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