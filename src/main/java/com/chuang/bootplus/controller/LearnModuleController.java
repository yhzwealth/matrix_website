package com.chuang.bootplus.controller;


import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.po.leanmodule.LearnModulePO;
import com.chuang.bootplus.service.LearnModuleService;
import com.chuang.bootplus.vo.group.GroupVO;
import com.chuang.bootplus.vo.leanmodule.LearnModuleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2021-08-14
 */
@RestController
@RequestMapping("/learn/module")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(tags = {"每个组别的学习路线"})
@CrossOrigin(origins = "*")
public class LearnModuleController {

    private final LearnModuleService learnModuleService;

    @PostMapping("/list")
    @ApiOperation("获取每个组别的学习路线列表")
    public ApiResponse<LearnModuleVO> learnModuleList(@RequestBody LearnModulePO learnModulePO){
//        return learnModuleService.learnModuleList(learnModulePO);
        return learnModuleService.learnModuleListByRedis(learnModulePO);
    }
}

