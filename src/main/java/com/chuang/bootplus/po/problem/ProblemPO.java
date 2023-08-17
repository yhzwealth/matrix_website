package com.chuang.bootplus.po.problem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode()
@ApiModel(value = "添加题目", description = "")
public class ProblemPO {
    @ApiModelProperty("发布题目用户")
    private Long matrixUser;

    @ApiModelProperty("题目名称")
    private String title;

    @ApiModelProperty("题目链接")
    private String link;

    @ApiModelProperty("题目描述")
    private String problemDescribe;

    @ApiModelProperty("题目等级")
    private String problemLevel;

    @ApiModelProperty("题目标签")
    private String tag;

    @ApiModelProperty("算法标签")
    private String algorithmTag;

    @ApiModelProperty("数据结构标签")
    private String dataStructureTag;

    @ApiModelProperty("题目参考答案")
    private String answer;

    @ApiModelProperty("题目解答视频")
    private String video;

    @ApiModelProperty(value = "题目创建时间 yyyy-MM-dd HH:mm:ss", example = "2020-11-28 00:12:23", required = false)
    private String createTime;

}
