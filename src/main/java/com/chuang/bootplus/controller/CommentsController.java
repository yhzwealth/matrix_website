package com.chuang.bootplus.controller;


import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.po.bbs.BbsSendPO;
import com.chuang.bootplus.po.comments.CommentsListPO;
import com.chuang.bootplus.po.comments.CommentsSendPO;
import com.chuang.bootplus.service.CommentsService;
import com.chuang.bootplus.vo.comments.CommentsListVO;
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
@RequestMapping("/comments")
@Api(tags = {"留言板评论区"})
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*")
public class CommentsController {

    private final CommentsService commentsService;

    @PostMapping("send")
    @ApiOperation(httpMethod = "POST", value = "发送留言")
    @ApiOperationSupport(includeParameters = {"CommentsSendPO.userId", "CommentsSendPO.message","CommentsSendPO.bbsId","CommentsSendPO.parentId"})
    public ApiResponse<Void> commentsSend(@RequestBody CommentsSendPO po){
        return commentsService.commentsSend(po);
    }

    @PostMapping("list")
    @ApiOperation(httpMethod = "POST", value = "获取留言")
    @ApiOperationSupport(includeParameters = {"CommentsListPO.parentId","CommentsListPO.bbsId", "CommentsListPO.pageSize", "CommentsListPO.pageNumber"},ignoreParameters = {"CommentsListPO.id"})
    public ApiResponse<CommentsListVO> commentsList(@RequestBody CommentsListPO po){
        return commentsService.commentsList(po);
    }

}

