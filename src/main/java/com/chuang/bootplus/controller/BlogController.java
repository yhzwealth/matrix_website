package com.chuang.bootplus.controller;


import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.po.blog.BlogOfProblemPO;
import com.chuang.bootplus.po.blog.BlogRankPagePO;
import com.chuang.bootplus.po.blog.BlogScoreAddPO;
import com.chuang.bootplus.service.BlogService;
import com.chuang.bootplus.vo.blog.BlogVO;
import com.chuang.bootplus.vo.blog.RankVO;
import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.po.blog.BlogSubmitPO;
import com.chuang.bootplus.po.comments.CommentsSendPO;
import com.chuang.bootplus.service.BlogService;
import com.chuang.bootplus.service.UserService;
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
 * @author chang
 * @since 2022-03-09
 */

@RestController
@RequestMapping("/blog")
@Api(tags = {"每日一题博客"})
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*")
public class BlogController {

    final BlogService blogService;


    @PostMapping("addScore")
    @ApiOperation(httpMethod = "POST", value = "组长给组员打分")
    public ApiResponse<Void> addScoreAttention(@RequestBody BlogScoreAddPO po){
        return blogService.addScore(po);
    }
    @PostMapping("ranking")
    @ApiOperation(httpMethod = "POST", value = "用户排名")
    public ApiResponse<RankVO> ranking(){
        return blogService.ranking();
    }

    @PostMapping("submit")
    @ApiOperation(httpMethod = "POST", value = "提交博客链接")
    public ApiResponse<Void> blogSubmit(@RequestBody BlogSubmitPO po){
        return blogService.blogSubmit(po);
    }

    @PostMapping("getBlogs")
    @ApiOperation(httpMethod = "POST", value = "获取某题所有题解博客")
    public ApiResponse<BlogVO> getBlogs(@RequestBody BlogOfProblemPO po){
        return blogService.getBlogs(po);
    }

}




