package com.chen.gym.service.user;



import java.util.Map;

public interface UserService {

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    Map<String,Object> login(String username, String password);

}
