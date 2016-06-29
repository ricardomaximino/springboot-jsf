package com.brasajava.spring.managedbeans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.brasajava.beans.AddressImpl;

@Component
@Scope("request")
public class AddressManagedBean extends AddressImpl{
	private static final Logger log = LoggerFactory.getLogger(AddressManagedBean.class);
	
	@PostConstruct
	public void init() {
		log.info("INIT ADDRESS");
	}
	@PreDestroy
	public void destroy(){
		log.info("AddressManagedBean predestroy");
		
	}
	
}
