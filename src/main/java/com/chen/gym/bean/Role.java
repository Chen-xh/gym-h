package com.chen.gym.bean;


import java.io.Serializable;

/**
 * @author CHEN
 * @date 2020/10/12  19:07
 * 用户角色
 */
public class Role implements Serializable {

    private Long rid;

    private String roleName;

    private String target;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
