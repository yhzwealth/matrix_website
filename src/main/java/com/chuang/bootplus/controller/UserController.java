package com.chuang.bootplus.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.entity.Problem;
import com.chuang.bootplus.entity.User;
import com.chuang.bootplus.po.Email.EmailSendPO;
import com.chuang.bootplus.po.Email.EmailVertifyPO;
import com.chuang.bootplus.po.project.user.ChangePassword;
import com.chuang.bootplus.po.project.user.RegisterPO;
import com.chuang.bootplus.po.project.user.SubscribePO;
import com.chuang.bootplus.po.project.user.UserLogin;
import com.chuang.bootplus.po.user.*;
import com.chuang.bootplus.service.ProblemService;
import com.chuang.bootplus.service.UserService;
import com.chuang.bootplus.vo.user.Subscribe;
import com.chuang.bootplus.vo.user.UserInfoVO;
import com.chuang.bootplus.vo.user.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(tags = {"用户"})
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String rootEmail;

    final UserService userService;
    final ProblemService problemService;

    @ApiOperation("用户注册、招贤纳士")
    @PostMapping("/register")
    public ApiResponse<Void> register(@RequestBody RegisterPO po){
        return userService.register(po);
    }

    // Security
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ApiResponse<UserInfoVO> login(@RequestBody UserLogin po){
        return new ApiResponse();
    }

    @ApiOperation("通过 Token 获取用户信息")
    @PostMapping("/getInfo")
    public ApiResponse<UserVO> getUserByToken(@RequestBody UserTokenPo po){
        return userService.getUserByToken(po);
    }

    @ApiOperation("邮箱验证码验证")
    @PostMapping("/emailVertify")
    public ApiResponse<Void> emailVertify(@RequestBody EmailVertifyPO po){
        return userService.vertify(po);
    }


    @ApiOperation("发送验证码到qq邮箱")
    @PostMapping("/emailSend")
    public ApiResponse<Void> sendCode(@RequestBody EmailSendPO po){
        return userService.sendCode(po);
    }


    @ApiOperation("用户改密码")
    @PostMapping("/changePassword")
    public ApiResponse<Void> changePassword(@RequestBody ChangePassword po){
        return userService.changePassword(po);
    }

    @ApiOperation("用户订阅/取消订阅")
    @PostMapping("/subscribe")
    public ApiResponse<Void> subscribe(@RequestBody SubscribePO po){
          return userService.subscribe(po);
    }

    @ApiOperation("查看用户是否订阅")
    @PostMapping("/checkSubscribe")
    public ApiResponse<Subscribe> checkSubscribe(@RequestBody UserIdPO po){
        return userService.checkSubscribe(po);
    }

    @PostMapping("/deleteUser")
    @ApiOperation("删除用户")
    public ApiResponse<Void> deleteUser(@RequestBody UserDeletePO po) {
        return userService.deleteUser(po);
    }


    @PostMapping("/updateUser")
    @ApiOperation("修改用户")
    public ApiResponse<Void> updateUser(@RequestBody UserUpdatePO po){
        return userService.updateUser(po);
    }

    @PostMapping("/getByConditions")
    @ApiOperation("根据条件查询用户")
    public ApiResponse<UserVO> getByConditions(@RequestBody UserConditionPO po){
        return userService.getByConditions(po);
    }

    @PostMapping("/getAll")
    @ApiOperation("查询所有用户")
    public ApiResponse<UserVO> getAll(@RequestBody UserGetPO po){
        return userService.getAll(po);
    }

    @ApiOperation("发送每日一题邮件")
    @Scheduled(cron ="0 0 8 ? * *",zone = "GMT+8:00")   // cron表达式格式  秒 分钟 小时 日 月 年
    @Async  // 开启异步
    public void sendEmail(){
        try {
            List<User> list = userService.list(new LambdaQueryWrapper<User>().eq(User::getIsSubscribe, 0));
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            String time = LocalDateTime.now().toString().substring(0, 10);
            // 每天如果早上八点前没发布新的题目的话，会把最新的题目发送给用户
            // 由于是分等级发送不彤难度的题目，所以每一个难度的题目每天都要创建，否则都是发送最新的

            list.stream().forEach(user -> {
                String diff = "M" + user.getUserLevel();
                String name = user.getUsername();
                Problem problemOne = problemService.getOne(new LambdaQueryWrapper<Problem>()
                        .eq(Problem::getProblemLevel,diff).like(Problem::getGmtCreate,time));
                if(problemOne != null) {
                    String problem =  problemOne.getTitle() + " " + problemOne.getLink();
                    mailMessage.setSubject("每日一题邮件"); //主题
                    mailMessage.setText("Hi " + name + ":\n" +
                            "\t\n" +
                            "  今天是" + time + "，感谢你，订阅Matrix工作室每日一题栏目，我们将每天为您推送一道算法题目。\n" +
                            "  \n" +
                            "  今日题目：" + problem + "（难度："+ diff +"）\n" +
                            "  \n" +
                            "  做完记得写一篇博客总结哦，博客提交地址：http://8.141.147.254\n" +
                            "\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t每天进步一点点，加油！");//内容

                    mailMessage.setTo(user.getEmail());//发给谁
                    mailMessage.setFrom("1029366100@qq.com");//你自己的邮箱
                    mailSender.send(mailMessage);//发送
                    System.out.println(name + " success");
                }
                else{
                    System.out.println(name + " send fail");
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}

