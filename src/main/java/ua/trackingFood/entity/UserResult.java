package ua.trackingFood.entity;

import java.math.BigDecimal;

public class UserResult {
    private int id;
    private int userId;
    private BigDecimal levelMetabolism;
    private BigDecimal normaCalories;
    private BigDecimal expectedNormCalories;
    private BigDecimal proteins;
    private BigDecimal carbohydrates;
    private BigDecimal fats;

    public UserResult() {
    }

    public UserResult(int userId, BigDecimal levelMetabolism, BigDecimal normaCalories,
                      BigDecimal expectedNormCalories, BigDecimal proteins, BigDecimal carbohydrates, BigDecimal fats) {
        this.userId = userId;
        this.levelMetabolism = levelMetabolism;
        this.normaCalories = normaCalories;
        this.expectedNormCalories = expectedNormCalories;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getLevelMetabolism() {
        return levelMetabolism;
    }

    public void setLevelMetabolism(BigDecimal levelMetabolism) {
        this.levelMetabolism = levelMetabolism;
    }

    public BigDecimal getNormaCalories() {
        return normaCalories;
    }

    public void setNormaCalories(BigDecimal normaCalories) {
        this.normaCalories = normaCalories;
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

    public BigDecimal getExpectedNormCalories() {
        return expectedNormCalories;
    }

    public void setExpectedNormCalories(BigDecimal expectedNormCalories) {
        this.expectedNormCalories = expectedNormCalories;
    }
}
