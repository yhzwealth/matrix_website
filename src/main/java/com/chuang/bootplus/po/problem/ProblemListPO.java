package com.chuang.bootplus.po.problem;

import com.chuang.bootplus.base.database.Page;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@ToString
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode()
@ApiModel(value = "获取全部题目", description = "")
public class ProblemListPO extends Page {
    private static final long serialVersionUID = 1L;
}
