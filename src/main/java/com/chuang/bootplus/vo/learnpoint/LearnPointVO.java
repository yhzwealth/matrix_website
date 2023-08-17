package com.chuang.bootplus.vo.learnpoint;

import com.chuang.bootplus.base.database.EntityBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author ${author}
 * @since 2021-08-14
 */
@Data

public class LearnPointVO {


    @ApiModelProperty(value = "类型：video、	、blog	、website	、tutorials(教程)	、book		")
    private String pointType;

    private String pointName;

    private Long moduleId;

    private String pointLink;




}
