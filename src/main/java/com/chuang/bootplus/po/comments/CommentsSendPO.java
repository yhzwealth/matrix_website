package com.chuang.bootplus.po.comments;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @program: studio-official-website
 * @author: iamYBG
 * @description: 发送评论
 * @create: 2021-09-02
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode()
@ApiModel(value = "发布评论", description = "")
public class CommentsSendPO {

    @ApiModelProperty(value = "发布者用户id")
    private Long userId;

    @ApiModelProperty(value = "留言内容")
    private String message;

    @ApiModelProperty(value = "文章id")
    private Long bbsId;

    @ApiModelProperty(value = "回复评论的id(不必须)")
    private Long parentId;
}
