package com.chuang.bootplus.po.user;

import com.chuang.bootplus.base.database.Page;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode()
@ApiModel(value = "获取全部成员", description = "")
public class UserGetPO extends Page {
    private static final long serialVersionUID = 1L;
}
