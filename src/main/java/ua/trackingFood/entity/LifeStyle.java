package ua.trackingFood.entity;

import java.math.BigDecimal;

public class LifeStyle {
    private  int id;
    private String lifeStyle;
    private BigDecimal index;

    public LifeStyle() {
    }

    public LifeStyle(int id, String lifeStyle, BigDecimal index) {
        this.id = id;
        this.lifeStyle = lifeStyle;
        this.index = index;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLifeStyle() {
        return lifeStyle;
    }

    public void setLifeStyle(String lifeStyle) {
        this.lifeStyle = lifeStyle;
    }

    public BigDecimal getIndex() {
        return index;
    }

    public void setIndex(BigDecimal index) {
        this.index = index;
    }
}
