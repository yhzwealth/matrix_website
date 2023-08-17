package com.chuang.bootplus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.chuang.bootplus.base.database.EntityBase;
import io.swagger.annotations.ApiModel;
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
@TableName("wx_user")
@ApiModel(value = "WxUser对象", description = "")
public class WxUser extends EntityBase {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private String openId;

    private String nickname;

    private String avatarUrl;


}
