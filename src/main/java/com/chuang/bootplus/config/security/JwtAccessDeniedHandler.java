package com.chuang.bootplus.config.security;

import cn.hutool.json.JSONUtil;
import com.chuang.bootplus.base.utils.ApiResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

		response.setContentType("application/json;charset=UTF-8");
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);

		ServletOutputStream outputStream = response.getOutputStream();

		// jwt出现异常
		outputStream.write(JSONUtil.toJsonStr(new ApiResponse<>().setReCode("405").setReMsg(accessDeniedException.getMessage())).getBytes("UTF-8"));

		outputStream.flush();
		outputStream.close();

	}
}
