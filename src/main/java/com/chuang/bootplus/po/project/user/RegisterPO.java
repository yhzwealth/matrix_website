package com.chuang.bootplus.po.project.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lcc
 * @create 2021-08-31
 * @本代码仅限matrix工作室内部传阅
 */
@Data
@ApiModel(value = "注册、招贤纳士", description = "")
public class RegisterPO  {

    @ApiModelProperty(value = "真实姓名")
    private String username;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "qq号")
    private String qqNumber;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "邮箱",required = true)
    private String email;


    @ApiModelProperty(value = "密码",required = true)
    private String password;


    @ApiModelProperty(value = "学号")
    private String studentNumber;



}
