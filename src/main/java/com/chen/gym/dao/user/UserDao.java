package com.chen.gym.dao.user;

import com.chen.gym.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: CHEN
 * @date: 2020-12-09 11:10
 **/
@Mapper
public interface UserDao {

    @Select("select * from user")
    List<User> findAllUser();

    @Select("SELECT * FROM `user` WHERE id=#{id}")
    User findUserById(Long id);

    @Select("SELECT * FROM `user` WHERE username=#{username}")
    User findUserByUsername(String username);

    @Select("SELECT target from user_role u left outer join role r on(u.rid=r.rid) where u.uid=#{uid}")
    List<String> getUserRole(Long uid);


}
