package com.chen.gym.service.user.impl;


import com.chen.gym.bean.User;
import com.chen.gym.dao.user.UserDao;
import com.chen.gym.security.CustomRealm;
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
}
