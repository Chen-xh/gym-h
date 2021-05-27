package com.chen.gym.controller.user;

import com.chen.gym.security.CustomRealm;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CHEN
 * @date 2021/5/26
 */
@Api(tags = "用户管理接口")
@RestController
@CrossOrigin
public class UserController {
    private static final Logger PLOG = LoggerFactory.getLogger(UserController.class);

}
