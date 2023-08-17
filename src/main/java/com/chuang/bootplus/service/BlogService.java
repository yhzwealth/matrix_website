package com.chuang.bootplus.service;

import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.entity.Blog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chuang.bootplus.po.blog.BlogOfProblemPO;
import com.chuang.bootplus.po.blog.BlogRankPagePO;
import com.chuang.bootplus.po.blog.BlogScoreAddPO;
import com.chuang.bootplus.po.blog.BlogSubmitPO;
import com.chuang.bootplus.vo.blog.BlogVO;
import com.chuang.bootplus.vo.blog.RankVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chang
 * @since 2022-03-09
 */
public interface BlogService extends IService<Blog> {

    ApiResponse<Void> addScore(BlogScoreAddPO po);

    ApiResponse<RankVO> ranking();

    ApiResponse<Void> blogSubmit(BlogSubmitPO po);

    ApiResponse<BlogVO> getBlogs(BlogOfProblemPO po);
}
