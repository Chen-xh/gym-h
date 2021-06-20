package com.chen.gym.controller.equipment;

import com.chen.gym.bean.Equipment;
import com.chen.gym.service.equipment.EquipmentRentInfoService;
import com.chen.gym.service.equipment.EquipmentService;
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
 * @author Jinbert
 * @date 2021/6/13
 */
@Api(tags = "器材管理接口")
@RequestMapping("/equipment/")
@RestController
@CrossOrigin
public class EquipmentController {
    @Resource
    EquipmentService equipmentService;

    @ApiOperation(value = "条件查询")
    @PostMapping("select")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "器材标识id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "equipmentName", value = "器材名称（器材类型）", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "allNum", value = "所有数量", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "damageNum", value = "损坏或遗失数量", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rentNum", value = "租出数量", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "totalNum", value = "剩余数量", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rendStandard", value = "器材收费标准", required = false, paramType = "query", dataType = "double"),
            @ApiImplicitParam(name = "editTime", value = "器材编辑时间(YYYY-MM-dd-HH:mm:ss)", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "sno", value = "管理员账号", required = false, paramType = "query", dataType = "String"),
    })
    public JsonResult selectEquipment(Equipment equipment) {
        List<Equipment> list = equipmentService.select(equipment);

        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "根据id查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "器材标识id", required = true, paramType = "query", dataType = "String"),
    })
    @GetMapping("findEquipmentById")
    public JsonResult findEquipmentById(Long id) {
        Equipment equipment = equipmentService.findEquipmentById(id);

        return JsonResult.success()
                .addObject("Equipment", equipment);

    }

    @ApiOperation(value = "查询所有")
    @GetMapping("findAll")
    public JsonResult findAll() {
        List<Equipment> list = equipmentService.findAll();

        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "查询所有可租借器材")
    @GetMapping("findALlCanRent")
    public JsonResult findALlCanRent() {
        List<Equipment> list = equipmentService.findALlCanRent();

        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "添加")
    @PostMapping("add")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "器材标识id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "equipmentName", value = "器材名称（器材类型）", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "allNum", value = "所有数量", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "damageNum", value = "损坏或遗失数量", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rentNum", value = "租出数量", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "totalNum", value = "剩余数量", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rendStandard", value = "器材收费标准", required = true, paramType = "query", dataType = "double"),
            @ApiImplicitParam(name = "editTime", value = "器材编辑时间(YYYY-MM-dd-HH:mm:ss)", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "sno", value = "管理员账号", required = true, paramType = "query", dataType = "String"),
    })
    public JsonResult add(Equipment equipment) {
        equipmentService.add(equipment);

        return JsonResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "器材标识id", required = true, paramType = "query", dataType = "String"),
    })
    public JsonResult delete(Long id) {
        equipmentService.delete(id);

        return JsonResult.success();
    }

    @ApiOperation(value = "修改")
    @PostMapping("update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "器材标识id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "equipmentName", value = "器材名称（器材类型）", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "allNum", value = "所有数量", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "damageNum", value = "损坏或遗失数量", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rentNum", value = "租出数量", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "totalNum", value = "剩余数量", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rendStandard", value = "器材收费标准", required = false, paramType = "query", dataType = "double"),
            @ApiImplicitParam(name = "editTime", value = "器材编辑时间(YYYY-MM-dd-HH:mm:ss)", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "sno", value = "管理员账号", required = false, paramType = "query", dataType = "String"),
    })
    public JsonResult update(Equipment equipment) {
        equipmentService.update(equipment);

        return JsonResult.success();
    }

    @ApiOperation(value = "获取所有器材名称（器材类型）")
    @PostMapping("getAllKind")
    public JsonResult getAllKind() {
        List list = equipmentService.getAllKind();
        return JsonResult.success().addObject("list", list);
    }
}
