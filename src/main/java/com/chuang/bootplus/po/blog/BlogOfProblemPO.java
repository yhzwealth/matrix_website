package com.chuang.bootplus.po.blog;


import com.chuang.bootplus.base.database.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value = "获取对应的博客", description = "")
public class BlogOfProblemPO extends Page {

    @ApiModelProperty(value = "题目名字",example = "8")
    private String proName;

    @ApiModelProperty(value = "提交时间",example = "")
    private String sendTime;

    @ApiModelProperty(value = "组别名",example = "前端组，后端组，AI组，IOS组，游戏组)")
    private String groupName;

    @ApiModelProperty(value = "是否批改完成",example = "1:已批改，0未批改")
    private Integer isSelect;


}
