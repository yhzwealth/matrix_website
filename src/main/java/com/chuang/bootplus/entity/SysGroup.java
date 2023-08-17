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
@TableName("sys_group")
@ApiModel(value = "SysGroup对象", description = "")
public class SysGroup extends EntityBase {

    private static final long serialVersionUID = 1L;

    private String groupName;

    private Boolean sort;


}
