package com.chen.gym.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Field {
    private Long id;
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
    private String sno;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    /**
     * 编辑时间
     */
    private Date editTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    @Override
    public String toString() {
        return "Field{" +
                "id=" + id +
                ", siteName='" + siteName + '\'' +
                ", siteCost=" + siteCost +
                ", place='" + place + '\'' +
                ", sno='" + sno + '\'' +
                ", editTime=" + editTime +
                '}';
    }
}
