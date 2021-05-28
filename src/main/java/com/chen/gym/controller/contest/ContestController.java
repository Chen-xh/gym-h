package com.chen.gym.controller.contest;

import com.chen.gym.bean.Contest;
import com.chen.gym.service.contest.ContestService;
import com.chen.gym.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author CHEN
 * @date 2021/5/26
 */
@Api(tags = "赛事管理接口")
@RestController
@CrossOrigin
public class ContestController {

    @Resource
    ContestService contestService;


    @ApiOperation(value = "条件查询")
    @PostMapping("select")
    public JsonResult select(Contest contest) {
        List<Contest> list = contestService.select(contest);
        return JsonResult.success().addObject("list", list);
    }

    @ApiOperation(value = "根据id查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "赛事id", required = true, paramType = "query", dataType = "String"),
    })
    @GetMapping("findContestById")
    public JsonResult findContestById(Long cid) {
        Contest contest = contestService.findContestById(cid);

        return JsonResult.success()
                .addObject("contest", contest);

    }

    @ApiOperation(value = "安排器材")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "赛事id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "eid", value = "器材id", required = true, paramType = "query", dataType = "String"),
    })
    @GetMapping("arrayEquipment")
    public JsonResult arrayEquipment(Long cid, Long eid) {
        contestService.arrayEquipment(cid, eid);

        return JsonResult.success();

    }

    @ApiOperation(value = "安排场地")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "赛事id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "fid", value = "场地id", required = true, paramType = "query", dataType = "String"),
    })
    @GetMapping("arrayField")
    public JsonResult arrayField(Long cid, Long fid) {
        contestService.arrayField(cid, fid);

        return JsonResult.success();

    }

    @ApiOperation(value = "查询所有")
    @GetMapping("findAll")
    public JsonResult findAll() {
        List<Contest> list = contestService.findAll();

        return JsonResult.success().addObject("list", list);

    }

    @ApiOperation(value = "添加")
    @PostMapping("add")
    public JsonResult add(Contest contest) {
        contestService.add(contest);

        return JsonResult.success();

    }

    @ApiOperation(value = "删除")
    @DeleteMapping("delete")
    public JsonResult delete(Long id) {
        contestService.delete(id);

        return JsonResult.success();

    }

    @ApiOperation(value = "修改")
    @PostMapping("update")
    public JsonResult update(Contest contest) {
        contestService.update(contest);

        return JsonResult.success();

    }

    @ApiOperation(value = "管理员复核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "赛事id", required = true, paramType = "query", dataType = "String"),
    })
    @GetMapping("review")
    public JsonResult review(Long cid) {
        contestService.review(cid);

        return JsonResult.success();

    }

}
