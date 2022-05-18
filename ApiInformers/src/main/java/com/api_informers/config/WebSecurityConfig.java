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
    		.sessionManagement().sessionCreationPolicy(STATELESS)
    		.and()
    		.authorizeRequests()
    		.antMatchers("/authenticate").permitAll()
    		.antMatchers("/api/home").permitAll()
    		.antMatchers("/api/users/new").permitAll()
    	    .antMatchers("/api/informador/index").permitAll()
    	    .antMatchers("/api/informador/new").permitAll()
    	    .antMatchers("/api/informador/registro").permitAll()
    	    .antMatchers("/api/informador/edit").hasAuthority("INFORMER")
    	    .antMatchers("/api/informador/newFile").hasAuthority("INFORMER")
    	    .antMatchers("/api/informador/files").hasAuthority("INFORMER")
    	    .antMatchers("/api/informador/editFile").hasAuthority("INFORMER")
    	    .antMatchers("/api/informador/deleteFile").hasAuthority("INFORMER")
    		.and()
    		.addFilter(customAuthenticationFilter)
    		.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
	
}
