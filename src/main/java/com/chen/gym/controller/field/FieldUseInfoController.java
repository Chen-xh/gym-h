package com.chen.gym.controller.field;

import com.chen.gym.bean.Field;
import com.chen.gym.bean.FieldUseInfo;
import com.chen.gym.service.field.FieldUseInfoService;
import com.chen.gym.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "场地使用信息管理接口")
@RequestMapping("/fieldUse/")
@RestController
@CrossOrigin
public class FieldUseInfoController {
    private static final Logger PLOG = LoggerFactory.getLogger(FieldUseInfoController.class);

    FieldUseInfoService fieldUseInfoService;

    @ApiOperation(value = "条件查询场地使用记录")
    @PostMapping("select")
    public JsonResult selectField(FieldUseInfo fieldUseInfo) {
        List<FieldUseInfo> list = fieldUseInfoService.select(fieldUseInfo);
        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "根据id查询场地使用记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "场地使用记录id", required = true, paramType = "query", dataType = "String"),
    })
    @GetMapping("findFieldUseInfoByID")
    public JsonResult findFieldUseInfoByID(Long id) {
        FieldUseInfo field = fieldUseInfoService.findFieldUseInfoByID(id);

        return JsonResult.success()
                .addObject("FieldUseInfo", field);
    }


    @ApiOperation(value = "查询所有场地使用记录")
    @GetMapping("findAllField")
    public JsonResult findAllField() {
        List<FieldUseInfo> list = fieldUseInfoService.findAll();

        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "查询所有租出中场地使用记录")
    @GetMapping("findAllRenting")
    public JsonResult findAllRenting() {
        List<FieldUseInfo> list = fieldUseInfoService.findAllRenting();

        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "查询所有上课使用场地使用记录")
    @GetMapping("findAllInClass")
    public JsonResult findAllInClass() {
        List<FieldUseInfo> list = fieldUseInfoService.findAllInClass();

        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "查询所有回收场地使用记录")
    @GetMapping("findAllRecover")
    public JsonResult findAllRecover() {
        List<FieldUseInfo> list = fieldUseInfoService.findAllRecover();

        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "添加场地使用记录")
    @PostMapping("add")
    public JsonResult add(FieldUseInfo fieldUseInfo) {
        fieldUseInfoService.add(fieldUseInfo);

        return JsonResult.success();

    }

    @ApiOperation(value = "删除场地使用记录")
    @DeleteMapping("delete")
    public JsonResult delete(Long id) {
        fieldUseInfoService.delete(id);

        return JsonResult.success();

    }

    @ApiOperation(value = "修改场地使用记录")
    @PostMapping("update")
    public JsonResult update(FieldUseInfo field) {
        fieldUseInfoService.update(field);

        return JsonResult.success();

    }

    @ApiOperation(value = "回收场地")
    @DeleteMapping("recover")
    public JsonResult recover(Long id) {
        fieldUseInfoService.recover(id);

        return JsonResult.success();

    }
}
