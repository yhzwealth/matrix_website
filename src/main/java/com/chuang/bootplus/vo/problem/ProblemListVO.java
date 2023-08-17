package com.chuang.bootplus.vo.problem;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class ProblemListVO {
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

    @ApiModelProperty("题目创建时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "上传时间(转换格式后)")
    private LocalDate gmdCreate;

}
