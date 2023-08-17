package com.chuang.bootplus.controller;


import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.po.group.GroupPO;
import com.chuang.bootplus.service.SysGroupService;
import com.chuang.bootplus.vo.group.GroupVO;
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
@RequestMapping("/group")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(tags = {"组别"})
@CrossOrigin(origins = "*")
public class SysGroupController {

    private final SysGroupService sysGroupService;

    @PostMapping("/list")
    @ApiOperation("获取组别列表")
    public ApiResponse<GroupVO> groupList(@RequestBody GroupPO groupPO){
        return sysGroupService.groupList(groupPO);
    }
}

