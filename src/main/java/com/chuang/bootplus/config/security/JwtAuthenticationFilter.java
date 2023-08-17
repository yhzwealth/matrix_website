package com.chuang.bootplus.config.security;

import cn.hutool.core.util.StrUtil;
import com.chuang.bootplus.base.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {


	@Autowired
	JwtUtils jwtUtils;
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

//		System.out.println("0"+request);

		String jwt = request.getHeader(jwtUtils.getHeader());
//		System.out.println("1"+jwtUtils.getHeader());
//		System.out.println("2"+request.getHeader(jwtUtils.getHeader()));
		if (StrUtil.isBlankOrUndefined(jwt)) {
			// 没有jwt直接放行
			chain.doFilter(request, response);
			return;
		}
		System.out.println(12312);

		Claims claim = jwtUtils.getClaimByToken(jwt);
		if (claim == null) {
			throw new JwtException("token 异常");
		}
		if (jwtUtils.isTokenExpired(claim)) {
			// 会注入进验证失败的异常中
			throw new JwtException("token已过期");
		}

		String username = claim.getSubject();

		// 将token信息存放
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, null);


		SecurityContextHolder.getContext().setAuthentication(token);
		// 放行
		chain.doFilter(request, response);

	}
}
