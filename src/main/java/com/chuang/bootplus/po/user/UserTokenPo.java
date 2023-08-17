package com.chuang.bootplus.po.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @description: TODO
 * @author nuo
 * @date 2022/8/17 18:03
 * @version 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode()
@ApiModel(value = "根据 token 获取用户信息", description = "")
public class UserTokenPo {

    @ApiModelProperty("token")
    private String token;

}
