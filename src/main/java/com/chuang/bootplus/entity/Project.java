package com.chuang.bootplus.entity;

import com.chuang.bootplus.base.database.EntityBase;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author chang
 * @since 2022-03-09
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "Project对象", description = "")
public class Project extends EntityBase {

    private static final long serialVersionUID = 1L;

    private String projectName;

    @ApiModelProperty("描述")
    private String projectDescribe;

    @ApiModelProperty("展示图")
    private String image;

    @ApiModelProperty("项目对应git链接")
    private String link;

    @ApiModelProperty("项目负责人id")
    private Long userId;


}
