package com.chuang.bootplus.config.security;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chuang.bootplus.entity.User;
import com.chuang.bootplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {


	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(@RequestBody String email) throws UsernameNotFoundException {

		User user= userService.getOne(new LambdaQueryWrapper<User>().eq(User::getEmail,email));
		if (user == null) {
			throw new UsernameNotFoundException("邮箱或密码不正确");
		}

		return new AccountUser(user.getId(), user.getEmail(), user.getPassword(),getUserAuthority());

	}
	/**
	 * 获取用户权限信息（角色、菜单权限）
	 * @return
	 */
	public List<GrantedAuthority> getUserAuthority(){
		// 角色(ROLE_admin)、菜单操作权限 sys:user:list
		return null;
	}


}
