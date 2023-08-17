package com.chuang.bootplus.po.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode()
@ApiModel(value = "删除成员", description = "")
public class UserDeletePO {
    @ApiModelProperty("成员id")
    private Long id;

    @ApiModelProperty("执行操作的成员id")
    private Long empId;

    @ApiModelProperty("真实姓名")
    private String username;

    @ApiModelProperty("角色")
    private Long roleId;
}
