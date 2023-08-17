package com.chuang.bootplus.config.wx;

import io.swagger.annotations.Api;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @description: TODO
 * @author nuo
 * @date 2022/7/28 13:49
 * @version 1.0
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Api(tags = {"微信授权登陆获取的token"})
public class Token {

    private String access_token;

    private Integer expires_in;

    private String refresh_token;

    private String openid;

    private String scope;

    private String unionid;
}
