package com.chen.gym.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Equipment {
    private Long id;
    private String equipmentName;
    private int allNum;
    private int damageNum;
    private int rentNum;
    private int totalNum;
    private double rendStandard;
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd-HH:mm:ss",timezone="GMT+8")
    private Date editTime;
    private String sno;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
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

    public String getSno() {
        return sno;
    }

    public int getRentNum() {
        return rentNum;
    }

    public void setRentNum(int rentNum) {
        this.rentNum = rentNum;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    @Override
    public String toString() {
        return "Tool{" +
                "id=" + id +
                ", equipmentName='" + equipmentName + '\'' +
                ", allNum=" + allNum +
                ", damageNum=" + damageNum +
                ", rentNum=" + rentNum +
                ", totalNum=" + totalNum +
                ", rendStandard=" + rendStandard +
                ", editTime=" + editTime +
                ", sno=" + sno +
                '}';
    }
}
