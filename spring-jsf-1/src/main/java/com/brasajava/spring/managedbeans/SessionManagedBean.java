package com.brasajava.spring.managedbeans;

import java.util.Locale;

import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class SessionManagedBean {
	
	private static final Logger log = LoggerFactory.getLogger(SessionManagedBean.class);
	private Locale locale;
	private String textLocale;
	public static final Locale DEFAUT_LOCALE = new Locale("es");

	public void init() {
		this.setLocale(FacesContext.getCurrentInstance().getViewRoot().getLocale());
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getTextLocale() {
		return textLocale;
	}

	public void setTextLocale(String textLocale) {
		log.info(" Text Locale = " + textLocale);
		try{
			Locale locale = new Locale(textLocale);
			this.setLocale(locale);
			this.textLocale = textLocale;
			log.info("Text Locale has been converted successfully the locale get language result is: " + locale.getLanguage());
		}catch(Exception ex){
			this.setLocale(DEFAUT_LOCALE);
			this.setTextLocale(DEFAUT_LOCALE.getLanguage());
			ex.printStackTrace();
		}
		
	}

}
