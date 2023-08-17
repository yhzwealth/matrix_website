package com.chuang.bootplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chuang.bootplus.base.enumerate.HttpStatusEnum;
import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.base.utils.BeanUtil;
import com.chuang.bootplus.entity.News;
import com.chuang.bootplus.mapper.NewsMapper;
import com.chuang.bootplus.po.news.NewsListPO;
import com.chuang.bootplus.po.news.NewsMessagePO;
import com.chuang.bootplus.service.NewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chuang.bootplus.service.UserService;
import com.chuang.bootplus.vo.news.NewsListVO;
import com.chuang.bootplus.vo.news.NewsMessageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-08-14
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

    private final UserService userService;

    @Override
    public ApiResponse<NewsListVO> newsList(NewsListPO po) {
        Page<News> pages = new Page<>(po.getPageNumber(), po.getPageSize());

        Page<News> newsPage = page(pages, new LambdaQueryWrapper<News>()
                .like(po.getTitle() != null, News::getTitle, po.getTitle())
                .orderByDesc(News::getGmtCreate));
        if (newsPage == null) {
            return new ApiResponse<>(HttpStatusEnum.RE_CODE_NO_HAVE_OBJECT);
        }

        List<NewsListVO> newsListVOS = BeanUtil.listA2ListB(newsPage.getRecords(), NewsListVO.class);

        List<NewsListVO> collect = newsListVOS.stream().peek(k -> {
            k.setGmdCreate(k.getGmtCreate().toLocalDate());
            k.setAuthor(userService.getById(k.getUserId()).getUsername());
        }).collect(Collectors.toList());
//        return new ApiResponse();
        return new ApiResponse(collect, newsPage.getTotal());
    }

    @Override
    public ApiResponse<NewsMessageVO> getNewsById(NewsMessagePO po) {

        News info = getById(po.getId());
        if (info == null) {
            return new ApiResponse<>(HttpStatusEnum.RE_CODE_NO_HAVE_OBJECT);
        }

        NewsMessageVO newsMessageVO = BeanUtil.beanA2beanB(info, NewsMessageVO.class);
        newsMessageVO.setGmdCreate(info.getGmtCreate().toLocalDate());

        return new ApiResponse<>(newsMessageVO);
    }
}
