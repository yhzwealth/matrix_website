package com.chuang.bootplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chuang.bootplus.base.database.EntityBase;
import com.chuang.bootplus.base.enumerate.HttpStatusEnum;
import com.chuang.bootplus.base.exception.BusException;
import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.base.utils.BeanUtil;
import com.chuang.bootplus.entity.Bbs;
import com.chuang.bootplus.entity.Comments;
import com.chuang.bootplus.entity.User;
import com.chuang.bootplus.mapper.CommentsMapper;
import com.chuang.bootplus.po.comments.CommentsListPO;
import com.chuang.bootplus.po.comments.CommentsSendPO;
import com.chuang.bootplus.service.CommentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chuang.bootplus.service.UserService;
import com.chuang.bootplus.vo.bbs.BbsListVO;
import com.chuang.bootplus.vo.comments.CommentsListVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-09-01
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements CommentsService {

    private final UserService userService;

    @Override
    public ApiResponse<Void> commentsSend(CommentsSendPO po) {
        if(userService.list(new LambdaQueryWrapper<User>().eq(User::getId,po.getUserId())).isEmpty()){
            throw new BusException("该用户不存在");
        }
        save(BeanUtil.beanA2beanB(po,Comments.class));
        return new ApiResponse<>();
    }

    @Override
    public ApiResponse<CommentsListVO> commentsList(CommentsListPO po) {
        Page<Comments> pages = new Page<>(po.getPageNumber(), po.getPageSize());

        Page<Comments> commentsPage = page(pages, new LambdaQueryWrapper<Comments>()
                .eq(Comments::getBbsId,po.getBbsId())
                .eq(po.getParentId()!=null,Comments::getParentId,po.getParentId())
                .isNull(po.getParentId()==null,Comments::getParentId)
                .orderByDesc(Comments::getGmtCreate));
        if (commentsPage.getRecords().isEmpty()) {
            return new ApiResponse<>(HttpStatusEnum.RE_CODE_NO_HAVE_OBJECT);
        }


        List<CommentsListVO> vos = BeanUtil.listA2ListB(commentsPage.getRecords(), CommentsListVO.class);
        //
        // userIdList
        List<Long> collect = vos.stream().map(CommentsListVO::getUserId).collect(Collectors.toList());
        // userMap
        Map<Long,String> users = userService.listByIds(collect).stream().collect(Collectors.toMap(EntityBase::getId, User::getUsername));

        List<CommentsListVO> commentsList = vos.stream().peek(k -> {
            k.setGmdCreate(k.getGmtCreate().toLocalDate());
            k.setAuthor(users.get(k.getUserId()));
        }).collect(Collectors.toList());
        /*
        *   100
        *
        *   1,10
        *
        *
        * */

        return new ApiResponse(commentsList,commentsPage.getTotal());
    }
}
