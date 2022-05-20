package com.api_informers.config;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.api_informers.security.CustomAuthenticationFilter;
import com.api_informers.security.CustomAuthorizationFilter;
import com.api_informers.security.RestAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private UserDetailsService customUserDetailsService;
	
	@Autowired
    private RestAuthenticationSuccessHandler authenticationSuccessHandler;
    
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(customUserDetailsService)
            .passwordEncoder(passwordEncoder());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      	// customize login endpoint by overriding the AuthenticationFilter processor url
    	CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());
    	customAuthenticationFilter.setFilterProcessesUrl("/authenticate");
    	
    		http.csrf().disable()
    		.formLogin().disable()
    		.logout().disable()
    		.authorizeRequests()
    		.antMatchers("/authenticate","/authenticate/refresh", "/api/users/**","/api/informador/new").permitAll()
    		.antMatchers("/user").hasAuthority("INFORMER")
    		.antMatchers("/api/informador/edit").hasAuthority("INFORMER")
    	    .antMatchers("/api/informador/newFile").hasAuthority("INFORMER")
    	    .antMatchers("/api/informador/files").hasAuthority("INFORMER")
    	    .antMatchers("/api/informador/editFile").hasAuthority("INFORMER")
    	    .antMatchers("/api/informador/deleteFile").hasAuthority("INFORMER")
    	    .anyRequest().authenticated()
    		.and()
    		.addFilter(customAuthenticationFilter)
    		.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
	
}
