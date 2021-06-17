package com.chen.gym.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;


import java.io.Serializable;
import java.util.Date;

import static com.alibaba.druid.util.FnvHash.Constants.DATE_FORMAT;

/**
 * @author: CHEN
 * @date: 2020-12-09 09:30
 * 用户信息
 **/
public class User implements Serializable {

    private Long userId;

    //学号用作username
    private String sno;

    @JsonIgnore
    private String password;

    private String name;
    private String idCard;
    @DateTimeFormat(pattern = "yyyy-MM-dd" )
//    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+9")
    private Date birthday;
    private String tele;
    private String mail;
    private String address;

    private Long rid;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", sno='" + sno + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", idCard='" + idCard + '\'' +
                ", birthday=" + birthday +
                ", tele='" + tele + '\'' +
                ", mail='" + mail + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
