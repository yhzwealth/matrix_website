package com.chuang.bootplus.config.security;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.base.utils.BeanUtil;
import com.chuang.bootplus.base.utils.JwtUtils;
import com.chuang.bootplus.entity.User;
import com.chuang.bootplus.service.SysGroupService;
import com.chuang.bootplus.service.UserService;
import com.chuang.bootplus.vo.user.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {


	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	UserService userService;

	@Autowired
	SysGroupService sysGroupService;



	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

		User user= userService.getOne(new LambdaQueryWrapper<User>().eq(User::getEmail,authentication.getName()));
		response.setContentType("application/json;charset=UTF-8");
		ServletOutputStream outputStream = response.getOutputStream();
		// 生成jwt，并放置到请求头中
		String jwt = jwtUtils.generateToken(authentication.getName());
		response.setHeader(jwtUtils.getHeader(), jwt);
		HashMap<String, Object> map = new HashMap<>();

		map.put("token", jwt);
		map.put("id",user.getId());
		map.put("Username",user.getUsername());
		map.put("roleId",user.getRoleId());
		map.put("isSubscribe",user.getIsSubscribe());
		map.put("openid", user.getOpenid());
		map.put("groupName",sysGroupService.getById(user.getGroupId()).getGroupName());
		System.out.println("---------------");

		outputStream.write(JSONUtil.toJsonStr(new ApiResponse().setBody(map).setReMsg("登录成功")).getBytes("UTF-8"));
		outputStream.flush();
		outputStream.close();
	}

}
