package com.chuang.bootplus.po.user;

import com.chuang.bootplus.base.database.Page;
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
@ApiModel(value = "根据条件查询成员", description = "")
public class UserConditionPO extends Page {

    @ApiModelProperty("真实姓名")
    private String username;

    @ApiModelProperty("角色")
    private Long roleId;

    @ApiModelProperty("分组")
    private Long groupId;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("学号")
    private String studentNumber;

    @ApiModelProperty("状态")
    private Boolean status;

    @ApiModelProperty("用户分为游客和工作室成员两种")
    private Integer isMatrix;

    @ApiModelProperty("M1~M5，5个等级")
    private Integer userLevel;

    @ApiModelProperty("工作室成员指导学长ID")
    private Long mentorId;

    @ApiModelProperty("是否订阅每日一题推送")
    private Integer isSubscribe;
}
