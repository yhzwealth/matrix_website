package com.chuang.bootplus.po.blog;

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
@ApiModel(value = "提交博客", description = "")
public class BlogSubmitPO {

    @ApiModelProperty(value = "提交用户id")
    private Long userId;

    @ApiModelProperty(value = "题目id",example = "题目id")
    private Long proId;

    @ApiModelProperty(value = "题目名")
    private String proName;

    @ApiModelProperty(value = "提交链接")
    private String link;


    @ApiModelProperty(value = "组别")
    private String groupName;


}
