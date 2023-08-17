package com.chuang.bootplus.po.wx;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @description: TODO
 * @author nuo
 * @date 2022/8/5 19:01
 * @version 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode()
@ApiModel(value = "通过微信获取详细的用户信息", description = "")
public class WxUserInfoPo {

    @ApiModelProperty("微信 openid")
    private String openid;

}
