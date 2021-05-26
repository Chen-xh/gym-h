package com.chen.gym.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;


import java.io.Serializable;

/**
 * @author: CHEN
 * @date: 2020-12-09 09:30
 * 用户账号
 **/
public class User implements Serializable {

    private Long userId;

    private String username;

    @JsonIgnore
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
