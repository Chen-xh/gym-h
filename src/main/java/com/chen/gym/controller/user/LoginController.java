package com.chen.gym.controller.user;

import com.chen.gym.bean.User;
import com.chen.gym.security.JWTToken;
import com.chen.gym.security.JWTUtil;
import com.chen.gym.service.user.UserService;
import com.chen.gym.util.JsonResult;
import com.chen.gym.util.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
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
    @Resource
    UserService userService;


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

    @ApiOperation(value = "用户退出")
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
