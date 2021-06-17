package com.chen.gym.dao.user;

import com.chen.gym.bean.Role;
import com.chen.gym.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author: CHEN
 * @date: 2020-12-09 11:10
 **/
@Mapper
public interface RoleDao {

    @Insert("insert into user_role(uid,rid) " +
            "values(#{uid} , #{rid} )")
    void addRoleRelative(Long uid, Long rid);
    @Update("Update  `user_role` set rid=#{rid} where uid=#{uid} ")
    void updateRoleRelative(Long uid, Long rid);
    @Select("select * from role")
    List<Role> findAllRole();
}
