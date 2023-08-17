package com.chuang.bootplus.vo.comments;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @program: studio-official-website
 * @author: iamYBG
 * @description: 获取评论展示
 * @create: 2021-09-02
 */

@Data
@Accessors(chain = true)
public class CommentsListVO {

    @ApiModelProperty(value = "评论id")
    private Long id;

    @ApiModelProperty(value = "发布者id")
    private Long userId;

    @ApiModelProperty(value = "作者名")
    private String author;

    @ApiModelProperty(value = "评论内容详情")
    private String message;

    @ApiModelProperty(value = "文章id")
    private Long bbsId;

    @ApiModelProperty(value = "父级评论的id")
    private Long parentId;

    @ApiModelProperty(value = "留言创建时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "上传时间(转换格式后)")
    private LocalDate gmdCreate;
}
