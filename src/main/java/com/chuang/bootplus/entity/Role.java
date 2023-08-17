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
@ApiModel(value = "Role对象", description = "")
public class Role extends EntityBase {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色名")
    private String name;

    @ApiModelProperty("备注")
    private String remark;


}
