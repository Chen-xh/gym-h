package com.chen.gym.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class FieldUseInfo {
    private Long id;
    // 使用的场地名称
    private String siteName;
    // 此次使用总金额
    private double Cost;
    // 借用者id
    private Long borrower;
    // 借用用途
    private String purpose;
    /**
     * 状态标签
     * 1、租用中
     * 2、上课中
     * 3、已回收
     */
    private int target;
    // 总使用时间段
    private int borrowTime;
    // 开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    // 结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

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

    public double getCost() {
        return Cost;
    }

    public void setCost(double cost) {
        Cost = cost;
    }

    public Long getBorrower() {
        return borrower;
    }

    public void setBorrower(Long borrower) {
        this.borrower = borrower;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(int borrowTime) {
        this.borrowTime = borrowTime;
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

    @Override
    public String toString() {
        return "FieldUseInfo{" +
                "id=" + id +
                ", siteName='" + siteName + '\'' +
                ", Cost=" + Cost +
                ", borrower=" + borrower +
                ", purpose='" + purpose + '\'' +
                ", target=" + target +
                ", borrowTime=" + borrowTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
