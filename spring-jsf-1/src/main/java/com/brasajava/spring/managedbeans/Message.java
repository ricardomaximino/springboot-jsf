package com.brasajava.spring.managedbeans;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class Message extends HashMap<String, String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private SessionManagedBean localeManage;
	@Override
	public String get(Object key){
		String message;
		try{
			message = messageSource.getMessage((String)key,null,localeManage.getLocale());
		}catch(NoSuchMessageException ex){
			ex.printStackTrace();
			message = "??????" + key + "?????";
		}
		return message;
	}
}
