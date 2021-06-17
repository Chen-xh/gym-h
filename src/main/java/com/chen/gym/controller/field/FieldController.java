package com.chen.gym.controller.field;

import com.chen.gym.bean.Equipment;
import com.chen.gym.bean.Field;
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
    public JsonResult selectField(Field field) {
        List<Field> list = fieldService.select(field);
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

    @ApiOperation(value = "查询所有空闲场地")
    @GetMapping("findAllIdle")
    public JsonResult findAllIdle() {
        List<Field> list = fieldService.findAllIdle();

        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "查询所有上课使用场地")
    @GetMapping("findAllInClass")
    public JsonResult findAllInClass() {
        List<Field> list = fieldService.findAllInClass();

        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "查询所有租出场地")
    @GetMapping("findAllRenting")
    public JsonResult findAllRenting() {
        List<Field> list = fieldService.findAllRenting();

        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "添加场地")
    @PostMapping("addField")
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
    public JsonResult update(Field field) {
        fieldService.updateField(field);

        return JsonResult.success();

    }

    @ApiOperation(value = "设置场地为租借中")
    @PostMapping("setFieldRenting")
    public JsonResult setFieldRenting(Field field) {
        fieldService.setFieldRenting(field);

        return JsonResult.success();

    }

    @ApiOperation(value = "设置场地为空闲中")
    @PostMapping("setFieldIdle")
    public JsonResult setFieldIdle(Field field) {
        fieldService.setFieldIdle(field);

        return JsonResult.success();

    }

    @ApiOperation(value = "设置场地为上课中")
    @PostMapping("setFieldInClass")
    public JsonResult setFieldInClass(Field field) {
        fieldService.setFieldInClass(field);

        return JsonResult.success();

    }
}
