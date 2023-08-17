package com.chuang.bootplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chuang.bootplus.base.enumerate.HttpStatusEnum;
import com.chuang.bootplus.base.exception.BusException;
import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.base.utils.BeanUtil;
import com.chuang.bootplus.entity.Problem;
import com.chuang.bootplus.mapper.ProblemMapper;
import com.chuang.bootplus.po.problem.*;
import com.chuang.bootplus.service.ProblemService;
import com.chuang.bootplus.service.UserService;
import com.chuang.bootplus.vo.problem.ProblemListVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProblemServiceImpl extends ServiceImpl<ProblemMapper, Problem> implements ProblemService {

    private final UserService userService;

    @Override
    public ApiResponse<ProblemListVO> getByConditions(ProblemConditionPO po) {

        int countAll=count(new LambdaQueryWrapper<Problem>()
        .like(po.getTitle()!=null,Problem::getTitle,po.getTitle())
        .like(po.getGmtCreate()!=null,Problem::getGmtCreate,po.getGmtCreate())
        .like(po.getTag()!=null,Problem::getTag,po.getTag())
        .like(po.getProblemLevel()!=null,Problem::getProblemLevel,po.getProblemLevel())
        .like(po.getAlgorithmTag()!=null,Problem::getAlgorithmTag,po.getAlgorithmTag())
        .like(po.getDataStructureTag()!=null,Problem::getDataStructureTag,po.getDataStructureTag())
        .eq(Problem::getIsDelete,0)
        .orderByDesc(Problem::getGmtCreate));

        Page<Problem> pages=new Page<>(po.getPageNumber(),po.getPageSize());
        Page<Problem> problemPages=page(pages,new LambdaQueryWrapper<Problem>()
                .like(po.getTitle()!=null,Problem::getTitle,po.getTitle())
                .like(po.getGmtCreate()!=null,Problem::getGmtCreate,po.getGmtCreate())
                .like(po.getTag()!=null,Problem::getTag,po.getTag())
                .like(po.getProblemLevel()!=null,Problem::getProblemLevel,po.getProblemLevel())
                .like(po.getAlgorithmTag()!=null,Problem::getAlgorithmTag,po.getAlgorithmTag())
                .like(po.getDataStructureTag()!=null,Problem::getDataStructureTag,po.getDataStructureTag())
                .eq(Problem::getIsDelete,0)
                .orderByDesc(Problem::getGmtCreate));

       if(problemPages.getRecords()==null){
           return new ApiResponse<>(HttpStatusEnum.RE_CODE_NO_HAVE_OBJECT);
       }
        List<ProblemListVO> problemListVOS=BeanUtil.listA2ListB(problemPages.getRecords(),ProblemListVO.class);
        for(ProblemListVO problemListVO:problemListVOS){
            problemListVO.setGmdCreate(problemListVO.getGmtCreate().toLocalDate());
        }

       return new ApiResponse<>(problemListVOS,problemPages.getTotal(),countAll);
    }

    @Override
    public ApiResponse<ProblemListVO> getAll(ProblemListPO po) {
        int countAll=count(new LambdaQueryWrapper<Problem>().orderByDesc(Problem::getGmtCreate));
        Page<Problem> pages=new Page<>(po.getPageNumber(),po.getPageSize());
        Page<Problem> problemPages=page(pages,new LambdaQueryWrapper<Problem>()
                .eq(Problem::getIsDelete,0)
        .orderByDesc(Problem::getGmtCreate));
        if(problemPages.getRecords().isEmpty()){
            return new ApiResponse<>(HttpStatusEnum.RE_CODE_NO_HAVE_OBJECT);
        }
        List<ProblemListVO> problemListVOS=BeanUtil.listA2ListB(problemPages.getRecords(),ProblemListVO.class);
        for(ProblemListVO problemListVO:problemListVOS){
            problemListVO.setGmdCreate(problemListVO.getGmtCreate().toLocalDate());
        }
        return new ApiResponse<>(problemListVOS,problemPages.getTotal(),countAll);
    }



    @Override
    public ApiResponse<Void> modifyProblem(ProblemUpdatePO po) {
        if(userService.getById(po.getMatrixUser()).getRoleId()==10) {
            throw new BusException("Sorry,您不具备该权限");
        }

        LocalDateTime dateTime = null;
        if(po.getGmtCreate()!=null){
            dateTime = LocalDateTime.parse(po.getGmtCreate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }

        update(new LambdaUpdateWrapper<Problem>()
                .eq(Problem::getId,po.getId())
                .set(po.getAlgorithmTag()!=null,Problem::getAlgorithmTag,po.getAlgorithmTag())
                .set(po.getDataStructureTag()!=null,Problem::getDataStructureTag,po.getDataStructureTag())
                .set(po.getTag()!=null,Problem::getTag,po.getTag())
                .set(po.getAnswer()!=null,Problem::getAnswer,po.getAnswer())
                .set(po.getLink()!=null,Problem::getLink,po.getLink())
                .set(po.getProblemDescribe()!=null,Problem::getProblemDescribe,po.getProblemDescribe())
                .set(po.getProblemLevel()!=null,Problem::getProblemLevel,po.getProblemLevel())
                .set(po.getTitle()!=null,Problem::getTitle,po.getTitle())
                .set(po.getVideo()!=null,Problem::getVideo,po.getVideo())
                .set(po.getGmtCreate()!=null,Problem::getGmtCreate,dateTime));

        return new ApiResponse("修改成功");
    }

    @Override
    public ApiResponse<Void> deleteProblem(ProblemDeletePO po) {
        if(userService.getById(po.getMatrixUser()).getRoleId()==10){
            throw new BusException("Sorry,您不具备该权限");
        }
        removeById(po.getPId());
        return new ApiResponse("删除成功");
    }

    @Override
    public ApiResponse<Void> sendProblem(ProblemPO po) {
        if(userService.getById(po.getMatrixUser()).getRoleId()==10){
            throw new BusException("Sorry,您不具备该权限");
        }
        Problem problem=BeanUtil.beanA2beanB(po,Problem.class);
        LocalDateTime dateTime = null;
        if(po.getCreateTime()!=null){
            // po.getCreateTime() => "2022-09-02"
            dateTime = LocalDateTime.parse(po.getCreateTime() + " 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
        problem.setGmtCreate(dateTime);
        save(problem);
        return new ApiResponse("发送成功");
    }
}
