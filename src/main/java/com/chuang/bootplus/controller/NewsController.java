package com.chuang.bootplus.controller;


import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.po.news.NewsListPO;
import com.chuang.bootplus.po.news.NewsMessagePO;
import com.chuang.bootplus.service.NewsService;
import com.chuang.bootplus.vo.news.NewsListVO;
import com.chuang.bootplus.vo.news.NewsMessageVO;
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
 * @author ${author}
 * @since 2021-08-14
 */
@Api(tags = {"新闻中心"})
@RestController
@RequestMapping("/news")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*")
public class NewsController {

    private final NewsService newsService;

    @PostMapping("newsList")
    @ApiOperation(httpMethod = "POST", value = "新闻详情列表")
    @ApiOperationSupport(includeParameters = {"NewsListPO.title", "NewsListPO.pageSize", "NewsListPO.pageNumber"},ignoreParameters = {"BasePO.id"})
    public ApiResponse<NewsListVO> newsList(@RequestBody NewsListPO po) {
        return newsService.newsList(po);
    }

    @PostMapping("getNewsById")
    @ApiOperation(httpMethod = "POST", value = "根据id获取新闻详情")
    @ApiOperationSupport(includeParameters = {"NewsMessagePO.id"})
    public ApiResponse<NewsMessageVO> getNewsById(@RequestBody NewsMessagePO po) {
        return newsService.getNewsById(po);
    }

}

