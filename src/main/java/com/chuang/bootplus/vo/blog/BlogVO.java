package com.chuang.bootplus.vo.blog;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class BlogVO {

    @ApiModelProperty(value = "博客id")
    private Long id;

    @ApiModelProperty(value = "绑定的每日一题id")
    private Long proId;



    @ApiModelProperty(value = "提交链接用户id")
    private Long userId;

    @ApiModelProperty(value = "提交链接用户名")
    private String userName;

    @ApiModelProperty(value = "题目链接")
    private String proLink;

    @ApiModelProperty(value = "题目名")
    private String proName;

    @ApiModelProperty(value = "博客链接")
    private String link;

    @ApiModelProperty(value = "博客得分")
    private Integer score;

    @ApiModelProperty(value = "时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "时间转换后")
    private String created;

}
