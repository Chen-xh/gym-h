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
    private static final Logger PLOG = LoggerFactory.getLogger(EquipmentController.class);

    @Resource
    EquipmentService equipmentService;

    @Resource
    EquipmentRentInfoService equipmentRentInfoService;


    @ApiOperation(value = "条件查询")
    @PostMapping("select")
    public JsonResult selectEquipment(Equipment equipment) {
        List<Equipment> list = equipmentService.select(equipment);
        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "根据id查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "器材id", required = true, paramType = "query", dataType = "String"),
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

    @ApiOperation(value = "添加")
    @PostMapping("add")
    public JsonResult add(Equipment equipment) {
        equipmentService.add(equipment);

        return JsonResult.success();

    }

    @ApiOperation(value = "删除")
    @DeleteMapping("delete")
    public JsonResult delete(Long id) {
        equipmentService.delete(id);

        return JsonResult.success();

    }

    @ApiOperation(value = "修改")
    @PostMapping("update")
    public JsonResult update(Equipment equipment) {
        equipmentService.update(equipment);

        return JsonResult.success();

    }

    @ApiOperation(value = "获取所有器材类型")
    @PostMapping("getAllKind")
    public JsonResult getAllKind() {
        equipmentService.getAllKind();
        return JsonResult.success();
    }
}
