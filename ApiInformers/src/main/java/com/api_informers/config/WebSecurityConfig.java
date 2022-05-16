package com.api_informers.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService customUserDetailsService;
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.cors().and().csrf().disable();
        http.authorizeRequests()
        	.antMatchers("/api/home").permitAll()
            .antMatchers("/api/informador/index").permitAll()
            .antMatchers("/api/informador/new").permitAll()
            .antMatchers("/api/informador/registro").permitAll()
            .antMatchers("/api/users/new").permitAll()
            .antMatchers("/api/informador/edit").hasAuthority("INFORMER")
            .antMatchers("/api/informador/newFile").hasAuthority("INFORMER")
            .antMatchers("/api/informador/files").hasAuthority("INFORMER")
            .antMatchers("/api/informador/editFile").hasAuthority("INFORMER")
            .antMatchers("/api/informador/deleteFile").hasAuthority("INFORMER")
            .antMatchers("/login").permitAll()
            .anyRequest().authenticated()
		    .and()
            .formLogin()
                .permitAll()
                .loginProcessingUrl("/login")
            .and()
            .logout()
                .permitAll();
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**", "/icon/**");
    }
    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
        	.inMemoryAuthentication()
        	.withUser("user1").password(passwordEncoder().encode("1234")).roles("INFORMER")
        	.and()
        	.withUser("user2").password(passwordEncoder().encode("1234")).roles("VALIDATOR");
    }
    */
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(customUserDetailsService)
	    	.passwordEncoder(passwordEncoder());
    }
}
