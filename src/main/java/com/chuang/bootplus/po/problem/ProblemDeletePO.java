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
@ApiModel(value = "删除题目", description = "")
public class ProblemDeletePO {
    @ApiModelProperty("删除题目用户")
    private Long matrixUser;
    @ApiModelProperty("题目id")
    private Long pId;
}
