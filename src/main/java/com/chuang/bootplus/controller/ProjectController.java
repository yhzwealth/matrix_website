package com.chuang.bootplus.controller;


import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.po.project.ProjectListPO;
import com.chuang.bootplus.service.ProjectService;
import com.chuang.bootplus.vo.project.ProjectListVO;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2021-08-14
 */
@Api(tags = {"项目广场"})
@RestController
@RequestMapping("/project")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("projectList")
    @ApiOperation(httpMethod = "POST", value = "项目列表")
    @ApiOperationSupport(includeParameters = {"ProjectListPO.projectName", "ProjectListPO.pageSize", "ProjectListPO.pageNumber"},ignoreParameters = {"BasePO.id"})
    public ApiResponse<ProjectListVO> projectList(@RequestBody ProjectListPO po) {
        return projectService.projectList(po);
    }
}

