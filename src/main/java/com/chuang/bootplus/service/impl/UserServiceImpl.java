package com.chuang.bootplus.service.impl;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chuang.bootplus.base.enumerate.HttpStatusEnum;
import com.chuang.bootplus.base.exception.BusException;
import com.chuang.bootplus.base.utils.*;
import com.chuang.bootplus.entity.User;
import com.chuang.bootplus.mapper.UserMapper;
import com.chuang.bootplus.po.Email.EmailSendPO;
import com.chuang.bootplus.po.Email.EmailVertifyPO;
import com.chuang.bootplus.po.project.user.ChangePassword;
import com.chuang.bootplus.po.project.user.RegisterPO;
import com.chuang.bootplus.po.project.user.SubscribePO;
import com.chuang.bootplus.po.user.*;
import com.chuang.bootplus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chuang.bootplus.po.project.user.UserLogin;
import com.chuang.bootplus.vo.user.Subscribe;
import com.chuang.bootplus.vo.user.UserInfoVO;
import com.chuang.bootplus.vo.user.UserVO;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-08-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private JavaMailSender mailSender;//一定要用@Autowired

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public ApiResponse<Void> register(RegisterPO po) {
        if (po.getEmail()==null) throw new BusException("邮箱为空");

            if(!list(new LambdaQueryWrapper<User>().eq(User::getEmail,po.getEmail())).isEmpty()){
                throw new BusException("该邮箱已被申请");
            }

        User user = BeanUtil.beanA2beanB(po, User.class);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        user.setPassword(MD5Util.easyMd5(Constant.DEFAULT_PASSWORD));
        user.setPassword(encoder.encode(po.getPassword()));
        save(user);
        return new ApiResponse<>();
    }

    @Override
    public ApiResponse<Void> changePassword(ChangePassword po) {
        User one = getOne(new LambdaQueryWrapper<User>().eq(User::getEmail, po.getEmail()));
        if(one==null){
            throw new BusException("用户不存在");
        }
        if(!one.getUsername().equals(po.getUsername())){
            throw new BusException("用户名错误");
        }
        if(one.getStudentNumber()!=null){
            if(!one.getStudentNumber().equals(po.getStudentNumber())){
                throw new BusException("学号错误");
            }
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        one.setPassword(encoder.encode(po.getNewPassword()));
        updateById(one);
        return new ApiResponse<>();
    }

    // sadasda
    @Override
    public ApiResponse<UserInfoVO> login(UserLogin po) {
        return new ApiResponse<>();
    }

    @Override
    public ApiResponse<Void> subscribe(SubscribePO po) {
        Integer userId = po.getUserId();
        User byId = getById(userId);
        if(byId == null){
            throw new BusException("没有找到用户");
        }

        if(po.getFlag().equals(0)){
            byId.setIsSubscribe(0);
        }
        if(po.getFlag().equals(1)){
            byId.setIsSubscribe(1);
        }
        boolean b = updateById(byId);
        if(!b){
            throw new BusException("操作失败");
        }
        return new ApiResponse().setReMsg("操作成功");
    }

    @Override
    public ApiResponse<Void> vertify(EmailVertifyPO po) {
        String emailKey = po.getEmailKey();
        // 获取redis的验证码
        String message= (String)redisTemplate.opsForHash().get("emailMessage", emailKey);
        if (message.equals(po.getEmailCode())){
            redisTemplate.opsForHash().delete("emailMessage", emailKey);
            return new ApiResponse().setReMsg("邮箱验证成功");
        }
        return new ApiResponse().setReMsg("邮箱验证失败").setReCode("100");
    }

    @Override
    public ApiResponse<Void> sendCode(EmailSendPO po) {
        String key ="";
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject("验证码邮件");//主题
            //生成随机数
            String code = StringUtil.sixCode();
            //将随机数放置到session中
            mailMessage.setText("matrix工作室温馨提醒您,您收到的验证码是："+code);//内容
            String str = po.getQqStr();
            System.out.println(str);
            mailMessage.setTo(str);//发给谁
            mailMessage.setFrom("3033616351@qq.com");//你自己的邮箱
            mailSender.send(mailMessage);//发送
            // 将验证码存到redis，随机生成一个key
            key = UUID.randomUUID().toString();
            redisTemplate.opsForHash().put("emailMessage", key, code);
        }catch (Exception e){
            e.printStackTrace();
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("emailKey",key);
        return new ApiResponse().setReMsg("发送成功").setBody(map);
    }

    @Override
    public ApiResponse<Void> deleteUser(UserDeletePO po) {
        if(getById(po.getEmpId()).getRoleId()==10){
            throw new BusException("Sorry,您不具备该权限");
        }
        removeById(po.getId());
        return new ApiResponse("删除成功");
    }

    @Override
    public ApiResponse<Void> addUser(UserPO po) {
        if(getById(po.getEmpId()).getRoleId()==10){
            throw new BusException("Sorry,您不具备该权限");
        }
        User user = BeanUtil.beanA2beanB(po, User.class);
        save(user);
        return new ApiResponse("添加成功");
    }

    @Override
    public ApiResponse<Void> updateUser(UserUpdatePO po) {
        if(getById(po.getEmpId()).getRoleId()==10){
            throw new BusException("Sorry,您不具备该权限");
        }
        User user = BeanUtil.beanA2beanB(po, User.class);
        updateById(user);
        return new ApiResponse("修改成功");
    }

    @Override
    public ApiResponse<UserVO> getAll(UserGetPO po) {
        int count = count();
        Page<User> pages = new Page<>(po.getPageNumber(), po.getPageSize());
        Page<User> userPages = page(pages,new LambdaQueryWrapper<User>()
                .eq(User::getIsDelete,0)
                .orderByDesc(User::getGmtCreate));
        if(userPages.getRecords().isEmpty()){
            return new ApiResponse<>(HttpStatusEnum.RE_CODE_NO_HAVE_OBJECT);
        }
        List<UserVO> userVOS = BeanUtil.listA2ListB(userPages.getRecords(), UserVO.class);
        for(UserVO userVO:userVOS){
            userVO.setGmdCreate(userVO.getGmtCreate().toLocalDate());
        }
        return new ApiResponse<>(userVOS,pages.getTotal(),count);
    }

    @Override
    public ApiResponse<UserVO> getByConditions(UserConditionPO po) {
        int count = count(new LambdaQueryWrapper<User>()
                .eq(po.getUsername()!=null, User::getUsername, po.getUsername())
                .eq(po.getStudentNumber()!=null, User::getStudentNumber, po.getStudentNumber())
                .eq(po.getRoleId()!=null , User::getRoleId, po.getRoleId())
                .eq(po.getGroupId()!=null, User::getGroupId, po.getGroupId())
                .eq(po.getSex()!=null, User::getSex, po.getSex())
                .eq(po.getStatus()!=null, User::getStatus, po.getStatus())
                .eq(po.getIsMatrix()!=null, User::getIsMatrix, po.getIsMatrix())
                .eq(po.getUserLevel()!=null, User::getUserLevel, po.getUserLevel())
                .eq(po.getMentorId()!=null, User::getMentorId, po.getMentorId())
                .eq(po.getIsSubscribe()!=null, User::getIsSubscribe, po.getIsSubscribe())
//                .eq(User::getIsDelete,0)
                .orderByDesc(User::getScore)
        );
        Page<User> pages = new Page<>(po.getPageNumber(),po.getPageSize());

        Page<User> userPages = page(pages,new LambdaQueryWrapper<User>()
                .eq(po.getUsername() != null, User::getUsername, po.getUsername())
                .eq(po.getStudentNumber() != null, User::getStudentNumber, po.getStudentNumber())
                .eq(po.getRoleId() != null && po.getUsername() == null && po.getStudentNumber() == null, User::getRoleId, po.getRoleId())
                .eq(po.getGroupId() != null && po.getUsername() == null && po.getStudentNumber() == null, User::getGroupId, po.getGroupId())
                .eq(po.getSex() != null && po.getUsername() == null && po.getStudentNumber() == null, User::getSex, po.getSex())
                .eq(po.getStatus() != null && po.getUsername() == null && po.getStudentNumber() == null, User::getStatus, po.getStatus())
                .eq(po.getIsMatrix() != null && po.getUsername() == null && po.getStudentNumber() == null, User::getIsMatrix, po.getIsMatrix())
                .eq(po.getUserLevel() != null && po.getUsername() == null && po.getStudentNumber() == null, User::getUserLevel, po.getUserLevel())
                .eq(po.getMentorId() != null && po.getUsername() == null && po.getStudentNumber() == null, User::getMentorId, po.getMentorId())
                .eq(po.getIsSubscribe() != null && po.getUsername() == null && po.getStudentNumber() == null, User::getIsSubscribe, po.getIsSubscribe())
                .orderByDesc(User::getScore));
        if(userPages.getRecords()==null){
            return new ApiResponse<>(HttpStatusEnum.RE_CODE_NO_HAVE_OBJECT);
        }
        List<UserVO> userVOS = BeanUtil.listA2ListB(userPages.getRecords(), UserVO.class);
        for (UserVO userVO : userVOS) {
            userVO.setGmdCreate(userVO.getGmtCreate().toLocalDate());
        }
        return new ApiResponse<>(userVOS,pages.getTotal(),count);
    }

    @Override
    public ApiResponse<Subscribe> checkSubscribe(UserIdPO po) {
        User one = getOne(new LambdaQueryWrapper<User>().eq(User::getId, po.getId()));
        if(one == null){
            throw new BusException(HttpStatusEnum.RE_CODE_NO_HAVE_OBJECT);
        }
        Integer isSubscribe = one.getIsSubscribe();
        if(isSubscribe == null || isSubscribe != 0){
            return new ApiResponse<>(new Subscribe(0)).setReMsg("该用户未订阅");
        }else{
            return new ApiResponse<>(new Subscribe(1)).setReMsg("该用户已订阅");
        }
    }

    @Override
    public ApiResponse<UserVO> getUserByToken(UserTokenPo po) {
        if (po == null || po.getToken() == null) {
            throw new BusException("token 为空...");
        }
        Claims claim = jwtUtils.getClaimByToken(po.getToken());
        String email = claim.getSubject();
        User user = getOne(new LambdaQueryWrapper<User>()
                .eq(User::getEmail, email));
        if (user == null) {
            throw new BusException("用户不存在...");
        }
        return new ApiResponse<>(BeanUtil.beanA2beanB(user, UserVO.class));
    }

}
