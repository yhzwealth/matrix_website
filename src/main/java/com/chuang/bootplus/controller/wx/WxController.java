package com.chuang.bootplus.controller.wx;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.chuang.bootplus.base.enumerate.HttpStatusEnum;
import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.base.utils.BeanUtil;
import com.chuang.bootplus.base.utils.CharUtil;
import com.chuang.bootplus.base.utils.HttpClientUtil;
import com.chuang.bootplus.config.wx.Token;
import com.chuang.bootplus.config.wx.WxProperties;
import com.chuang.bootplus.entity.User;
import com.chuang.bootplus.po.user.UserIdPO;
import com.chuang.bootplus.po.wx.WxBindPo;
import com.chuang.bootplus.po.wx.WxLoginPo;
import com.chuang.bootplus.po.wx.WxUserInfoPo;
import com.chuang.bootplus.service.SysGroupService;
import com.chuang.bootplus.service.UserService;
import com.chuang.bootplus.vo.user.UserVO;
import com.chuang.bootplus.vo.wx.WxStateVo;
import com.chuang.bootplus.vo.wx.WxUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @description: TODO 微信
 * @author nuo
 * @date 2022/7/28 2:25
 * @version 1.0
 */
@RestController
@RequestMapping("/wechat")
@Api(tags = {"微信接口"})
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*")
public class WxController {

    final UserService userService;
    final SysGroupService sysGroupService;

    final WxProperties wxProperties;

    @PostMapping("/getState")
    @ApiOperation("获取 state 用于请求二维码...")
    public ApiResponse getState() {
        return new ApiResponse(new WxStateVo().setState(CharUtil.getRandomString(15)));
    }

    // 测接口请求 code 的 url:
    // https://open.weixin.qq.com/connect/qrconnect?appid=wx53cf447461989356&redirect_uri=https%3A%2F%2Fwww.matrix-studio.top&response_type=code&scope=snsapi_login#wechat_redirect

    @PostMapping("/callback")
    public ApiResponse<WxUserVo> callback(@RequestBody WxLoginPo po) {
        if (po.getCode() == null) {
            return new ApiResponse<>("401", "用户授权失败...");
        }
        // 请求 token
        String token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=" + wxProperties.getAppId() +
                "&secret=" + wxProperties.getAppSecret() +
                "&code=" + po.getCode() +
                "&grant_type=authorization_code";
        String tokenContent = HttpClientUtil.get(token_url);
        if (tokenContent == null) {
            return new ApiResponse<>(HttpStatusEnum.RE_CODE_JWT_CREATE);
        }
        Token token = JSON.parseObject(tokenContent, Token.class);

        // 通过 wx_token 请求用户信息
        // String wxUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo?" +
        //         "access_token=" + token.getAccess_token() +
        //         "&openid=" + token.getOpenid();
        // String wxUserInfoContent = HttpClientUtil.get(wxUserInfoUrl);
        // JSONObject wxUserInfo = JSON.parseObject(wxUserInfoContent);
        // String nickname = (String)wxUserInfo.get("nickname");

        // User user = userService.getOne(new LambdaQueryWrapper<User>()
                // .eq(User::getOpenid, token.getOpenid()));
        // if (user == null) {
            // user = new User().setOpenid(token.getOpenid())
                    // .setUsername(UUID.randomUUID().toString().replaceAll("-", ""));
                    // .setUsername(nickname);
            // userService.save(user);
            // return new ApiResponse<>("404", "用户不存在...");
        // }

        return new ApiResponse<>(new WxUserVo()
                .setOpenid(token.getOpenid()));
    }

    @PostMapping("/getUserInfoByOpenId")
    @ApiOperation("通过 openid 获取 用户详细信息")
    public ApiResponse<UserVO> getUserInfoByOpenId(@RequestBody WxUserInfoPo po) {
        User user = userService.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getOpenid, po.getOpenid()));
        if (user == null) {
            return new ApiResponse<>("404", "用户不存在...");
        } else {
            return new ApiResponse<>(BeanUtil.beanA2beanB(user, UserVO.class));
        }
    }

    @PostMapping("/bind")
    @ApiOperation("绑定")
    public ApiResponse bind(@RequestBody WxBindPo po) {
        boolean res = userService.update(new LambdaUpdateWrapper<User>()
                .eq(User::getId, po.getId())
                .set(User::getOpenid, po.getOpenid()));
        if (res) {
            return new ApiResponse();
        } else {
            return new ApiResponse("100", "绑定失败...");
        }
    }

    @PostMapping("/unbind")
    @ApiOperation("解绑")
    public ApiResponse unbind(@RequestBody UserIdPO po) {
        userService.update(new LambdaUpdateWrapper<User>()
                .eq(User::getId, po.getId())
                .set(User::getOpenid, null));
        return new ApiResponse("200", "解绑成功...");
    }


}
