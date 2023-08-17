package com.chuang.bootplus.po.project.user;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户是否订阅", description = "")
public class SubscribePO {

    @ApiModelProperty(value = "订阅人id")
    private Integer userId;

    @ApiModelProperty(value = "0为订阅  1为取消订阅")
    private Integer flag;

}
