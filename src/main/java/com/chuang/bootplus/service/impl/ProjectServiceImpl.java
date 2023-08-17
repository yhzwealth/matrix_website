package com.chuang.bootplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chuang.bootplus.base.enumerate.HttpStatusEnum;
import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.base.utils.BeanUtil;
import com.chuang.bootplus.entity.Project;
import com.chuang.bootplus.mapper.ProjectMapper;
import com.chuang.bootplus.po.project.ProjectListPO;
import com.chuang.bootplus.service.ProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chuang.bootplus.vo.project.ProjectListVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

    @Override
    public ApiResponse<ProjectListVO> projectList(ProjectListPO po) {
        Page<Project> pages = new Page<>(po.getPageNumber(), po.getPageSize());

        Page<Project> projectPage = page(pages, new LambdaQueryWrapper<Project>()
                .like(po.getProjectName() != null, Project::getProjectName, po.getProjectName())
                .orderByDesc(Project::getGmtCreate));
        if (projectPage == null) {
            return new ApiResponse<>(HttpStatusEnum.RE_CODE_NO_HAVE_OBJECT);
        }

        List<ProjectListVO> projectListVOS = BeanUtil.listA2ListB(projectPage.getRecords(), ProjectListVO.class);

        List<ProjectListVO> collect = projectListVOS.stream().peek(k -> {
            k.setGmdCreate(k.getGmtCreate().toLocalDate());
        }).collect(Collectors.toList());

        return new ApiResponse(collect, projectPage.getTotal());
    }
}
