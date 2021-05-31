package com.chen.gym.controller.field;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CHEN
 * @date 2021/5/26
 */
@Api(tags = "场地管理接口")
@RequestMapping("/field/")
@RestController
@CrossOrigin
public class FieldController {
    private static final Logger PLOG = LoggerFactory.getLogger(FieldController.class);
}
