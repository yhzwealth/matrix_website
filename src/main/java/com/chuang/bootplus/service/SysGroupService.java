package com.chuang.bootplus.service;

import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.entity.SysGroup;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chuang.bootplus.po.group.GroupPO;
import com.chuang.bootplus.vo.group.GroupVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-08-14
 */
public interface SysGroupService extends IService<SysGroup> {

    ApiResponse<GroupVO> groupList(GroupPO groupPO);
}
