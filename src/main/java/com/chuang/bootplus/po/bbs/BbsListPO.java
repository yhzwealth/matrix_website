package com.chuang.bootplus.po.bbs;

import com.chuang.bootplus.base.database.Page;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @program: studio-official-website
 * @author: iamYBG
 * @description: 获取留言版内容
 * @create: 2021-09-02
 */

@Data
@ToString
@NoArgsConstructor
//@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "留言板内容获取", description = "")
public class BbsListPO extends Page {
    private static final long serialVersionUID = 1L;
}
