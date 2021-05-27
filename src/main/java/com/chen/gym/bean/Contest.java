package com.chen.gym.bean;

import java.util.Date;

/**
 * @author CHEN
 * @date 2021/5/26
 * 赛事实体类
 */
public class Contest {
    private Long cid;
    private String name;
    private String organizers;
    private Date date;
    private String object;
    private String tele;
    private Long place;
    private Long equipment;

    @Override
    public String toString() {
        return "Contest{" +
                "cId=" + cid +
                ", name='" + name + '\'' +
                ", organizers='" + organizers + '\'' +
                ", date=" + date +
                ", object='" + object + '\'' +
                ", tele='" + tele + '\'' +
                ", place=" + place +
                ", equipment=" + equipment +
                '}';
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganizers() {
        return organizers;
    }

    public void setOrganizers(String organizers) {
        this.organizers = organizers;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public Long getPlace() {
        return place;
    }

    public void setPlace(Long place) {
        this.place = place;
    }

    public Long getEquipment() {
        return equipment;
    }

    public void setEquipment(Long equipment) {
        this.equipment = equipment;
    }
}
