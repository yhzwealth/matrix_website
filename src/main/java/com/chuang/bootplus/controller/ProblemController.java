package com.chuang.bootplus.controller;

import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.po.news.NewsListPO;
import com.chuang.bootplus.po.problem.*;
import com.chuang.bootplus.service.ProblemService;
import com.chuang.bootplus.vo.news.NewsListVO;
import com.chuang.bootplus.vo.problem.ProblemListVO;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"每日一题"})
@RestController
@RequestMapping("/problem")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*")
public class ProblemController {

    private final ProblemService problemService;

    @PostMapping("getAll")
    @ApiOperation(httpMethod = "POST", value = "获取所有题目")
    public ApiResponse<ProblemListVO> getAll(@RequestBody ProblemListPO po) {
        return problemService.getAll(po);
    }

    @PostMapping("sendProblem")
    @ApiOperation(httpMethod = "POST", value = "发送（添加）题目")
    public ApiResponse<Void> sendProblem(@RequestBody ProblemPO po) {
        return problemService.sendProblem(po);
    }

    @PostMapping("deleteProblem")
    @ApiOperation(httpMethod = "POST", value = "删除题目")
    public ApiResponse<Void> deleteProblem(@RequestBody ProblemDeletePO po) {
        return problemService.deleteProblem(po);
    }

    @PostMapping("updateProblem")
    @ApiOperation(httpMethod = "POST", value = "修改题目")
    public ApiResponse<Void> modifyProblem(@RequestBody ProblemUpdatePO po) {
        return problemService.modifyProblem(po);
    }

/*
    @PostMapping("getByTitle")
    @ApiOperation(httpMethod = "POST", value = "根据名称查找指定题目")
    public ApiResponse<ProblemListVO> getByTitle(@RequestBody ProblemTitlePO po) {
        return problemService.getByTitle(po);
    }

    @PostMapping("getByTime")
    @ApiOperation(httpMethod = "POST", value = "根据日期查找指定题目")
    public ApiResponse<ProblemListVO> getByTime(@RequestBody ProblemTimePO po) {
        return problemService.getByTime(po);
    }

    @PostMapping("getByLevel")
    @ApiOperation(httpMethod = "POST", value = "根据题目等级查找题目")
    public ApiResponse<ProblemListVO> getByLevel(@RequestBody ProblemLevelPO po) {
        return problemService.getByLevel(po);
    }

 */

    @PostMapping("getByConditions")
    @ApiOperation(httpMethod = "POST", value = "根据提供条件查找题目")
    public ApiResponse<ProblemListVO> getByConditions(@RequestBody ProblemConditionPO po) {
        return problemService.getByConditions(po);
    }


}
