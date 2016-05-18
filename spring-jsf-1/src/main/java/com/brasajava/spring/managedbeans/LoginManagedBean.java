package com.brasajava.spring.managedbeans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.brasajava.beans.User;
import com.brasajava.repositories.UserRepository;

@Component
@Scope("request")
public class LoginManagedBean {

	private static final Logger log = LoggerFactory.getLogger(LoginManagedBean.class);
	
	private String loginName;
	private String password;
	@Autowired
	private UserRepository userRepository;
	
	@PostConstruct
	public void init(){
		log.info("LoginManagedBean Initiated");
	}
	@PreDestroy
	public void destroy(){
		log.info("LoginManagedBean Destroy");
	}
	
	public String login(){
		log.info("Login Method");
		String result = "../app/customer.jsf";
		if(loginName != null && !loginName.isEmpty()){
			log.info("loginName is not null nether empty");
			User user = userRepository.findByEmail(loginName);
			if(user != null){
				log.info("User: " + user.toString());
				if(user.getPassword() != null ){
					if(user.getPassword().equals(password)){
						result = "../app/user.jsf";
						log.info("Return value is: " + result);
					}else{
						log.error("Wrong password");
					}
				}else if(password.isEmpty()){
					result = "../app/user.jsf";
					log.info("Empty");
				}else{
					log.error("Wrong password Empty");
				}
			}
		}
		log.info("Return value is: " + result);
		return result;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
}
