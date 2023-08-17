package com.chuang.bootplus.service;

import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.entity.Comments;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chuang.bootplus.po.comments.CommentsListPO;
import com.chuang.bootplus.po.comments.CommentsSendPO;
import com.chuang.bootplus.vo.comments.CommentsListVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YBG
 * @since 2021-09-01
 */
public interface CommentsService extends IService<Comments> {
    ApiResponse<Void> commentsSend(CommentsSendPO po);

    ApiResponse<CommentsListVO> commentsList(CommentsListPO po);
}
