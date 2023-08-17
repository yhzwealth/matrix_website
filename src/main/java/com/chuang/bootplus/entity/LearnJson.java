package com.chuang.bootplus.entity;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
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
 * @since 2022-11-01
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName(value = "learn_json",autoResultMap = true)
@ApiModel(value = "LearnJson对象", description = "")
public class LearnJson extends EntityBase {

    private static final long serialVersionUID = 1L;

    private Integer groupId;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private JSONArray learnMessage;


}
