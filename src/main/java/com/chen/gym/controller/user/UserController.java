package com.chen.gym.controller.user;

import com.chen.gym.bean.Contest;
import com.chen.gym.bean.Role;
import com.chen.gym.bean.User;
import com.chen.gym.security.CustomRealm;
import com.chen.gym.service.contest.ContestService;
import com.chen.gym.service.user.UserService;
import com.chen.gym.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CHEN
 * @date 2021/5/26
 */
@Api(tags = "用户管理接口")
@RequestMapping("/user/")
@RestController
@CrossOrigin
public class UserController {
    private static final Logger PLOG = LoggerFactory.getLogger(UserController.class);

    @Resource
    UserService userService;


    @ApiOperation(value = "条件查询",notes = "默认查全部，条可以为空，有值就模糊查询")
    @PostMapping("select")
    public JsonResult select(User user) {
        List<User> list = userService.select(user);
        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "根据id查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户id", required = true, paramType = "query", dataType = "String"),
    })
    @GetMapping("findById")
    public JsonResult findContestById(Long uid) {
        User user = userService.findUserById(uid);

        return JsonResult.success()
                .addObject("user", user);

    }


    @ApiOperation(value = "查询所有")
    @GetMapping("findAll")
    public JsonResult findAll() {
        List<User> list = userService.findAll();

        return JsonResult.success().addObject("list", list);

    }

    @ApiOperation(value = "查询所有管理员")
    @GetMapping("findAllManager")
    public JsonResult findAllManager() {
        List<User> list = userService.findAllManager();

        return JsonResult.success().addObject("list", list);

    }

    @ApiOperation(value = "添加")
    @PostMapping("admin/add")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sno",value = "学号（12位）", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "idCard", value = "身份证（18位）", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "birthday", value = "生日日期", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "tele", value = "联系电话", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "mail", value = "邮箱", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "address", value = "地址", required = true, paramType = "query", dataType = "String"),
    })
    public JsonResult add(User user) {
        userService.add(user);

        return JsonResult.success();

    }

    @ApiOperation(value = "删除")
    @DeleteMapping("admin/delete")
    public JsonResult delete(Long id) {
        userService.delete(id);

        return JsonResult.success();

    }

    @ApiOperation(value = "修改")
    @PostMapping("admin/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "sno",value = "学号（12位）", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "idCard", value = "身份证（18位）", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "birthday", value = "生日日期", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "tele", value = "联系电话", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "mail", value = "邮箱", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "address", value = "地址", required = true, paramType = "query", dataType = "String"),
    })
    public JsonResult update(User user) {
        userService.update(user);

        return JsonResult.success();

    }

    @ApiOperation(value = "查询所有角色")
    @GetMapping("findAllRole")
    public JsonResult findAllRole() {
        List<Role> list=userService.findAllRole();

        return JsonResult.success().addObject("list",list);

    }

    @ApiOperation(value = "修改用户角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "rid", value = "角色id", required = true, paramType = "query", dataType = "Long"),
    })
    @PostMapping("admin/changeRole")
    public JsonResult changeRole(Long uid,Long rid) {
        userService.setRole(uid,rid);

        return JsonResult.success();

    }
}
