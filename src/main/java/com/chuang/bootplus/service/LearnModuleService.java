package com.chuang.bootplus.service;

import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.entity.LearnModule;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chuang.bootplus.po.leanmodule.LearnModulePO;
import com.chuang.bootplus.vo.group.GroupVO;
import com.chuang.bootplus.vo.leanmodule.LearnModuleVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-08-14
 */
public interface LearnModuleService extends IService<LearnModule> {

    ApiResponse<LearnModuleVO> learnModuleList(LearnModulePO learnModulePO);

    public ApiResponse<LearnModuleVO> learnModuleListByRedis(LearnModulePO learnModulePO);
}
