package com.chen.gym.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class EquipmentRentInfo {
    private Long id;
    private String toolName;
    private int rentNum;
    @DateTimeFormat(pattern = "yyyy-MM-dd-hh-mm")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd-hh-mm")
    private Date endTime;
    /**
     * 借用课时数
     */
    private int classNum;
    /**
     * 预约人学号或教工号（外键 预约人账号）
     */
    private String username;
    /**
     * 1:租出中
     * 2:已回收
     * 3:已超时
     * 4:存在损坏或遗失
     */
    private int target;
    /**
     * 归还数（初始为0 回收时赋值）
      */
    private int backNum;
    /**
     * 归还时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd-hh-mm")
    private Date backTime;
    /**
     * 损坏或遗失数（初始为0 回收时赋值）
     */
    private int brokenNum;
    private double totalMoney;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public int getRentNum() {
        return rentNum;
    }

    public void setRentNum(int rentNum) {
        this.rentNum = rentNum;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getClassNum() {
        return classNum;
    }

    public void setClassNum(int classNum) {
        this.classNum = classNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getBackNum() {
        return backNum;
    }

    public void setBackNum(int backNum) {
        this.backNum = backNum;
    }

    public int getBrokenNum() {
        return brokenNum;
    }

    public void setBrokenNum(int brokenNum) {
        this.brokenNum = brokenNum;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Date getBackTime() {
        return backTime;
    }

    public void setBackTime(Date backTime) {
        this.backTime = backTime;
    }

    @Override
    public String toString() {
        return "EquipmentRentInfo{" +
                "id=" + id +
                ", toolName='" + toolName + '\'' +
                ", rentNum=" + rentNum +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", classNum=" + classNum +
                ", username='" + username + '\'' +
                ", target=" + target +
                ", backNum=" + backNum +
                ", backTime=" + backTime +
                ", brokenNum=" + brokenNum +
                ", totalMoney=" + totalMoney +
                '}';
    }
}
