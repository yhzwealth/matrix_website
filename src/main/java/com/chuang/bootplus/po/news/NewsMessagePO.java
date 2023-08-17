package com.chuang.bootplus.po.news;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author hsy
 * @create 2021-08-15
 * @注意
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode()
@ApiModel(value = "新闻详情", description = "")
public class NewsMessagePO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "新闻id")
    private Long id;
}
