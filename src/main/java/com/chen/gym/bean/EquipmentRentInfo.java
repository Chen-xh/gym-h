package com.chen.gym.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class EquipmentRentInfo {
    private Long id;
    private int rentNum;
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm:ss")
    private Date endTime;
    // 用户提出申请时间
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm:ss")
    private Date requireTime;
    /**
     * 借用课时数
     */
    private int classNum;
    /**
     * 预约人学号或教工号（外键 预约人账号）
     */
    private String sno;
    /**
     * 0:待审核
     * 1:审核通过
     * 2:拒绝：已有其他同学提前申请导致余数不足
     * 3:租出中
     * 4:已回收
     * 5:已超时
     * 6:超时回收
     */
    private int target;
    /**
     * 归还数（初始为0 回收时赋值）
      */
    private int backNum;
    /**
     * 管理员审批/编辑时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date editTime;
    /**
     * 损坏或遗失数（初始为0 回收时赋值）
     */
    private int brokenNum;
    private double totalMoney;


    //  租用的器材ID
    private Long eid;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getRequireTime() {
        return requireTime;
    }

    public void setRequireTime(Date requireTime) {
        this.requireTime = requireTime;
    }

    public int getClassNum() {
        return classNum;
    }

    public void setClassNum(int classNum) {
        this.classNum = classNum;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
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

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
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

    public Long getEid() {
        return eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    @Override
    public String toString() {
        return "EquipmentRentInfo{" +
                "id=" + id +
                ", rentNum=" + rentNum +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", requireTime=" + requireTime +
                ", classNum=" + classNum +
                ", sno='" + sno + '\'' +
                ", target=" + target +
                ", backNum=" + backNum +
                ", editTime=" + editTime +
                ", brokenNum=" + brokenNum +
                ", totalMoney=" + totalMoney +
                ", eid=" + eid +
                '}';
    }
}
