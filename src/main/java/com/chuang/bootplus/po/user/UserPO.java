package com.chuang.bootplus.po.user;

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
@ApiModel(value = "成员", description = "")
public class UserPO {

    @ApiModelProperty("成员id")
    private Long id;

    @ApiModelProperty("执行操作的成员id")
    private Long empId;

    @ApiModelProperty("真实姓名")
    private String username;

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("qq号")
    private String qqNumber;

    @ApiModelProperty("角色")
    private Long roleId;

    @ApiModelProperty("分组")
    private Long groupId;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("状态")
    private Boolean status;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("学号")
    private String studentNumber;

    @ApiModelProperty("密码")
    private String password;


    @ApiModelProperty("用户分为游客和工作室成员两种")
    private Integer isMatrix;

    @ApiModelProperty("M1~M5，5个等级")
    private Integer userLevel;

    @ApiModelProperty("积分")
    private Long score;

    @ApiModelProperty("工作室成员指导学长ID")
    private Long mentorId;

    @ApiModelProperty("是否订阅每日一题推送")
    private Integer isSubscribe;
}
