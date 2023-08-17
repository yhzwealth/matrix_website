package com.chuang.bootplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chuang.bootplus.base.database.EntityBase;
import com.chuang.bootplus.base.enumerate.HttpStatusEnum;
import com.chuang.bootplus.base.exception.BusException;
import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.base.utils.BeanUtil;
import com.chuang.bootplus.entity.Bbs;
import com.chuang.bootplus.entity.User;
import com.chuang.bootplus.mapper.BbsMapper;
import com.chuang.bootplus.po.bbs.BbsListPO;
import com.chuang.bootplus.po.bbs.BbsSendPO;
import com.chuang.bootplus.service.BbsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chuang.bootplus.service.UserService;
import com.chuang.bootplus.vo.bbs.BbsListVO;
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
public class BbsServiceImpl extends ServiceImpl<BbsMapper, Bbs> implements BbsService {

    private final UserService userService;

    @Override
    public ApiResponse<BbsListVO> bbsList(BbsListPO po) {
        Page<Bbs> pages = new Page<>(po.getPageNumber(), po.getPageSize());

        Page<Bbs> bbsPage = page(pages, new LambdaQueryWrapper<Bbs>()
                .orderByDesc(Bbs::getGmtCreate));
        if (bbsPage.getRecords().isEmpty()) {
            return new ApiResponse<>(HttpStatusEnum.RE_CODE_NO_HAVE_OBJECT);
        }

        List<BbsListVO> bbsListVOS = BeanUtil.listA2ListB(bbsPage.getRecords(), BbsListVO.class);
        List<Long> collect = bbsListVOS.stream().map(BbsListVO::getUserId).collect(Collectors.toList());
        Map<Long,String> users = userService.listByIds(collect).stream().collect(Collectors.toMap(EntityBase::getId, User::getUsername));

        List<BbsListVO> bbsList = bbsListVOS.stream().peek(k -> {
            k.setGmdCreate(k.getGmtCreate().toLocalDate());
            k.setAuthor(users.get(k.getUserId()));
        }).collect(Collectors.toList());

        return new ApiResponse(bbsList, bbsPage.getTotal());
    }

    @Override
    public ApiResponse<Void> bbsSend(BbsSendPO po) {
        if(userService.list(new LambdaQueryWrapper<User>().eq(User::getId,po.getUserId())).isEmpty()){
            throw new BusException("该用户不存在");
        }
        save(BeanUtil.beanA2beanB(po,Bbs.class));
        return new ApiResponse<>();
    }
}
