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
@RequestMapping("/conttest/")
@RestController
@CrossOrigin
public class ContestController {

    @Resource
    ContestService contestService;


    @ApiOperation(value = "条件查询")
    @PostMapping("select")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "赛事id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "赛事名字", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "organizers", value = "赛事主办方", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "openTime", value = "赛事开始时间", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "date", value = "赛事日期", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "object", value = "参赛对象", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "tele", value = "赛事主办方电话", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "place", value = "赛事场地使用ID", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "equipment", value = "赛事器材使用id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "target", value = "赛事id", required = false, paramType = "query", dataType = "number"),
    })
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
    @ApiOperation(value = "根据target查询所有")
    @GetMapping("findByTarget")
    public JsonResult findByTarget(Integer target) {
        List<Contest> list = contestService.findByTarget(target);

        return JsonResult.success().addObject("list", list);

    }

    @ApiOperation(value = "添加")
    @PostMapping("add")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "赛事id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "赛事名字", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "organizers", value = "赛事主办方", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "openTime", value = "赛事开始时间", required = true, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "date", value = "编辑时间", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "object", value = "参赛对象", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "tele", value = "赛事主办方电话", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "place", value = "赛事场地使用ID", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "equipment", value = "赛事器材使用id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "target", value = "标签状态：1、刚创建未复核；2、已复核", required = false, paramType = "query", dataType = "number"),
    })
    public JsonResult add(Contest contest) {
        contestService.add(contest);

        return JsonResult.success();

    }

    @ApiOperation(value = "删除")
    @DeleteMapping("delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "赛事id", required = true, paramType = "query", dataType = "String"),
    })
    public JsonResult delete(Long id) {
        contestService.delete(id);

        return JsonResult.success();

    }

    @ApiOperation(value = "修改")
    @PostMapping("update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "赛事id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "赛事名字", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "organizers", value = "赛事主办方", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "openTime", value = "赛事开始时间", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "date", value = "赛事日期", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "object", value = "参赛对象", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "tele", value = "赛事主办方电话", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "place", value = "赛事场地使用ID", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "equipment", value = "赛事器材使用id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "target", value = "赛事id", required = false, paramType = "query", dataType = "number"),
    })
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
