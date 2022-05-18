package com.api_informers.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_informers.models.AuthenticatedUser;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping(value="/api/users", produces=MediaType.APPLICATION_JSON_VALUE)
public class UserAuthenticateController {

	@GetMapping("authenticated")
	public ResponseEntity<AuthenticatedUser> getAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication!= null) {
			Object userDetails = authentication.getPrincipal();
			if(userDetails != null && userDetails instanceof UserDetails)
			{
				UserDetails secUser = (UserDetails) userDetails;
				String username = secUser.getUsername();
				
				List<String> roles = secUser.getAuthorities()
											.stream()
											.map(authority -> authority.getAuthority())
											.collect(Collectors.toList());
				AuthenticatedUser authenticatedUser = new AuthenticatedUser(username, roles);
				return new ResponseEntity<>(authenticatedUser,HttpStatus.OK); 
			}
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping("roles/{username}")
	@PreAuthorize("#username == authentication.principal.username")
	public ResponseEntity<AuthenticatedUser> getMyRoles(@PathVariable String username) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication!= null){
			Object userDetails = authentication.getPrincipal();
			if(userDetails != null && userDetails instanceof UserDetails){
				UserDetails secUser = (UserDetails) userDetails;
				
				List<String> roles = secUser.getAuthorities()
											.stream()
												.map(authority -> authority.getAuthority())
												.collect(Collectors.toList());
				AuthenticatedUser authenticatedUser = new AuthenticatedUser(username, roles);
				return new ResponseEntity<>(authenticatedUser,HttpStatus.OK); 
			}
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
}
