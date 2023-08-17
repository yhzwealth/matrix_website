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
@TableName("learn_point")
@ApiModel(value = "LearnPoint对象", description = "")
public class LearnPoint extends EntityBase {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("类型：video、	、blog	、website	、tutorials(教程)	、book		")
    private String pointType;

    private String pointName;

    private Long moduleId;

    private String pointLink;

    private Boolean sort;


}
