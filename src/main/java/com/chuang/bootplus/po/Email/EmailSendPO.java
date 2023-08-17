package com.chuang.bootplus.po.Email;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EmailSendPO {

    @ApiModelProperty(value = "qq邮箱地址")
    String qqStr;
}
