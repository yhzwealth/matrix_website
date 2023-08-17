package com.chuang.bootplus.po.wx;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;


/**
 * @description: TODO
 * @author nuo
 * @date 2022/7/28 13:10
 * @version 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode()
@ApiModel(value = "微信登陆", description = "")
public class WxLoginPo{

    @ApiModelProperty("微信生成的随机码")
    private String code;

    @ApiModelProperty("用于保持请求和回调的状态，授权请求后原样带回给第三方。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议第三方带上该参数，可设置为简单的随机数加session进行校验")
    private String state;
}
