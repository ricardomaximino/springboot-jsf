package com.brasajava.spring.managedbeans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.brasajava.beans.CustomerImpl;

@Component
@Scope("request")
public class CustomerManagedBean extends CustomerImpl{
	private static final Logger log = LoggerFactory.getLogger(CustomerManagedBean.class);
	
	@PostConstruct
	public void init() {
		log.info("INIT CUSTOMER");
	}
	@PreDestroy
	public void destroy(){
		log.info("CustomerManagedBean predestroy");
		
	}

}
