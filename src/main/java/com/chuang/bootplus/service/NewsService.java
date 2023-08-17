package com.chuang.bootplus.service;

import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.entity.News;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chuang.bootplus.po.news.NewsListPO;
import com.chuang.bootplus.po.news.NewsMessagePO;
import com.chuang.bootplus.vo.news.NewsListVO;
import com.chuang.bootplus.vo.news.NewsMessageVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-08-14
 */
public interface NewsService extends IService<News> {

    /**
     * 新闻详情列表
     * @param po
     * @return 无
     */
    ApiResponse<NewsListVO> newsList(NewsListPO po);

    /**
     * 根据id获取新闻详情
     * @param po
     * @return 无
     */
    ApiResponse<NewsMessageVO> getNewsById(NewsMessagePO po);
}
