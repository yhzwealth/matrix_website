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
@ApiModel(value = "Comments对象", description = "")
public class Comments extends EntityBase {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("新闻内容详情")
    private String message;

    @ApiModelProperty("文章id")
    private Long bbsId;

    @ApiModelProperty("回复评论的id")
    private Long parentId;


}
