package com.chuang.bootplus.po.wx;

import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @description: TODO
 * @author nuo
 * @date 2022/8/5 18:52
 * @version 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode()
@ApiModel(value = "微信绑定", description = "")
public class WxBindPo {

    private Long id;

    private String openid;
}
