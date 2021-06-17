package com.chen.gym.service.user.impl;


import com.chen.gym.bean.Role;
import com.chen.gym.bean.User;
import com.chen.gym.dao.user.RoleDao;
import com.chen.gym.dao.user.UserDao;
import com.chen.gym.exception.CustomizeRuntimeException;
import com.chen.gym.exception.MyCustomizeErrorCode;
import com.chen.gym.security.JWTToken;
import com.chen.gym.security.JWTUtil;
import com.chen.gym.service.user.UserService;
import com.chen.gym.util.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: CHEN
 * @date: 2020-12-10 21:01
 **/
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger PLOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    UserDao userDao;

    @Resource
    RoleDao roleDao;


    @Override
    public Map<String, Object> login(String username, String password) {
        String passwords = MD5Util.getHexPassword(password);
        JWTToken token = new JWTToken(JWTUtil.sign(username, passwords));
        Subject subject = SecurityUtils.getSubject();
        //登录认证
        subject.login(token);

        User user = userDao.findUserByUsername(username);
        List<String> roles = userDao.getUserRole(user.getUserId());
        Map<String, Object> map = new HashMap<>();
        map.put("token", token.getPrincipal());
        map.put("roles", roles);
        return map;
    }

    @Override
    public void register(User user,String passwordCheck) {
        if(user.getPassword().equals(passwordCheck))add(user);
        else {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.CHECK_PASSWORD_NO);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> list=userDao.findAllUser();

        return userDao.findAllUser();
    }

    @Override
    public List<Role> findAllRole() {
        return roleDao.findAllRole();
    }

    @Override
    public List<User> select(User user) {
        StringBuilder sql = new StringBuilder("SELECT * FROM user where 1=1 ");
        try {
            //通过反射遍历对象中的属性
            Class cls = user.getClass();
            Field[] fields = cls.getDeclaredFields();
            for (Field f : fields) {
                //设置属性可访问，破坏私有
                f.setAccessible(true);
                if (f.get(user) != null) {
//                    System.out.println("属性名:" + f.getName() + " 属性值:" + f.get(contest));
                    if (f.getName().equals("birthday")) {
//                       时间要具体处理。。
                    } else {
                        sql.append(" and ").append(f.getName()).append(" like '%").append(f.get(user)).append("%'");
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        PLOG.info("Select >> "+sql.toString());
        return userDao.select(sql.toString());
    }

    @Override
    public User findUserById(Long id) {
        User user = userDao.findUserById(id);
        if (user == null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_USER);
        }
        return user;
    }

    @Override
    public void add(User user) {
        //加密
        String passwords = MD5Util.getHexPassword(user.getPassword());
       user.setPassword(passwords);

        userDao.add(user);
        Long uid=user.getUserId();
        //默认普通用户
         roleDao.addRoleRelative(uid,2L);
    }

    @Override
    public void delete(Long id) {
        User user = userDao.findUserById(id);
        if (user == null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_USER);
        }
        userDao.delete(id);
    }

    @Override
    public void update(User user) {
        User item = userDao.findUserById(user.getUserId());
        if (item == null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_USER);
        }
        //加密
        String passwords = MD5Util.getHexPassword(user.getPassword());
        //更新
        item.setPassword(passwords);
        item.setAddress(user.getAddress());
        item.setBirthday(user.getBirthday());
        item.setIdCard(user.getIdCard());
        item.setMail(user.getMail());
        item.setName(user.getName());
        item.setTele(user.getTele());
        item.setSno(user.getSno());
        userDao.update(item);
    }

    @Override
    public void setRole(Long uid, Long rid) {
        User item = userDao.findUserById(uid);
        if (item == null) {
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_USER);
        }
        roleDao.updateRoleRelative(uid,rid);
    }
}
