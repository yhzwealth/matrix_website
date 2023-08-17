package com.chuang.bootplus.po.bbs;

import com.chuang.bootplus.base.database.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @program: studio-official-website
 * @author: iamYBG
 * @description: 发布留言
 * @create: 2021-09-02
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode()
@ApiModel(value = "发布留言", description = "")
public class BbsSendPO {

    @ApiModelProperty(value = "发布者用户id")
    private String userId;

    @ApiModelProperty(value = "留言内容")
    private String message;
}
