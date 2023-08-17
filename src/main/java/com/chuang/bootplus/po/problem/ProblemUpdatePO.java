package com.chuang.bootplus.po.problem;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode()
@ApiModel(value = "更新题目", description = "")
public class ProblemUpdatePO {
    @ApiModelProperty("发布题目用户")
    private Long matrixUser;

    @ApiModelProperty("题目id")
    private Long id;

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

    @ApiModelProperty(value = "修改创建时间 yyyy-MM-dd HH:mm:ss", example = "2020-11-28 00:12:23", required = true)
    private String gmtCreate;;


}
