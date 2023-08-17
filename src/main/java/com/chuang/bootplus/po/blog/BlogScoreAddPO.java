package com.chuang.bootplus.po.blog;

import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @ author xinyi
 * @create 2022-03-09 20:05
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "组长给组员打分")
public class BlogScoreAddPO {

//    private Long userId;

    private Long blogId;

    private Integer score;

}
