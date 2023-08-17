package com.chuang.bootplus.config.security;

import cn.hutool.json.JSONUtil;

import com.chuang.bootplus.base.utils.ApiResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

		response.setContentType("application/json;charset=UTF-8");
		ServletOutputStream outputStream = response.getOutputStream();
		outputStream.write(JSONUtil.toJsonStr(new ApiResponse().setReCode("404").setReMsg("登录失败")).getBytes("UTF-8"));
		outputStream.flush();
		outputStream.close();
	}
}