package com.chuang.bootplus.vo.bbs;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @program: studio-official-website
 * @author: iamYBG
 * @description: 留言板内容返回
 * @create: 2021-09-02
 */

@Data
@Accessors(chain = true)
public class BbsListVO {

    @ApiModelProperty(value = "留言id")
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "作者名")
    private String author;

    @ApiModelProperty(value = "留言内容详情")
    private String message;

    @ApiModelProperty(value = "展示图")
    private String picture;

    @ApiModelProperty(value = "留言创建时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "上传时间(转换格式后)")
    private LocalDate gmdCreate;

}
