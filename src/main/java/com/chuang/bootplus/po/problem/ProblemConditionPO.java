package com.chuang.bootplus.po.problem;

import com.chuang.bootplus.base.database.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode()
@ApiModel(value = "根据多个标签筛选题目", description = "")
public class ProblemConditionPO extends Page {
    @ApiModelProperty("题目名称")
    private String title;

    @ApiModelProperty("标签")
    private String tag;

    @ApiModelProperty("题目等级")
    private String problemLevel;

    @ApiModelProperty("算法标签")
    private String algorithmTag;

    @ApiModelProperty("数据结构标签")
    private String dataStructureTag;

    @ApiModelProperty("每日一题时间")
    private String gmtCreate;



}
