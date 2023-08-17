package com.chuang.bootplus.po.Email;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EmailVertifyPO {


    @ApiModelProperty(value = "验证的key")
    private String emailKey;

    @ApiModelProperty(value = "QQ邮箱的验证码")
    private String emailCode;


}
