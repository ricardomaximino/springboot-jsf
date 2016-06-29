package com.brasajava;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	@Override
	protected void configure(HttpSecurity http)throws Exception{
		
		http.csrf()
				.disable();
		
		http.authorizeRequests()
				.antMatchers("/","/index","/view/login/**").permitAll()
				.antMatchers("/view/app/**").hasRole("ADMIN")
				.antMatchers("/view/pages/**").hasAnyRole("ADMIN","USER")
				.and()
			.formLogin()
				.loginPage("/view/login/login.jsf")
				.loginProcessingUrl("/view/login/login.jsf")
				.defaultSuccessUrl("/view/app/user.jsf")
				.passwordParameter("password")
				.usernameParameter("username")
				.and()
				.exceptionHandling()
				.accessDeniedPage("/view/login/error.jsf");
		
		http.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/view/login/logout"))
				.invalidateHttpSession(true)
				.logoutSuccessUrl("/view/login/login.jsf");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception{
		
		/*
		 * THIS APROCHE IS USED FOR IN MEMORY AUTHENTICATION
		 * */
		auth.inMemoryAuthentication()
				.withUser("user")
				.password("user")
				.roles("USER");
		auth.inMemoryAuthentication()
				.withUser("admin")
				.password("admin")
				.roles("ADMIN");
		
		
		/*auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("Select username,password,enabled from user INNER JOIN user_roles ON user.id = user_roles.user_id where username=?")
			.authoritiesByUsernameQuery("SELECT username, role FROM user_roles INNER JOIN user ON user.id = user_roles.user_id INNER JOIN roles ON roles.id = user_roles.role_id where username = ?");
		*/
	}
}
