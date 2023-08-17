package com.chuang.bootplus.po.project.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lcc
 * @create 2021-09-10
 * @本代码仅限matrix工作室内部传阅
 */
@Data
public class ChangePassword {
    @ApiModelProperty(value = "用户名",required = true)
    private String username;

    @ApiModelProperty(value = "新密码",required = true)
    private String newPassword;

    @ApiModelProperty(value = "邮箱",required = true)
    private String email;

    @ApiModelProperty(value = "学号")
    private String studentNumber;
}

