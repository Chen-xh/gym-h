package com.chen.gym.exception;

/**
 * @author CHEN
 * @date 2020/10/13  12:07
 */
public enum MyCustomizeErrorCode implements CustomizeErrorCode {
    /**
     * 2001:没有找到用户!
     */
    NOT_FOND_USER(2001,"没有找到用户!"),
    /**
     * 2002:没有找到赛事!
     */
    NOT_FOND_Contest(2002,"没有找到赛事!"),

    /**
     * 3001:尚未登录!
     */
    NOT_LOGIN(3001,"尚未登录!"),
    /**
     * 3002:没有权限!
     */
    NOT_ALLOWED(3002,"没有权限!"),
    /**
     * 3003:密码错误!
     */
    PASS_NOT_CORRECT(3003,"密码错误!"),

    /**
     * 3007:文件大小超出限制！
     */
    FILE_MAX_SIZE_EXCEPTION(3007, "文件大小超出限制！"),
    /**
     * 500:服务端异常
     */
    INTERNAL_SERVER_ERROR(500, "服务器冒烟了...要不等它降降温后再来访问?");


    private Integer code;
    private String message;

    MyCustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
