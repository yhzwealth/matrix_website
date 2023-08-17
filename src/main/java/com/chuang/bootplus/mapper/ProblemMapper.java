package com.chuang.bootplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chuang.bootplus.entity.Problem;
import com.chuang.bootplus.po.problem.ProblemConditionPO;
import com.chuang.bootplus.vo.problem.ProblemListVO;

import java.util.List;


public interface ProblemMapper extends BaseMapper<Problem> {
    List<Problem> getByConditions(ProblemConditionPO po);
}
