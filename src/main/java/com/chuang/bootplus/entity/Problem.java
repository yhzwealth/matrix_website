package com.chuang.bootplus.entity;

import com.chuang.bootplus.base.database.EntityBase;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author chang
 * @since 2022-03-09
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "Problem对象", description = "")
public class Problem extends EntityBase {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("外键链接到发布此题目的用户")
    private Long matrixUser;

    @ApiModelProperty("题目名称")
    private String title;

    @ApiModelProperty("	点击链接跳转到题目")
    private String link;

    @ApiModelProperty("题目描述")
    private String problemDescribe;

    @ApiModelProperty("题目等级")
    private String problemLevel;

    @ApiModelProperty("题目标签")
    private String tag;

    @ApiModelProperty("算法标签")
    private String algorithmTag;

    @ApiModelProperty("数据结构标签")
    private String dataStructureTag;

    @ApiModelProperty("题目参考答案，要求内容一定详尽")
    private String answer;

    @ApiModelProperty("题目解答")
    private String video;


}
