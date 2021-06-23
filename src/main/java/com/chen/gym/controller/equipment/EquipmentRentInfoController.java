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
            @ApiImplicitParam(name = "eid", value = "租用的器材ID", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "rentNum", value = "租出数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "startTime", value = "开始时间(YYYY-MM-dd-HH:00:00)", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "endTime", value = "结束时间(YYYY-MM-dd-HH:00:00)", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "requireTime", value = "用户提出申请时间(YYYY-MM-dd-HH:mm:ss)", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "classNum", value = "租出时间段数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "sno", value = "借用者学号或教工号", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "target", value = "租用记录状态：0、待审核；1、审核通过；2、拒绝：已有其他同学提前申请导致余数不足；3、租出中；4、已回收；5、已超时;6、超时回收", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "backNum", value = "归还数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "editTime", value = "管理员审批/编辑时间(YYYY-MM-dd-HH:mm:ss)", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "brokenNum", value = "损坏或遗失数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "totalMoney", value = "总租用金额", required = false, paramType = "query", dataType = "double"),
    })
    public JsonResult selectRentInfo(EquipmentRentInfo equipment) {
        List<EquipmentRentInfo> list = equipmentRentInfoService.select(equipment);
        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "根据借用者学号或教工号查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sno", value = "借用者学号或教工号", required = true, paramType = "query", dataType = "String"),
    })
    @GetMapping("findRentInfoBySno")
    public JsonResult findRentInfoBySno(String sno) {
        List<EquipmentRentInfo> list = equipmentRentInfoService.findRentInfoBySno(sno);

        return JsonResult.success()
                .addObject("list", list);

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

    @ApiOperation(value = "根据状态查询:0、待审核；1、审核通过；2、拒绝：已有其他同学提前申请导致余数不足；3、租出中；4、已回收；5、已超时;6、超时回收")
    @GetMapping("findByTarget")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "target", value = "状态标签", required = true, paramType = "query", dataType = "int"),
    })
    public JsonResult findByTarget(int target) {
        List<EquipmentRentInfo> list = equipmentRentInfoService.findRentInfoByTarget(target);

        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "查询所有")
    @GetMapping("findAll")
    public JsonResult findAll() {
        List<EquipmentRentInfo> list = equipmentRentInfoService.findAll();

        return JsonResult.success().addObject("list", list);
    }


    @ApiOperation(value = "查询所有待回收（target=1 or 3）")
    @GetMapping("findAllBeRecover")
    public JsonResult findAllBeRecover() {
        List<EquipmentRentInfo> list = equipmentRentInfoService.findAllBeRecover();

        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "查询所有已回收（target=4 or 6）")
    @GetMapping("findAllRecover")
    public JsonResult findAllRecover() {
        List<EquipmentRentInfo> list = equipmentRentInfoService.findAllRecover();

        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "查询所有待审核（target=0）")
    @GetMapping("findAllBePass")
    public JsonResult findAllBePass() {
        List<EquipmentRentInfo> list = equipmentRentInfoService.findRentInfoByTarget(0);

        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "查询所有已审核通过（target=1）")
    @GetMapping("findAllPass")
    public JsonResult findAllPass() {
        List<EquipmentRentInfo> list = equipmentRentInfoService.findRentInfoByTarget(1);

        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "查询所有超时记录（target=5）")
    @GetMapping("findAllOutTime")
    public JsonResult findAllOutTime() {
        List<EquipmentRentInfo> list = equipmentRentInfoService.findRentInfoByTarget(5);

        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "进行器材租用预约")
    @PostMapping("add")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "器材租用记录标识ID", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "eid", value = "租用的器材ID", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "rentNum", value = "租出数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "startTime", value = "开始时间(YYYY-MM-dd-HH:00:00)", required = true, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "endTime", value = "结束时间(YYYY-MM-dd-HH:00:00)", required = true, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "requireTime", value = "用户提出申请时间(YYYY-MM-dd-HH:mm:ss)", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "classNum", value = "租出时间段数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "sno", value = "借用者学号或教工号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "target", value = "租用记录状态：0、待审核；1、审核通过；2、拒绝：已有其他同学提前申请导致余数不足；3、租出中；4、已回收；5、已超时;6、超时回收", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "backNum", value = "归还数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "editTime", value = "管理员审批/编辑时间(YYYY-MM-dd-HH:mm:ss)", required = false, paramType = "query", dataType = "Date"),
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

    @ApiOperation(value = "通过审批")
    @PostMapping("pass")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "器材租用记录标识ID", required = true, paramType = "query", dataType = "String"),
    })
    public JsonResult pass(Long id) {

        equipmentRentInfoService.passRequire(id);

        return JsonResult.success();
    }

    @ApiOperation(value = "拒绝审批")
    @PostMapping("reject")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "器材租用记录标识ID", required = true, paramType = "query", dataType = "String"),
    })
    public JsonResult reject(Long id) {
        equipmentRentInfoService.updateTarget(2,id);

        return JsonResult.success();
    }

    @ApiOperation(value = "标记租出")
    @PostMapping("renting")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "器材租用记录标识ID", required = true, paramType = "query", dataType = "String"),
    })
    public JsonResult renting(Long id) {
        equipmentRentInfoService.updateTarget(3,id);

        return JsonResult.success();
    }

    @ApiOperation(value = "标记超时")
    @PostMapping("outTime")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "器材租用记录标识ID", required = true, paramType = "query", dataType = "String"),
    })
    public JsonResult outTime(Long id) {
        equipmentRentInfoService.updateTarget(5,id);

        return JsonResult.success();
    }



    @ApiOperation(value = "普通回收")
    @PostMapping("recover")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "器材租用记录标识ID", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "eid", value = "租用的器材ID", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "rentNum", value = "租出数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "startTime", value = "开始时间(YYYY-MM-dd-HH:00:00)", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "endTime", value = "结束时间(YYYY-MM-dd-HH:00:00)", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "requireTime", value = "用户提出申请时间(YYYY-MM-dd-HH:mm:ss)", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "classNum", value = "租出时间段数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "sno", value = "借用者学号或教工号", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "target", value = "租用记录状态：0、待审核；1、审核通过；2、拒绝：已有其他同学提前申请导致余数不足；3、租出中；4、已回收；5、已超时;6、超时回收", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "backNum", value = "归还数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "editTime", value = "管理员审批/编辑时间(YYYY-MM-dd-HH:mm:ss)", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "brokenNum", value = "损坏或遗失数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "totalMoney", value = "总租用金额", required = false, paramType = "query", dataType = "double"),
    })
    public JsonResult recover(EquipmentRentInfo equipmentRentInfo) {
        equipmentRentInfoService.recover(equipmentRentInfo, 4);

        return JsonResult.success();

    }

    @ApiOperation(value = "超时回收")
    @PostMapping("outTimeRecover")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "器材租用记录标识ID", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "eid", value = "租用的器材ID", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "rentNum", value = "租出数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "startTime", value = "开始时间(YYYY-MM-dd-HH:00:00)", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "endTime", value = "结束时间(YYYY-MM-dd-HH:00:00)", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "requireTime", value = "用户提出申请时间(YYYY-MM-dd-HH:mm:ss)", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "classNum", value = "租出时间段数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "sno", value = "借用者学号或教工号", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "target", value = "租用记录状态：0、待审核；1、审核通过；2、拒绝：已有其他同学提前申请导致余数不足；3、租出中；4、已回收；5、已超时;6、超时回收", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "backNum", value = "归还数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "editTime", value = "管理员审批/编辑时间(YYYY-MM-dd-HH:mm:ss)", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "brokenNum", value = "损坏或遗失数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "totalMoney", value = "总租用金额", required = false, paramType = "query", dataType = "double"),
    })
    public JsonResult outTimeRecover(EquipmentRentInfo equipmentRentInfo) {
        equipmentRentInfoService.recover(equipmentRentInfo, 6);

        return JsonResult.success();

    }
}
