package com.api_informers.security;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api_informers.domain.User;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService{

	static final String uriGetUserName = "http://localhost:8081/api/users/username/{username}";
	
	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		
		RestTemplate restTemplate = new RestTemplate();
		
		User user = restTemplate.getForObject(
					 uriGetUserName,
					 User.class,userName);
		
		return new AuthUser(user);
		
	}

	private static Collection<? extends GrantedAuthority> getAuthorities(User user) {		
		String userRole = user.getRole();
								 
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRole);
		return authorities;
	}

}