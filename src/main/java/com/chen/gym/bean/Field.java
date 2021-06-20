package com.chen.gym.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Field {
    private Long ID;
    /**
     * 场地名称
     */
    private String siteName;
    /**
     * 场地标准
     */
    private double siteCost;
    /**
     * 具体地点
     */
    private String place;
    /**
     * 添加人
     */
    private Long userId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    /**
     * 编辑时间
     */
    private Date editTime;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public double getSiteCost() {
        return siteCost;
    }

    public void setSiteCost(double siteCost) {
        this.siteCost = siteCost;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    @Override
    public String toString() {
        return "Field{" +
                "ID=" + ID +
                ", siteName='" + siteName + '\'' +
                ", siteCost=" + siteCost +
                ", place='" + place + '\'' +
                ", userId=" + userId +
                ", editTime=" + editTime +
                '}';
    }
}
