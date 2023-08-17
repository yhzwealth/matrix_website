package com.chuang.bootplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.base.utils.BeanUtil;
import com.chuang.bootplus.entity.SysGroup;
import com.chuang.bootplus.mapper.SysGroupMapper;
import com.chuang.bootplus.po.group.GroupPO;
import com.chuang.bootplus.service.SysGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chuang.bootplus.vo.group.GroupVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
public class SysGroupServiceImpl extends ServiceImpl<SysGroupMapper, SysGroup> implements SysGroupService {

    private final SysGroupMapper sysGroupMapper;

    @Override
    public ApiResponse<GroupVO> groupList(GroupPO groupPO) {
        List<SysGroup> list = list(
                new LambdaQueryWrapper<SysGroup>()
                .orderByAsc(SysGroup::getSort)
        );
        List<GroupVO> groupVOS = BeanUtil.listA2ListB(list, GroupVO.class);
        return new ApiResponse<GroupVO>(
                groupVOS
        );

    }
}
