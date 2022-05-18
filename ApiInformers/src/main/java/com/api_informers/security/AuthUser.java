package com.api_informers.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.api_informers.domain.User;

public class AuthUser extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 1L;
	private User user;
	
	public AuthUser(User user) {
		super(user.getUsername(), user.getPassword(), getAuthorities(user));
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
	private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
		String role = user.getRole();
		
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(role);
		return authorities;
	}
	
}
