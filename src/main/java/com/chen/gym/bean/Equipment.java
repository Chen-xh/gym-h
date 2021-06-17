package com.chen.gym.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Equipment {
    private Long id;
    private String toolKind;
    private int allNum;
    private int damageNum;
    private int rentNum;
    private int totalNum;
    private double rendStandard;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date editTime;
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToolKind() {
        return toolKind;
    }

    public void setToolKind(String toolKind) {
        this.toolKind = toolKind;
    }

    public int getAllNum() {
        return allNum;
    }

    public void setAllNum(int allNum) {
        this.allNum = allNum;
    }

    public int getDamageNum() {
        return damageNum;
    }

    public void setDamageNum(int damageNum) {
        this.damageNum = damageNum;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public double getRendStandard() {
        return rendStandard;
    }

    public void setRendStandard(double rendStandard) {
        this.rendStandard = rendStandard;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public Long getUserId() {
        return userId;
    }

    public int getRentNum() {
        return rentNum;
    }

    public void setRentNum(int rentNum) {
        this.rentNum = rentNum;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Tool{" +
                "id=" + id +
                ", toolKind='" + toolKind + '\'' +
                ", allNum=" + allNum +
                ", damageNum=" + damageNum +
                ", rentNum=" + rentNum +
                ", totalNum=" + totalNum +
                ", rendStandard=" + rendStandard +
                ", editTime=" + editTime +
                ", userId=" + userId +
                '}';
    }
}
