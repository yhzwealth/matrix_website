package com.chuang.bootplus.po.comments;

import com.chuang.bootplus.base.database.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @program: studio-official-website
 * @author: iamYBG
 * @description: 获取评论
 * @create: 2021-09-02
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "获取评论", description = "")
public class CommentsListPO extends Page {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "留言id")
    private Long bbsId;

    @ApiModelProperty(value = "父级评论id(不必须)")
    private Long parentId;
}
