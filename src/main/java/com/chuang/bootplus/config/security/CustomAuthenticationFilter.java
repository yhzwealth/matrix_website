package com.chuang.bootplus.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        System.out.println(request.getContentType());
//
//
//        if (request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE) || request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)||request.getContentType().contains("application/json")) {
//            ObjectMapper mapper = new ObjectMapper();
//            UsernamePasswordAuthenticationToken authRequest = null;
//            try  {
////                BufferedReader reader = new BodyReaderHttpServletRequestWrapper(request).getReader();
//                Map<String,String> authenticationBean = mapper.readValue(request.getReader(), Map.class);
//                authRequest = new UsernamePasswordAuthenticationToken(authenticationBean.get("username"), authenticationBean.get("password"));
//                System.out.println(authenticationBean.get("username"));
//                System.out.println(authenticationBean.get("password"));
//            } catch (IOException e) {
//                e.printStackTrace();
//                authRequest = new UsernamePasswordAuthenticationToken("", "");
//            } finally {
//                setDetails(request, authRequest);
//                return this.getAuthenticationManager().authenticate(authRequest);
//            }
//        }
//        else {
//            return super.attemptAuthentication(request, response);
//        }
//    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 判断数据是否为json类型
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)
                || request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)
                || request.getContentType().contains("application/json")) {
            ObjectMapper mapper = new ObjectMapper();
            UsernamePasswordAuthenticationToken authRequest = null;
            try  {
//                BufferedReader reader = new BodyReaderHttpServletRequestWrapper(request).getReader();
                Map<String,String> authenticationBean = mapper.readValue(request.getReader(), Map.class);
                authRequest = new UsernamePasswordAuthenticationToken(authenticationBean.get("email"), authenticationBean.get("password"));
            } catch (IOException e) {
                e.printStackTrace();
                authRequest = new UsernamePasswordAuthenticationToken("", "");
            } finally {
                setDetails(request, authRequest);
                return this.getAuthenticationManager().authenticate(authRequest);
            }
        } else {
            return super.attemptAuthentication(request, response);
        }
    }

}
