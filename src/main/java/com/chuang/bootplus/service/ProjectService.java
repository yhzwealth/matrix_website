package com.chuang.bootplus.service;

import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.entity.Project;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chuang.bootplus.po.project.ProjectListPO;
import com.chuang.bootplus.vo.project.ProjectListVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-08-14
 */
public interface ProjectService extends IService<Project> {

    /**
     * 项目列表
     * @param po
     * @return 无
     */
    ApiResponse<ProjectListVO> projectList(ProjectListPO po);

}
