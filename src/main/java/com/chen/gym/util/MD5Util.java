package com.chen.gym.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author CHEN
 * @date 2020/3/2 14:57
 * 密码加密
 */
public class MD5Util {

    private static String salt="gym";

    public static String getHexPassword(String password){
        return new Md5Hash(password, salt, 2).toString();
    }
}