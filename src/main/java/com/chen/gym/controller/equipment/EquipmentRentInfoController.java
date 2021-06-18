package com.chen.gym.controller.equipment;

import com.chen.gym.bean.EquipmentRentInfo;
import com.chen.gym.service.equipment.EquipmentRentInfoService;
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
@Api(tags = "器材租用信息管理接口")
@RequestMapping("/equipmentRentInfo/")
@RestController
@CrossOrigin
public class EquipmentRentInfoController {
    private static final Logger PLOG = LoggerFactory.getLogger(EquipmentRentInfoController.class);
    @Resource
    EquipmentRentInfoService equipmentRentInfoService;


    @ApiOperation(value = "条件查询")
    @PostMapping("select")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "器材租用记录标识ID", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "toolName", value = "器材名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "rentNum", value = "租出数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "classNum", value = "使用时间段数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "username", value = "借用者", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "target", value = "租用记录状态：1、租出；2、已回收；3、已超时；4、存在损坏或遗失", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "backNum", value = "归还数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "backTime", value = "归还时间", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "brokenNum", value = "损坏或遗失数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "totalMoney", value = "总租用金额", required = false, paramType = "query", dataType = "double"),
    })
    public JsonResult selectRentInfo(EquipmentRentInfo equipment) {
        List<EquipmentRentInfo> list = equipmentRentInfoService.select(equipment);
        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "根据id查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "器材租用记录标识id", required = true, paramType = "query", dataType = "String"),
    })
    @GetMapping("findEquipmentById")
    public JsonResult findRentInfoById(Long id) {
        EquipmentRentInfo equipment = equipmentRentInfoService.findRentInfoById(id);

        return JsonResult.success()
                .addObject("EquipmentRentInfo", equipment);

    }

    @ApiOperation(value = "查询所有")
    @GetMapping("findAll")
    public JsonResult findAll() {
        List<EquipmentRentInfo> list = equipmentRentInfoService.findAll();

        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "查询所有已回收")
    @GetMapping("findAllRecovering")
    public JsonResult findAllRecovering() {
        List<EquipmentRentInfo> list = equipmentRentInfoService.findAllRecovering();

        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "查询所有出租中")
    @GetMapping("findAllRenting")
    public JsonResult findAllRenting() {
        List<EquipmentRentInfo> list = equipmentRentInfoService.findAllRenting();

        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "添加")
    @PostMapping("add")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "器材租用记录标识ID", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "toolName", value = "器材名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "rentNum", value = "租出数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = true, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "classNum", value = "使用时间段数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "username", value = "借用者", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "target", value = "租用记录状态：1、租出；2、已回收；3、已超时；4、存在损坏或遗失", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "backNum", value = "归还数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "backTime", value = "归还时间", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "brokenNum", value = "损坏或遗失数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "totalMoney", value = "总租用金额", required = true, paramType = "query", dataType = "double"),
    })
    public JsonResult add(EquipmentRentInfo equipmentRentInfo) {
        equipmentRentInfoService.add(equipmentRentInfo);

        return JsonResult.success();

    }

    @ApiOperation(value = "删除")
    @DeleteMapping("delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "器材租用记录标识id", required = true, paramType = "query", dataType = "String"),
    })
    public JsonResult delete(Long id) {
        equipmentRentInfoService.delete(id);

        return JsonResult.success();

    }

    @ApiOperation(value = "修改")
    @PostMapping("recover")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "器材租用记录标识ID", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "toolName", value = "器材名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "rentNum", value = "租出数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "classNum", value = "使用时间段数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "username", value = "借用者", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "target", value = "租用记录状态：1、租出；2、已回收；3、已超时；4、存在损坏或遗失", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "backNum", value = "归还数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "backTime", value = "归还时间", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "brokenNum", value = "损坏或遗失数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "totalMoney", value = "总租用金额", required = false, paramType = "query", dataType = "double"),
    })
    public JsonResult recover(EquipmentRentInfo equipmentRentInfo) {
        equipmentRentInfoService.recover(equipmentRentInfo);

        return JsonResult.success();

    }
}
