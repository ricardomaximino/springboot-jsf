package com.brasajava.spring.managedbeans;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

@Component
@Scope("session")
public class Message extends HashMap<String, String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(Message.class);
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private SessionManagedBean localeManager;
	
	@Autowired
	LocaleResolver localeResolver;
	@Override
	public String get(Object key){
		String message;
		try{
			log.info("Locale in the MessageSource is: " + localeManager.getLocale().getLanguage());
			message = messageSource.getMessage((String)key,null,localeManager.getLocale());
		}catch(NoSuchMessageException ex){
			ex.printStackTrace();
			message = "??????" + key + "?????";
		}
		return message;
	}
}
