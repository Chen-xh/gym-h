package com.chen.gym.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class FieldUseInfo {
    private Long id;
    // 使用的场地名称
    private String siteName;
    // 使用的场地id
    private Long fid;
    // 借用者账号
    private String sno;
    // 借用用途
    private String whyToUse;
    /**
     * 0:待审核
     * 1:审核通过、该时间段被租用
     * 2:拒绝：已有其他同学提前申请导致该时间段场地已被使用
     * 3:该时间段上课使用
     * 4:已回收
     * 5:超时回收
     */
    private int target;
    // 总使用时间段
    private int borrowTime;
    // 开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd-HH:mm:ss",timezone="GMT+8")
    private Date startTime;
    // 结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd-HH:mm:ss",timezone="GMT+8")
    private Date endTime;
    // 该记录编辑时间
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd-HH:mm:ss",timezone="GMT+8")
    private Date editTime;
    // 此次使用总金额
    private double totalMoney;

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

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getWhyToUse() {
        return whyToUse;
    }

    public void setWhyToUse(String whyToUse) {
        this.whyToUse = whyToUse;
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

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    @Override
    public String toString() {
        return "FieldUseInfo{" +
                "id=" + id +
                ", siteName='" + siteName + '\'' +
                ", fid=" + fid +
                ", sno='" + sno + '\'' +
                ", whyToUse='" + whyToUse + '\'' +
                ", target=" + target +
                ", borrowTime=" + borrowTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", editTime=" + editTime +
                ", totalMoney=" + totalMoney +
                '}';
    }
}
