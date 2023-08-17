package com.chuang.bootplus.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import com.chuang.bootplus.base.database.EntityBase;
import java.io.Serializable;
import java.util.Date;
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
@ApiModel(value = "Blog对象", description = "")
public class Blog extends EntityBase {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "绑定的每日一题id")
    private Long proId;

    @ApiModelProperty(value = "题目名")
    private String proName;

    @ApiModelProperty(value = "提交链接用户id")
    private Long userId;

    private String userName;

    @ApiModelProperty(value = "博客链接")
    private String link;

    private String groupName;

    @ApiModelProperty(value = "博客得分")
    private Integer score;

    /**
     * 乐观锁
     */
//    @TableField(fill = FieldFill.INSERT)
    @Version
    private Integer version;



}
