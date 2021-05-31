package com.chen.gym.controller.user;


import com.chen.gym.bean.User;
import com.chen.gym.service.user.UserService;
import com.chen.gym.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author CHEN
 * @date 2021/5/26
 * 登录
 */
@Api(tags = "登录接口")
@RestController
@CrossOrigin
public class LoginController {

    private static final Logger PLOG = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private  UserService userService;


    @ApiOperation(value = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query", dataType = "String")
    })
    @PostMapping("login")
    public JsonResult userLogin(String username, String password) {
        Map<String,Object> map= userService.login(username,password);

        return JsonResult.success()
                .addObject("token", map.get("token"))
                .addObject("roles",  map.get("roles"));

    }
    @ApiOperation(value = "用户注册",notes = "除了id都不为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "passwordCheck", value = "确认密码", required = true, paramType = "query", dataType = "String")
    })
    @PostMapping("register")
    public JsonResult userRegister(User user,String passwordCheck) {
        userService.register(user,passwordCheck);

        return JsonResult.success();

    }
    @ApiOperation(value = "用户退出",notes = "需要token")
    @GetMapping("logout")
    public JsonResult logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return JsonResult.success();
    }


    @GetMapping("/error/{message}")
    public JsonResult error(@PathVariable String message) {
        return JsonResult.errorOf(200, message);
    }
}
