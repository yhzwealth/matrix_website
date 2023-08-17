package com.chuang.bootplus.controller;


import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.po.bbs.BbsListPO;
import com.chuang.bootplus.po.bbs.BbsSendPO;
import com.chuang.bootplus.service.BbsService;
import com.chuang.bootplus.vo.bbs.BbsListVO;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
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
 * @author YBG
 * @since 2021-09-01
 */
@RestController
@RequestMapping("/bbs")
@Api(tags = {"留言板"})
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*")
public class BbsController {

    private final BbsService bbsService;


    @PostMapping("bbsList")
    @ApiOperation(httpMethod = "POST", value = "获取留言板内容")
    @ApiOperationSupport(includeParameters = {"BbsListPO.pageSize", "BbsListPO.pageNumber"},ignoreParameters = {"BasePO.id"})
    public ApiResponse<BbsListVO> bbsList(@RequestBody BbsListPO po){
        return bbsService.bbsList(po);
    }


    @PostMapping("bbsSend")
    @ApiOperation(httpMethod = "POST", value = "发送留言")
    @ApiOperationSupport(includeParameters = {"BbsSendPO.userId", "BbsSendPO.message"})
    public ApiResponse<Void> bbsSend(@RequestBody BbsSendPO po){
        return bbsService.bbsSend(po);
    }

}

