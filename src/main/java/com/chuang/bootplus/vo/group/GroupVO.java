package com.chuang.bootplus.vo.group;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @创建人 wdl
 * @创建时间 2021/8/14
 * @描述
 */
@Data
public class GroupVO {


    @ApiModelProperty(value = "组别的id")
    private String id;

    @ApiModelProperty(value = "组别的名字")
    private String groupName;


}
