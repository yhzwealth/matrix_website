package com.chuang.bootplus.po.news;

import com.chuang.bootplus.base.database.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author hsy
 * @create 2021-08-14
 * @注意
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "新闻名筛选条件", description = "")
public class NewsListPO extends Page {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "新闻标题(不必须)")
    private String title;
}
