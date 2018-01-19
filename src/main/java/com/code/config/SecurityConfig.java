package com.code.config;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.header.writers.frameoptions.WhiteListedAllowFromStrategy;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.social.security.SpringSocialConfigurer;

import com.code.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;
	
	@Autowired
	private UserService iUserDao;
	SecurityConfig(UserService iUserDao){
		this.iUserDao=iUserDao;
	}

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
		        .passwordEncoder(passwordEncoder())
				.usersByUsernameQuery("select username,password, enabled from users where username=?")
				.authoritiesByUsernameQuery("select username, role from user_roles where username=?");

	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()

				// control by log in for page
				.antMatchers("/chatting").access("hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_EMPLOYEE')")

				.antMatchers("/users").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/users/add").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/users/**/update").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/users/**/delete").access("hasRole('ROLE_ADMIN')")
				
				.antMatchers("/post").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_EMPLOYEE')")
				
				.antMatchers("/admin").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/users/add").access("hasRole('ROLE_ADMIN')")
				
				.antMatchers("/users/**/update").access("hasRole('ROLE_ADMIN')")

				.anyRequest().permitAll()
				
		   .and()
		   		.formLogin()
		   		.loginPage("/login")
		   		.successHandler(new SuccessLoginHandler(iUserDao))//.defaultSuccessUrl("/")
		   		.failureHandler(new CustomLoginFailureHandler())//.failureUrl("/login")
		   		.usernameParameter("username")
				.passwordParameter("password")
			.and()
				.logout().logoutSuccessUrl("/login?logout")
			.and()
			 	.rememberMe()
			 	.rememberMeCookieName("example-app-remember-me")
		        .tokenRepository(persistentTokenRepository())
		        .tokenValiditySeconds(24 * 60 * 60)
			.and()
				.exceptionHandling()//.accessDeniedPage("/403")
				.accessDeniedHandler(accessDeniedHandler())
			.and().csrf()
			.csrfTokenRepository(new CookieCsrfTokenRepository()) // setting token
			;
		// add iframe work
		http.headers().frameOptions().disable();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	@Bean
	public AccessDeniedHandler accessDeniedHandler(){
	    return new CustomAccessDeniedHandler();
	}
	
	@Bean
	  public PersistentTokenRepository persistentTokenRepository() {
	      JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
	      repo.setDataSource(dataSource);
	      return repo;
	 }

}