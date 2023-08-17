package com.chuang.bootplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.entity.Problem;
import com.chuang.bootplus.po.problem.*;
import com.chuang.bootplus.vo.problem.ProblemListVO;

public interface ProblemService extends IService<Problem> {
    ApiResponse<Void> sendProblem(ProblemPO po);
    ApiResponse<Void> modifyProblem(ProblemUpdatePO po);
    ApiResponse<Void> deleteProblem(ProblemDeletePO po);
    ApiResponse<ProblemListVO> getAll(ProblemListPO po);
    ApiResponse<ProblemListVO> getByConditions(ProblemConditionPO po);

}
