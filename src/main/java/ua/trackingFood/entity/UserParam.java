/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package ua.trackingFood.entity;

import java.math.BigDecimal;

public class UserParam {
    private int id;
    private int userId;
    private String sex;
    private int lifeStyleId;
    private String lifeStyle;
    private BigDecimal index;
    private int age;
    private BigDecimal height;
    private BigDecimal weight;
    private String expectedResult;

    public UserParam() {
    }

    public UserParam(int userId, String sex, int lifeStyle_id, int age, BigDecimal height,
                     BigDecimal weight, String expectedResult) {
        this.userId = userId;
        this.sex = sex;
        this.lifeStyleId = lifeStyle_id;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.expectedResult = expectedResult;
    }

    public UserParam(int id, int userId, String sex, int lifeStyleId, String lifeStyle,
                     BigDecimal index, int age, BigDecimal height, BigDecimal weight, String expectedResult) {
        this.id = id;
        this.userId = userId;
        this.sex = sex;
        this.lifeStyleId = lifeStyleId;
        this.lifeStyle = lifeStyle;
        this.index = index;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.expectedResult = expectedResult;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getLifeStyleId() {
        return lifeStyleId;
    }

    public void setLifeStyleId(int lifeStyleId) {
        this.lifeStyleId = lifeStyleId;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }
}
