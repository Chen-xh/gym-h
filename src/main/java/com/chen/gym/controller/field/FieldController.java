package com.chen.gym.controller.field;

import com.chen.gym.bean.Equipment;
import com.chen.gym.bean.Field;
import com.chen.gym.bean.FieldUseInfo;
import com.chen.gym.service.field.FieldService;
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
@Api(tags = "场地管理接口")
@RequestMapping("/field/")
@RestController
@CrossOrigin
public class FieldController {
    private static final Logger PLOG = LoggerFactory.getLogger(FieldController.class);

    @Resource
    FieldService fieldService;

    @ApiOperation(value = "条件查询场地")
    @PostMapping("select")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "场地标识id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "siteName", value = "场地名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "siteCost", value = "收费标准", required = false, paramType = "query", dataType = "double"),
            @ApiImplicitParam(name = "place", value = "场地具体地址", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sno", value = "编辑人账号", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "editTime", value = "编辑时间", required = false, paramType = "query", dataType = "Date"),
    })
    public JsonResult selectField(Field field) {
        List<Field> list = fieldService.select(field);
        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "条件查询场地")
    @PostMapping("findAllCanUse")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "开始时间(YYYY-MM-dd-HH:00:00)", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "endTime", value = "结束时间(YYYY-MM-dd-HH:00:00)", required = false, paramType = "query", dataType = "Date"),
    })
    public JsonResult findAllCanUse(FieldUseInfo fieldUseInfo) {
        List<Field> list = fieldService.findAllCanUse(fieldUseInfo);
        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "根据id查询场地")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "场地id", required = true, paramType = "query", dataType = "String"),
    })
    @GetMapping("findFieldById")
    public JsonResult findFieldById(Long id) {
        Field field = fieldService.findFieldByID(id);

        return JsonResult.success()
                .addObject("Field", field);

    }


    @ApiOperation(value = "查询所有场地")
    @GetMapping("findAllField")
    public JsonResult findAllField() {
        List<Field> list = fieldService.findAll();

        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "添加场地")
    @PostMapping("addField")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "场地标识id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "siteName", value = "场地名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "siteCost", value = "收费标准", required = true, paramType = "query", dataType = "double"),
            @ApiImplicitParam(name = "place", value = "场地具体地址", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sno", value = "编辑人账号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "editTime", value = "编辑时间", required = false, paramType = "query", dataType = "Date"),
    })
    public JsonResult addField(Field field) {
        fieldService.addField(field);

        return JsonResult.success();

    }

    @ApiOperation(value = "删除场地")
    @DeleteMapping("deleteField")
    public JsonResult deleteField(Long id) {
        fieldService.deleteField(id);

        return JsonResult.success();

    }

    @ApiOperation(value = "修改场地")
    @PostMapping("update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "场地标识id", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "siteName", value = "场地名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "siteCost", value = "收费标准", required = false, paramType = "query", dataType = "double"),
            @ApiImplicitParam(name = "place", value = "场地具体地址", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sno", value = "编辑人账号", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "editTime", value = "编辑时间", required = false, paramType = "query", dataType = "Date"),
    })
    public JsonResult update(Field field) {
        fieldService.updateField(field);

        return JsonResult.success();

    }
}
