package com.chuang.bootplus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("learn_module")
@ApiModel(value = "LearnModule对象", description = "")
public class LearnModule extends EntityBase {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("等级")
    private Integer moduleLevel;

    private String moduleName;

    private Long groupId;

    private Integer moduleDays;

    @ApiModelProperty("描述")
    private String moduleDescribe;

    private Boolean sort;


}
