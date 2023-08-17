package com.chuang.bootplus.vo.news;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author hsy
 * @create 2021-08-14
 * @注意
 */
@Data
@Accessors(chain = true)
public class NewsListVO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "新闻id")
    private Long id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "展示图")
    private String picture;

    @ApiModelProperty(value = "新闻创建时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "上传时间(转换格式后)")
    private LocalDate gmdCreate;
}
