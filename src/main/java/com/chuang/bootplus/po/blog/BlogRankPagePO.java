package com.chuang.bootplus.po.blog;

import com.chuang.bootplus.base.database.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value = "排名分页", description = "")
public class BlogRankPagePO extends Page {

}