package com.chuang.bootplus.vo.wx;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description: TODO
 * @author nuo
 * @date 2022/8/5 18:45
 * @version 1.0
 */
@Data
@Accessors(chain = true)
public class WxUserVo {

    @ApiModelProperty("微信唯一标识")
    private String openid;

}
