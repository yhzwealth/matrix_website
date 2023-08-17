package com.chuang.bootplus.service.impl;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.base.utils.BeanUtil;
import com.chuang.bootplus.base.utils.RedisUtil;
import com.chuang.bootplus.entity.LearnJson;
import com.chuang.bootplus.entity.LearnModule;
import com.chuang.bootplus.entity.LearnPoint;
import com.chuang.bootplus.mapper.LearnModuleMapper;
import com.chuang.bootplus.mapper.LearnPointMapper;
import com.chuang.bootplus.po.leanmodule.LearnModulePO;
import com.chuang.bootplus.service.LearnJsonService;
import com.chuang.bootplus.service.LearnModuleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chuang.bootplus.service.LearnPointService;
import com.chuang.bootplus.vo.group.GroupVO;
import com.chuang.bootplus.vo.leanmodule.LearnModuleVO;
import com.chuang.bootplus.vo.learnpoint.LearnPointVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-08-14
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LearnModuleServiceImpl extends ServiceImpl<LearnModuleMapper, LearnModule> implements LearnModuleService {

    private final LearnModuleMapper learnModuleMapper;

    private final LearnPointService learnPointService;

    private final LearnJsonService learnJsonService;

    private final RedisUtil redisUtil;


    @Override
    public ApiResponse<LearnModuleVO> learnModuleList(LearnModulePO learnModulePO) {
        String groupId = learnModulePO.getGroupId();


        List<LearnModule> list = list(new LambdaQueryWrapper<LearnModule>().
                eq(LearnModule::getGroupId, groupId)
                .orderByAsc(LearnModule::getModuleLevel)
                .orderByAsc(LearnModule::getSort)
        );
        List<LearnModuleVO> learnModuleVOS = BeanUtil.listA2ListB(list, LearnModuleVO.class);
        for (LearnModuleVO learnModuleVO : learnModuleVOS) {
            List<LearnPoint> list1 = learnPointService.list(new LambdaQueryWrapper<LearnPoint>()
                    .eq(LearnPoint::getModuleId, learnModuleVO.getId())
                    .orderByAsc(LearnPoint::getSort)
            );
            learnModuleVO.setLearnPointVOList(BeanUtil.listA2ListB(list1,LearnPointVO.class));
        }
        return new ApiResponse<LearnModuleVO>(
                learnModuleVOS
        );
    }

    @Override
    public ApiResponse<LearnModuleVO> learnModuleListByRedis(LearnModulePO learnModulePO) {
        String groupId = learnModulePO.getGroupId();
        // redis不存在当前组的路线就查库
        if (!redisUtil.hasKey("study:"+learnModulePO.getGroupId())){
            LearnJson one = learnJsonService.getOne(new LambdaQueryWrapper<LearnJson>().eq(LearnJson::getGroupId, groupId));
            JSONArray message = one.getLearnMessage();
            // System.out.println(learnModuleVOS.toString());
            String s = JSONUtil.toJsonStr(message);
            List<LearnModuleVO> learnModuleVOS = JSONObject.parseArray(message.toJSONString(), LearnModuleVO.class);
            redisUtil.set("study:"+learnModulePO.getGroupId(),s);
            return new ApiResponse<LearnModuleVO>(learnModuleVOS);
        }

        // 这一步保证了redis中有组别数据
        String str= (String) redisUtil.get("study:" + groupId);
        List<LearnModuleVO> list = JSONUtil.toList(str, LearnModuleVO.class);
        return new ApiResponse(list);

    }


}