package com.brasajava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	//@Autowired
	//private UserDetailsService userDetailsService;
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
				.defaultSuccessUrl("/view/pages/home.jsf")
				.passwordParameter("password")
				.usernameParameter("username")
				.and()
				.exceptionHandling()
				.accessDeniedPage("/view/login/error.jsf")
				.and()
			.logout()
				.logoutUrl("/view/login/logout")
				.logoutSuccessUrl("/view/login/login.jsf?logout");
		
		/*http.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/view/login/logout.jsf"))
				.invalidateHttpSession(true)
				.logoutSuccessUrl("/view/login/login.jsf?logout");*/
	}
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth)throws Exception{
		//auth.userDetailsService(userDetailsService);//.passwordEncoder(passwordEncoder();
		//auth.jdbcAuthentication().getUserDetailsService();
		/*
		 * THIS APROCHE IS USED FOR IN MEMORY AUTHENTICATION
		 */
		auth.inMemoryAuthentication()
				.withUser("user")
				.password("user")
				.roles("USER");
		auth.inMemoryAuthentication()
				.withUser("admin")
				.password("admin")
				.roles("ADMIN");
		/* */
	}
	/*@Bean(name="passwordEncoder")
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}*/

}
