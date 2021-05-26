package com.chen.gym.util;

import com.chen.gym.exception.CustomizeErrorCode;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CHEN
 * @date 2020/10/13  12:13
 * 统一返回类型
 */
public class JsonResult implements Serializable {
    private Integer code;
    private String message;
    private Map<String, Object> extended = new HashMap<>();

    public static JsonResult success(){
        return new JsonResult(200, "处理成功");
    }

    public static JsonResult fail(){return new JsonResult(100, "处理失败!");}

    public static JsonResult errorOf(CustomizeErrorCode errorCode){
        return errorOf(errorCode.getCode(), errorCode.getMessage());
    }

    public static JsonResult errorOf(Integer code, String message) {

        return new JsonResult(code, message);
    }

    public  JsonResult addObject(String key, Object value){
        this.extended.put(key, value);
        return this;
    }

    public JsonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
