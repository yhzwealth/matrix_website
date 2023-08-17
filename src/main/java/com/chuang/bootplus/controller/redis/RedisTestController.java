package com.chuang.bootplus.controller.redis;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author lcc
 * @create 2021-05-29
 * @注意 本内容仅限于dev414内部传阅，禁止外泄以及用于其他的商业目的
 */
@Api(tags = {"Redis"})
@RestController
@RequestMapping("redis")
public class RedisTestController {

    @Autowired
    RedisTemplate redisTemplate;

    @PostMapping("getConstValues")
    public String getConstValues(){
        Map<String,Integer> consts = redisTemplate.opsForHash().entries("consts");
        System.out.println(consts);
        return consts.toString();
    }
}
