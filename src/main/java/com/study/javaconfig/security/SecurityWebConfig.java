package com.study.javaconfig.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.study.web.user.service.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"com.study.web.user"})
public class SecurityWebConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth
			.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery("select username,password,true" +
								" from user where username=?")
				.authoritiesByUsernameQuery("select username,'ROLE_USER' from user where username=?")
				.and()
			.userDetailsService(customUserDetailsService)
			.passwordEncoder(passwordEncoder());

	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.httpBasic()
				.and()
			.authorizeRequests()
				.antMatchers("/resources/**", "/about","/signUpPage","/signup","/").permitAll()
				//.antMatchers("/admin/**").hasRole("ADMIN")
				//.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.logoutSuccessUrl("/");			
	} 
	
}
