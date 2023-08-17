package com.chuang.bootplus.service;

import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chuang.bootplus.po.Email.EmailSendPO;
import com.chuang.bootplus.po.Email.EmailVertifyPO;
import com.chuang.bootplus.po.project.user.RegisterPO;
import com.chuang.bootplus.po.project.user.ChangePassword;
import com.chuang.bootplus.po.project.user.SubscribePO;
import com.chuang.bootplus.po.project.user.UserLogin;
import com.chuang.bootplus.po.user.*;
import com.chuang.bootplus.vo.user.Subscribe;
import com.chuang.bootplus.vo.user.UserInfoVO;
import com.chuang.bootplus.vo.user.UserVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-08-14
 */
public interface UserService extends IService<User> {
    ApiResponse<Void> register(RegisterPO po);
    ApiResponse<Void> changePassword(ChangePassword po);
    ApiResponse<UserInfoVO> login(UserLogin po);
    ApiResponse<Void> subscribe(SubscribePO po);

    ApiResponse<Void> vertify(EmailVertifyPO po);

    ApiResponse<Void> sendCode(EmailSendPO po);

    ApiResponse<Void> deleteUser(UserDeletePO po);
    ApiResponse<Void> addUser(UserPO po);
    ApiResponse<Void> updateUser(UserUpdatePO po);
    ApiResponse<UserVO> getAll(UserGetPO po);
    ApiResponse<UserVO> getByConditions(UserConditionPO po);

    ApiResponse<Subscribe> checkSubscribe(UserIdPO po);

    ApiResponse<UserVO> getUserByToken(UserTokenPo po);
}
