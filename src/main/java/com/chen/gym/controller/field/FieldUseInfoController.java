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

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "场地使用信息管理接口")
@RequestMapping("/fieldUse/")
@RestController
@CrossOrigin
public class FieldUseInfoController {
    private static final Logger PLOG = LoggerFactory.getLogger(FieldUseInfoController.class);

    @Resource
    FieldUseInfoService fieldUseInfoService;

    @ApiOperation(value = "条件查询场地使用记录")
    @PostMapping("select")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "场地使用记录标识id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "siteName", value = "场地名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "fid", value = "场地id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sno", value = "借用人账号", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "whyToUse", value = "借用用途", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "target", value = "状态标签：0、待审核；1、审核通过；2、拒绝；3、上课使用；4、已回收；5、超时回收", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "borrowTime", value = "使用总时间段数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "startTime", value = "开始时间(YYYY-MM-dd-HH:00:00)", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "endTime", value = "结束时间(YYYY-MM-dd-HH:00:00)", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "editTime", value = "编辑时间", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "totalMoney", value = "总金额", required = false, paramType = "query", dataType = "double"),
    })
    public JsonResult selectField(FieldUseInfo fieldUseInfo) {
        List<FieldUseInfo> list = fieldUseInfoService.select(fieldUseInfo);
        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "根据id查询场地使用记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "场地使用记录标识id", required = true, paramType = "query", dataType = "String"),
    })
    @GetMapping("findFieldUseInfoByID")
    public JsonResult findFieldUseInfoByID(Long id) {
        FieldUseInfo field = fieldUseInfoService.findFieldUseInfoByID(id);

        return JsonResult.success()
                .addObject("FieldUseInfo", field);
    }

    @ApiOperation(value = "根据借用人账号查询使用记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sno", value = "借用人账号", required = true, paramType = "query", dataType = "String"),
    })
    @GetMapping("findFieldUseInfoBySno")
    public JsonResult findFieldUseInfoBySno(String sno) {
        List<FieldUseInfo> list = fieldUseInfoService.findFieldUseInfoBySno(sno);

        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "根据场地使用记录标签查询使用记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "target", value = "场地使用记录标签", required = true, paramType = "query", dataType = "String"),
    })
    @GetMapping("findFieldUseInfoByTarget")
    public JsonResult findFieldUseInfoByTarget(int target) {
        List<FieldUseInfo> list = fieldUseInfoService.findFieldUseInfoByTarget(target);

        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "查询所有场地使用记录")
    @GetMapping("findAllField")
    public JsonResult findAllField() {
        List<FieldUseInfo> list = fieldUseInfoService.findAll();

        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "查询所有待审核场地使用记录")
    @GetMapping("findAllBePass")
    public JsonResult findAllBePass() {
        List<FieldUseInfo> list = fieldUseInfoService.findAllBePass();

        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "查询所有租出中场地使用记录")
    @GetMapping("findAllBeRecover")
    public JsonResult findAllBeRecover() {
        List<FieldUseInfo> list = fieldUseInfoService.findAllRenting();

        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "查询所有回收场地使用记录")
    @GetMapping("findAllRecover")
    public JsonResult findAllRecover() {
        List<FieldUseInfo> list = fieldUseInfoService.findAllRecover();

        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "普通用户预约场地")
    @PostMapping("add")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "场地使用记录标识id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "siteName", value = "场地名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "fid", value = "场地id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sno", value = "借用人账号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "whyToUse", value = "借用用途", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "target", value = "状态标签：0、待审核；1、审核通过；2、拒绝；3、上课使用；4、已回收；5、超时回收", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "borrowTime", value = "使用总时间段数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "startTime", value = "开始时间(YYYY-MM-dd-HH:00:00)", required = true, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "endTime", value = "结束时间(YYYY-MM-dd-HH:00:00)", required = true, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "editTime", value = "编辑时间", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "totalMoney", value = "总金额", required = true, paramType = "query", dataType = "double"),
    })
    public JsonResult add(FieldUseInfo fieldUseInfo) {
        fieldUseInfoService.add(fieldUseInfo);

        return JsonResult.success();

    }

    @ApiOperation(value = "删除场地使用记录")
    @DeleteMapping("delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "场地使用记录标识id", required = true, paramType = "query", dataType = "String"),
    })
    public JsonResult delete(Long id) {
        fieldUseInfoService.delete(id);

        return JsonResult.success();

    }

    @ApiOperation(value = "修改场地使用记录")
    @PostMapping("update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "场地使用记录标识id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "siteName", value = "场地名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "fid", value = "场地id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sno", value = "借用人账号", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "whyToUse", value = "借用用途", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "target", value = "状态标签：0、待审核；1、审核通过；2、拒绝；3、上课使用；4、已回收；5、超时回收", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "borrowTime", value = "使用总时间段数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "startTime", value = "开始时间(YYYY-MM-dd-HH:00:00)", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "endTime", value = "结束时间(YYYY-MM-dd-HH:00:00)", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "editTime", value = "编辑时间", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "totalMoney", value = "总金额", required = false, paramType = "query", dataType = "double"),
    })
    public JsonResult update(FieldUseInfo field) {
        fieldUseInfoService.update(field);

        return JsonResult.success();

    }

    @ApiOperation(value = "通过审批")
    @PostMapping("pass")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "场地使用记录标识id", required = true, paramType = "query", dataType = "String"),
    })
    public JsonResult pass(Long id) {
        fieldUseInfoService.updateTarget(id,1);

        return JsonResult.success();
    }

    @ApiOperation(value = "拒绝审批")
    @PostMapping("reject")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "场地使用记录标识id", required = true, paramType = "query", dataType = "String"),
    })
    public JsonResult reject(Long id) {
        fieldUseInfoService.updateTarget(id,2);

        return JsonResult.success();
    }

    @ApiOperation(value = "回收场地")
    @PostMapping("recover")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "场地使用记录标识id", required = true, paramType = "query", dataType = "String"),
    })
    public JsonResult recover(Long id) {
        fieldUseInfoService.updateTarget(id,4);

        return JsonResult.success();
    }

    @ApiOperation(value = "超时回收场地")
    @PostMapping("outTimeRecover")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "场地使用记录标识id", required = true, paramType = "query", dataType = "String"),
    })
    public JsonResult outTimeRecover(Long id) {
        fieldUseInfoService.updateTarget(id,5);

        return JsonResult.success();
    }

    @ApiOperation(value = "设置上课使用")
    @PostMapping("setInClass")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "场地使用记录标识id", required = true, paramType = "query", dataType = "String"),
    })
    public JsonResult setInClass(Long id) {
        fieldUseInfoService.updateTarget(id,3);

        return JsonResult.success();
    }
}
