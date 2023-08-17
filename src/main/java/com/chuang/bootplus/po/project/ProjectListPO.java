package com.chuang.bootplus.po.project;

import com.baomidou.mybatisplus.annotation.TableField;
import com.chuang.bootplus.base.database.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author hsy
 * @create 2021-08-14
 * @注意
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "项目名筛选条件", description = "")
public class ProjectListPO extends Page {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目名(不必须)")
    private String projectName;

}
