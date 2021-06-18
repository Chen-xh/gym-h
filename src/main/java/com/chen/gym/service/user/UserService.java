package com.chen.gym.service.user;



import com.chen.gym.bean.Contest;
import com.chen.gym.bean.Role;
import com.chen.gym.bean.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    /**
     * 登录
     */
    Map<String,Object> login(String username, String password);
    /**
     * 注册
     */
    void register(User user,String passwordCheck);
    /**
     * 查询所有
     */
    List<User> findAll();
    /**
     * 查询管理员
     */
    List<User> findAllManager();
    /**
     * 查询所有角色
     */
    List<Role> findAllRole();
    /**
     * 多条件查询
     */
    List<User> select(User user);
    /**
     * 根据id查询
     */
    User findUserById(Long id);

    /**
     * 添加
     */
    void add(User user);
    /**
     * 删除
     */
    void delete(Long id);
    /**
     * 修改
     */
    void update(User user);
    /**
     * 修改权限
     */
    void setRole(Long uid, Long rid);

}
